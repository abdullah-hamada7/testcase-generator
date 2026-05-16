package com.tefal.testcase.service.impl;

import com.tefal.testcase.dto.TestCaseBatchRequest;
import com.tefal.testcase.dto.TestcaseResponse;
import com.tefal.testcase.entity.TestCase;
import com.tefal.testcase.mapper.TestcaseMapper;
import com.tefal.testcase.repository.TestcaseRepository;
import com.tefal.testcase.service.ITestcaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TestcaseServiceImpl implements ITestcaseService {
    private final TestcaseRepository testcaseRepository;
    private static final Logger logger = LoggerFactory.getLogger(TestcaseServiceImpl.class);

    @Transactional
    @Override
    public void createTestcaseBatch(TestCaseBatchRequest testCaseBatchRequest) {
        List<TestCase> testCases  = testCaseBatchRequest.getTestcases().stream()
                .map(t->TestcaseMapper.toEntity(t , testCaseBatchRequest.getRequirementId() , testCaseBatchRequest.getProjectId()))
                .toList();
        testcaseRepository.saveAll(testCases);
        logger.debug("Testcases related requirement ID: {} created",
                testCaseBatchRequest.getRequirementId());
    }

    @Override
    public List<TestcaseResponse> getAllByRequirementId(Long requirementId) {
        return testcaseRepository.findAllByRequirementIdAndDeletedFalse(requirementId)
                .stream()
                .map(TestcaseMapper::toResponse)
                .toList();
    }

    @Override
    public List<TestcaseResponse> getAllByProjectId(Long projectId) {
        return testcaseRepository.findAllByProjectIdAndDeletedFalse(projectId)
                .stream()
                .map(TestcaseMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteByRequirement(Long requirementId) {

        List<TestCase> testCases =
                testcaseRepository.findAllByRequirementIdAndDeletedFalse(requirementId);

        testCases.forEach(t -> t.setDeleted(true));

        testcaseRepository.saveAll(testCases);

        logger.debug("All testcases related to requirement id: {} deleted softly",
                requirementId);
    }

}
