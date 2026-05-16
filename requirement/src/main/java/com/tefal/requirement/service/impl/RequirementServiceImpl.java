package com.tefal.requirement.service.impl;

import com.tefal.requirement.constant.RequirementConstants;
import com.tefal.requirement.dto.*;
import com.tefal.requirement.entity.Requirement;
import com.tefal.requirement.exception.ProjectNotFoundException;
import com.tefal.requirement.exception.RequirementNotFoundException;
import com.tefal.requirement.exception.UnauthorizedAccessException;
import com.tefal.requirement.mapper.RequirementMapper;
import com.tefal.requirement.repository.RequirementRepository;
import com.tefal.requirement.service.IRequirementService;
import com.tefal.requirement.service.client.AIFeignClient;
import com.tefal.requirement.service.client.ProjectsFeignClient;
import com.tefal.requirement.service.client.TestcaseFeignClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RequirementServiceImpl implements IRequirementService {
    private static final Logger logger = LoggerFactory.getLogger(RequirementServiceImpl.class);
    private final RequirementRepository repository;
    private final ProjectsFeignClient projectsFeignClient;
    private final TestcaseFeignClient testcaseFeignClient;
    private final AIFeignClient aiFeignClient;


    @Override
    public void createRequirement(RequirementRequest request, String userId) {
        projectsFeignClient.fetchProject(request.getProjectId(), userId);
        repository.save(RequirementMapper.mapToRequirement(request, userId));
        logger.debug("Requirement is created");
    }

    @Override
    public RequirementResponse getRequirement(Long requirementId, String userId) {

        Requirement requirement = repository.findById(requirementId)
                .orElseThrow(() -> new RequirementNotFoundException(
                        "Requirement with id: " + requirementId + " not found"
                ));

        if (!requirement.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException(
                    "You are not allowed to access this requirement"
            );
        }

        return RequirementMapper.mapToRequirementResponse(requirement);
    }

    @Override
    public void updateRequirement(Long requirementId, String userId, RequirementRequest request) {
        Requirement requirement = repository.findById(requirementId)
                .orElseThrow(() -> new RequirementNotFoundException(
                        "Requirement with id: " + requirementId + " not found"
                ));

        if (!requirement.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException(
                    "You are not allowed to access this requirement"
            );
        }
        requirement.setTitle(request.getTitle());
        requirement.setUserStory(request.getUserStory());
        requirement.setProjectId(request.getProjectId());
        repository.save(requirement);
        logger.debug("the requirement with id: {} is updated" , requirementId);
    }

    @Override
    public void deleteRequirement(Long requirementId, String userId) {
        Requirement requirement = repository.findById(requirementId)
                .orElseThrow(() -> new RequirementNotFoundException(
                        "Requirement with id: " + requirementId + " not found"
                ));

        if (!requirement.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException(
                    "You are not allowed to access this requirement"
            );
        }
        repository.delete(requirement);
        logger.debug("the requirement with id: {} is deleted" , requirementId);

    }

    @Override
    public List<RequirementResponse> getRequirements(String userId, Long projectId) {
        return repository.findAllByUserIdAndProjectId(userId,projectId)
                .stream()
                .map(RequirementMapper::mapToRequirementResponse)
                .toList();
    }

    @Override
    public AIResponse generateTestcases(Long requirementId, String userId) {

        Requirement requirement =
                repository.findById(requirementId)
                        .orElseThrow(() ->
                                new RuntimeException("Requirement not found"));

        if (!requirement.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException("Unauthorized");
        }

        AIRequest aiRequest = new AIRequest(
                requirement.getTitle(),
                requirement.getUserStory()
        );

        AIResponse aiResponse =
                aiFeignClient.generateTestcase(aiRequest);

        if (aiResponse == null
                || aiResponse.getTestcases() == null
                || aiResponse.getTestcases().isEmpty()) {

            throw new RuntimeException("AI returned empty testcases");
        }

        TestCaseBatchRequest batchRequest =
                new TestCaseBatchRequest();

        batchRequest.setRequirementId(requirementId);
        batchRequest.setProjectId(requirement.getProjectId());
        batchRequest.setTestcases(aiResponse.getTestcases());

        testcaseFeignClient.createTestcases(batchRequest);

        return aiResponse;
    }

    @Override
    public List<TestcaseResponse> getTestcasesByRequirementId(String userId, Long requirementId) {
        Requirement requirement = repository.findById(requirementId)
                .orElseThrow(() -> new RuntimeException("Requirement not found"));

        if (!requirement.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException("Unauthorized");
        }

        return testcaseFeignClient.getTestcasesByRequirementId(requirementId);
    }

    @Override
    public List<TestcaseResponse> getTestcasesByProjectId(Long projectId, String userId) {
        List<Requirement> requirements =
                repository.findByProjectId(projectId);

        if (requirements.isEmpty()) {
            throw new RuntimeException("No requirements found for project");
        }

        // ownership check (important security step)
        boolean unauthorized = requirements.stream()
                .anyMatch(r -> !r.getUserId().equals(userId));

        if (unauthorized) {
            throw new UnauthorizedAccessException("Unauthorized access to project");
        }

        return testcaseFeignClient.getTestcasesByProjectId(projectId);
    }
    @Override
    public ResponseDto deleteTestcasesByRequirementId(Long requirementId, String userId) {

        Requirement requirement = repository.findById(requirementId)
                .orElseThrow(() -> new RuntimeException("Requirement not found"));

        if (!requirement.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException("Unauthorized");
        }

        return testcaseFeignClient.deleteTestcasesByRequirementId(requirementId);

    }

}
