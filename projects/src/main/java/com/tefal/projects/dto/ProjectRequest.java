package com.tefal.projects.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(
        name = "Projects",
        description = "Schema to hold information to request for creating project"
)
public class ProjectRequest {
    @NotEmpty(message = "Project name can not be empty")
    @Schema(
            description = "Project name that indicates to project name" ,example = "Ecommerce Project"

    )
    private String name;
    @NotBlank
    @Schema(
            description = "Project description that describes the project" ,example = "Ecommerce Project"

    )
    @Size(min = 10 , message = "Description must be at least 10 characters")
    private String description;

}
