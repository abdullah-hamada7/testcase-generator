package com.tefal.requirement.service.client;

import com.tefal.requirement.dto.ResponseDto;
import com.tefal.requirement.dto.TestCaseBatchRequest;
import com.tefal.requirement.dto.TestcaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestcaseFallback implements TestcaseFeignClient{
    @Override
    public ResponseDto createTestcases(TestCaseBatchRequest request) {
        return null;
    }

    @Override
    public List<TestcaseResponse> getTestcasesByRequirementId(Long requirementId) {
        return List.of();
    }

    @Override
    public List<TestcaseResponse> getTestcasesByProjectId(Long projectId) {
        return List.of();
    }

    @Override
    public ResponseDto deleteTestcasesByRequirementId(Long requirementId) {
        return null;
    }
}
