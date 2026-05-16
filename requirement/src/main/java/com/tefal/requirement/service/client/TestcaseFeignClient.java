package com.tefal.requirement.service.client;

import com.tefal.requirement.dto.ResponseDto;
import com.tefal.requirement.dto.TestCaseBatchRequest;
import com.tefal.requirement.dto.TestcaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "testcase" , fallback = TestcaseFallback.class)
public interface TestcaseFeignClient {

    @PostMapping(value = "api/create" ,consumes = "application/json")
    public ResponseDto createTestcases(@RequestBody TestCaseBatchRequest request);
    @GetMapping(value = "api/requirements/{requirementId}" , consumes = "application/json")
    public List<TestcaseResponse> getTestcasesByRequirementId(@PathVariable Long requirementId);
    @GetMapping(value = "api/projects/{projectId}" , consumes = "application/json")
    public List<TestcaseResponse> getTestcasesByProjectId(@PathVariable Long projectId);
    @DeleteMapping(value = "api/requirements/{requirementId}" , consumes = "application/json")
    public ResponseDto deleteTestcasesByRequirementId(@PathVariable Long requirementId);

}
