package com.tefal.requirement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "Project Response",
        description = "Represents a project in the system"
)
public class ProjectResponse {


    private Long id;

    @Schema(example = "Ecommerce Project")
    private String name;

    @Schema(example = "Project for online shopping platform")
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

}
