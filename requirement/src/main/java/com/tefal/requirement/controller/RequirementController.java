package com.tefal.requirement.controller;

import com.tefal.requirement.constant.RequirementConstants;
import com.tefal.requirement.dto.*;
import com.tefal.requirement.service.IRequirementService;
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
        name = "CRUD REST APIs for Requirements in Testcase Generator",
        description = "CRUD REST APIs in Testcase Generator to CREATE, UPDATE, FETCH AND DELETE requirement details"
)
@RestController
@RequestMapping(value = "api" , produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class RequirementController {

    private final IRequirementService requirementService;


    @Operation(
            summary = "Create Requirement REST API",
            description = "REST API to create new Requirement inside TestCase Generator"
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
    public ResponseEntity<ResponseDto> createRequirement(@RequestHeader("X-User-Id") String userId , @RequestBody RequirementRequest request){
        requirementService.createRequirement(request , userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(RequirementConstants.STATUS_201 , RequirementConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Requirement Details REST API",
            description = "REST API to fetch Requirement details based on a requirement ID"
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
    @GetMapping("/fetch/{requirementId}")
    public ResponseEntity<RequirementResponse> getRequirement(@PathVariable Long requirementId , @RequestHeader("X-User-Id") String userId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(requirementService.getRequirement(requirementId , userId));
    }
    @Operation(
            summary = "Fetch All Requirements Details REST API",
            description = "REST API to fetch all Requirements details based on a User ID and Project Id"
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
    @GetMapping("/fetch-all")
    public ResponseEntity<List<RequirementResponse>> getAllRequirements(Long projectId ,@RequestHeader("X-User-Id") String userId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(requirementService.getRequirements(userId , projectId));
    }
    @Operation(
            summary = "Update Requirement Details REST API",
            description = "REST API to update requirement details based on a project ID , user ID"
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
    @PutMapping("update/{requirementId}")
    public ResponseEntity<ResponseDto> updateRequirement(@PathVariable Long requirementId ,@RequestHeader("X-User-Id") String userId , @RequestBody RequirementRequest request){
        requirementService.updateRequirement(requirementId , userId , request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(RequirementConstants.STATUS_200 , RequirementConstants.MESSAGE_200));

    }
    @Operation(
            summary = "Delete Requirement Details REST API",
            description = "REST API to delete requirement details based on a requirement ID and user ID"
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
    @DeleteMapping("delete/{requirementId}")
    public ResponseEntity<ResponseDto> deleteRequirement(@PathVariable Long requirementId , @RequestHeader("X-User-Id") String userId){
        requirementService.deleteRequirement(requirementId , userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(RequirementConstants.STATUS_200 , RequirementConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Generate Testcases REST API",
            description = "REST API to create testcases inside TestCase Generator"
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
    @PostMapping("/{id}/generate-testcases")
    public ResponseEntity<AIResponse> generateTestcases(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") String userId
    ) {

        return ResponseEntity.status(HttpStatus.OK).body(requirementService.generateTestcases(id, userId));
    }

    @GetMapping("/{requirementId}/testcases")
    public ResponseEntity<List<TestcaseResponse>> getByRequirement(
            @PathVariable Long requirementId,
            @RequestHeader("X-User-Id") String userId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(requirementService.getTestcasesByRequirementId(userId , requirementId));

    }

    @GetMapping("/project/{projectId}/testcases")
    public ResponseEntity<List<TestcaseResponse>> getByProject(
            @PathVariable Long projectId,
            @RequestHeader("X-User-Id") String userId) {

        return ResponseEntity.status(HttpStatus.OK)
        .body(requirementService.getTestcasesByProjectId(projectId, userId));
    }
    @DeleteMapping("/{requirementId}/testcases")
    public ResponseEntity<ResponseDto> deleteByRequirement(
            @PathVariable Long requirementId,
            @RequestHeader("X-User-Id") String userId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(requirementService.deleteTestcasesByRequirementId(requirementId, userId));
    }



}
