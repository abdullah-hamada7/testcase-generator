package com.tefal.testcase.controller;

import com.tefal.testcase.constants.TestcaseConstants;
import com.tefal.testcase.dto.ErrorResponseDto;
import com.tefal.testcase.dto.ResponseDto;
import com.tefal.testcase.dto.TestCaseBatchRequest;
import com.tefal.testcase.dto.TestcaseResponse;
import com.tefal.testcase.service.ITestcaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "Some REST APIs for Testcases in Testcase Generator",
        description = "Some REST APIs in Testcase Generator to CreateTestcase , GetTestcasesByRequirementId , GetTestcasesByProjectId , DeleteTestcasesByRequirementId , requirement details"
)
@RestController
@RequestMapping(value = "api" , produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class TestcaseController {

    private final ITestcaseService testcaseService;

    @Operation(
            summary = "Create Testcases REST API",
            description = "REST API to create new Testcases inside TestCase Generator"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createTestcases(@RequestBody TestCaseBatchRequest request){
        testcaseService.createTestcaseBatch(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(TestcaseConstants.STATUS_201 , TestcaseConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Testcases Details related to Requirement ID REST API",
            description = "REST API to fetch Testcases details based on a requirement ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/requirements/{requirementId}")
    public ResponseEntity<List<TestcaseResponse>> getTestcasesByRequirementId(@PathVariable Long requirementId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(testcaseService.getAllByRequirementId(requirementId));
    }

    @Operation(
            summary = "Fetch Testcases Details related to Project ID REST API",
            description = "REST API to fetch Testcases details based on a Project ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<List<TestcaseResponse>> getTestcasesByProjectId(@PathVariable Long projectId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(testcaseService.getAllByProjectId(projectId));
    }

    @Operation(
            summary = "Delete TestCases Details Based On Requirement ID REST API",
            description = "REST API to delete testcases details based on a requirement ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/requirements/{requirementId}")
    public ResponseEntity<ResponseDto> deleteTestcasesByRequirementId(@PathVariable Long requirementId){
        testcaseService.deleteByRequirement(requirementId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(TestcaseConstants.STATUS_200 , TestcaseConstants.MESSAGE_200));
    }




}
