package com.tefal.testcase.service;


import com.tefal.testcase.dto.TestCaseBatchRequest;
import com.tefal.testcase.dto.TestcaseResponse;

import java.util.List;

public interface ITestcaseService {


    /**
     *
     * @param testCaseBatchRequest
     */
    void createTestcaseBatch(TestCaseBatchRequest testCaseBatchRequest);


    /**
     *
     * @param requirementId
     * @return
     */
    List<TestcaseResponse> getAllByRequirementId(Long requirementId);

    /**
     *
     * @param projectId
     * @return
     */
    List<TestcaseResponse> getAllByProjectId(Long projectId);

    /***
     *
     * @param requirementId
     */
    void deleteByRequirement(Long requirementId);



}
