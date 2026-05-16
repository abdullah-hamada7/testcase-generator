package com.tefal.aiservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "AI Response",
        description = "Schema to hold information of AI Model Response"
)
public class AIResponse {
    @JsonProperty("testCases")
    private List<TestcaseDto> testcases;

}
