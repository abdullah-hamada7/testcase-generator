package com.tefal.projects.controller;

import com.tefal.projects.constant.ProjectsConstants;
import com.tefal.projects.dto.ErrorResponseDto;
import com.tefal.projects.dto.ProjectRequest;
import com.tefal.projects.dto.ProjectResponse;
import com.tefal.projects.dto.ResponseDto;
import com.tefal.projects.service.IProjectsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for Projects in Testcase Generator",
        description = "CRUD REST APIs in Testcase Generator to CREATE, UPDATE, FETCH AND DELETE project details"
)
@RestController
@RequestMapping(value = "api" , produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class ProjectsController {
    private final IProjectsService projectService;
    @PostMapping("/create")
    @Operation(
            summary = "Create Project REST API",
            description = "REST API to create new Project inside TestCase Generator"
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
    public ResponseEntity<ResponseDto> createProject(
            @Valid @RequestBody ProjectRequest projectRequest,
            @RequestHeader("X-User-Id") String userId) {

        projectService.createProject(projectRequest, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(ProjectsConstants.STATUS_201, ProjectsConstants.MESSAGE_201));
    }


    @Operation(
            summary = "Fetch Project Details REST API",
            description = "REST API to fetch Project details based on a project ID"
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
    @GetMapping("/fetch/{projectId}")

    public ResponseEntity<ProjectResponse> fetchProject(@PathVariable Long projectId ,@RequestHeader("X-User-Id") String userId) throws RuntimeException {

        return ResponseEntity.status(HttpStatus.OK)
                .body(projectService.fetchProject(projectId,userId));
    }



    @Operation(
            summary = "Fetch All Project Details REST API",
            description = "REST API to fetch all Project details related to user based on a project ID"
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
    public ResponseEntity<List<ProjectResponse>> getAllProjects(@RequestHeader("X-User-Id") String userId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(projectService.getAllProjects(userId));
    }

    @Operation(
            summary = "Update Project Details REST API",
            description = "REST API to update project details based on a project ID , user ID"
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
    @PutMapping("/update/{projectId}")
    public ResponseEntity<ResponseDto> updateProject(@RequestHeader("X-User-Id") String userId, @PathVariable Long projectId , @RequestBody ProjectRequest projectRequest) throws RuntimeException {
        boolean isUpdated = projectService.updateProject(projectRequest, projectId, userId);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ProjectsConstants.STATUS_200, ProjectsConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ProjectsConstants.STATUS_417 , ProjectsConstants.MESSAGE_417_UPDATE));

        }
    }

    @Operation(
            summary = "Delete Project Details REST API",
            description = "REST API to delete Project details based on a project ID and user ID"
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
    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<ResponseDto> deleteProject(@PathVariable Long projectId , @RequestHeader("X-User-Id") String userId) throws RuntimeException {
        projectService.deleteProject(projectId , userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(ProjectsConstants.STATUS_200,ProjectsConstants.MESSAGE_200));
    }


    @Operation(
            summary = "Search Projects Details REST API",
            description = "REST API to search Project details based on a project ID and userID"
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
    @GetMapping("/search")
    public ResponseEntity<List<ProjectResponse>> searchProjects(@RequestParam String keyword , @RequestHeader("X-User-Id") String userId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(projectService.searchProjects(keyword , userId));
    }


}
