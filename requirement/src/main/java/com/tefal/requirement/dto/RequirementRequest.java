package com.tefal.requirement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Schema(
        name = "Requirement",
        description = "Schema to hold information to request for creating Requirement"
)
public class RequirementRequest {

    @NotEmpty(message = "title of user story can not be empty")
    @Schema(
            description = "Title of the requirement" ,example = "user login"

    )
    @Size(min = 5 , max = 100)
    private String title;
    @NotEmpty(message = "description of user story can not be empty")
    @Schema(
            description = "The description that describes user story" ,example = "As a user, I want to log in using email and password so that I can access my account"

    )
    @Size(min = 30 , max= 5000)
    private String userStory;
    @NotNull(message = "Project Id can not be Empty ")
    @Schema(
            description = "the project ID that requirement belongs to it" , example = "1"
    )
    private Long projectId;

}
