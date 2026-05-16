package com.tefal.testcase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "Testcase Response" ,
        description = "Schema to hold testcase response information"
)
public class TestcaseResponse {
    private Long id;
    private String title;
    private String type;
    private List<String> steps;
    @JsonProperty("expected_result")
    private String expectedResult;

}
