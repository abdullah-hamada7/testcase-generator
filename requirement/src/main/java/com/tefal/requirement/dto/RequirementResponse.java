package com.tefal.requirement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "Requirement",
        description = "Schema to hold information to response for fetching Requirement"
)
public class RequirementResponse {

    private Long id;
    @Schema(example = "User Login")
    private String title;

    @Schema(example = "As a user, I want to log in using email and password so that I can access my account")
    private String userStory;

    @Schema(example = "1")
    private Long projectId;

    private LocalDateTime createdAt;
    private LocalDateTime lasModifiedAt;


}
