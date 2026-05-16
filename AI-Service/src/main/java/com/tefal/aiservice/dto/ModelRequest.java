package com.tefal.aiservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Model Request",
        description = "Schema to hold information to request for Calling AI Model"
)
public class ModelRequest {


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
    private String description;


}
