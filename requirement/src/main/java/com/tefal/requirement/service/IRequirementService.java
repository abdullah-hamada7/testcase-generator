package com.tefal.requirement.service;

import com.tefal.requirement.dto.*;

import java.util.List;

public interface IRequirementService {
    /**
     *
     * @param request
     * @param userId
     */
    void createRequirement(RequirementRequest request , String userId);

    /**
     *
     * @param requirementId
     * @param userId
     * @return
     */
    RequirementResponse getRequirement(Long requirementId , String userId);

    /**
     *
     * @param requirementId
     * @param userId
     * @param request
     * @return
     */
    void updateRequirement(Long requirementId , String userId , RequirementRequest request);

    /**
     *
     * @param requirementId
     * @param userId
     */
    void deleteRequirement(Long requirementId , String userId);

    /**
     *
     * @param userId
     * @param projectId
     * @return
     */
    List<RequirementResponse> getRequirements(String userId , Long projectId);

    /**
     *
     * @param requirementId
     * @param userId
     */
    AIResponse generateTestcases(Long requirementId, String userId);


    /**
     *
     * @param userId
     * @param requirementId
     * @return
     */
    List<TestcaseResponse> getTestcasesByRequirementId(String userId , Long requirementId);

    /**
     *
     * @param projectId
     * @param userId
     * @return
     */
    List<TestcaseResponse> getTestcasesByProjectId(Long projectId, String userId);

    /**
     *
     * @param requirementId
     * @param userId
     * @return
     */
    ResponseDto deleteTestcasesByRequirementId(Long requirementId, String userId);
}
