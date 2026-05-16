package com.tefal.testcase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "Testcase",
        description = "Schema to hold Testcase information"
)
public class TestcaseDto {
    @NotEmpty(message = "Type of testcase can not be empty")
    @Schema(
            description = "Type of the testcase" ,example = "positive or negative or boundary"

    )
    private String type;
    @NotEmpty(message = "title of user story can not be empty")
    @Schema(
            description = "Title of the requirement" ,example = "user login"

    )
    private String title;
    @NotEmpty(message = "Steps of testcase can not be empty")
    @Schema(
            description = "Steps of the testcase" ,example = "\"steps\": [\n" +
            "                \"Navigate to the login page\",\n" +
            "                \"Enter a valid email address in the email field\",\n" +
            "                \"Enter the correct password in the password field\",\n" +
            "                \"Click the login/submit button\"\n" +
            "            ]"

    )
    private List<String> steps;
    @NotEmpty(message = "ExpectedResult can not be empty")
    @Schema(
            description = "Expected reslt of testcase" , example = "User is successfully authenticated and redirected to the dashboard/home page"
    )
    @JsonProperty("expected_result")
    private String expectedResult;
}
