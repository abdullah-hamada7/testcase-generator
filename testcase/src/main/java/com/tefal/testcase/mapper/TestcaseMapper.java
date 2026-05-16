package com.tefal.testcase.mapper;

import com.tefal.testcase.dto.TestcaseDto;
import com.tefal.testcase.dto.TestcaseResponse;
import com.tefal.testcase.entity.TestCase;

public class TestcaseMapper {
    public static TestCase toEntity(TestcaseDto dto, Long requirementId, Long projectId) {
        TestCase tc = new TestCase();
        tc.setType(dto.getType());
        tc.setTitle(dto.getTitle());
        tc.setSteps(dto.getSteps());
        tc.setExpectedResult(dto.getExpectedResult());
        tc.setRequirementId(requirementId);
        tc.setProjectId(projectId);
        return tc;
    }

    public static TestcaseResponse toResponse(TestCase entity) {
        TestcaseResponse res = new TestcaseResponse();
        res.setId(entity.getId());
        res.setType(entity.getType());
        res.setTitle(entity.getTitle());
        res.setSteps(entity.getSteps());
        res.setExpectedResult(entity.getExpectedResult());
        return res;
    }
}
