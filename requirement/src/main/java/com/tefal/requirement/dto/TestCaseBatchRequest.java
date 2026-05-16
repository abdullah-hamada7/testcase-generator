package com.tefal.requirement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "Testcase Request",
        description = "Schema to hold information for creating new testcase"
)
public class TestCaseBatchRequest {
    @NotEmpty(message = "Requirement ID can not be empty")
    @Schema(
            description = "The ID of requirement" , example = "1"
    )
    private Long requirementId;
    @NotEmpty(message = "Project ID can not be empty")
    @Schema(
            description = "The ID of Project" , example = "1"
    )
    private Long projectId;
    @NotEmpty(message = "Testcase list can not be empty")
    @Schema(
            description = "List of generated testcases" ,  example= "{\n" +
            "            \"type\": \"positive\",\n" +
            "            \"title\": \"Valid User Login with Correct Credentials\",\n" +
            "            \"steps\": [\n" +
            "                \"Navigate to the login page\",\n" +
            "                \"Enter a valid email address in the email field\",\n" +
            "                \"Enter the correct password in the password field\",\n" +
            "                \"Click the login/submit button\"\n" +
            "            ],\n" +
            "            \"expected_result\": \"User is successfully authenticated and redirected to the dashboard/home page\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"negative\",\n" +
            "            \"title\": \"Login Attempt with Invalid Email Format\",\n" +
            "            \"steps\": [\n" +
            "                \"Navigate to the login page\",\n" +
            "                \"Enter an invalid email format (e.g., 'useremail.com' without @ symbol)\",\n" +
            "                \"Enter a password in the password field\",\n" +
            "                \"Click the login/submit button\"\n" +
            "            ],\n" +
            "            \"expected_result\": \"System displays an error message indicating the email format is invalid and login is not attempted\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"negative\",\n" +
            "            \"title\": \"Login Attempt with Incorrect Password\",\n" +
            "            \"steps\": [\n" +
            "                \"Navigate to the login page\",\n" +
            "                \"Enter a valid registered email address\",\n" +
            "                \"Enter an incorrect password\",\n" +
            "                \"Click the login/submit button\"\n" +
            "            ],\n" +
            "            \"expected_result\": \"System displays an error message indicating invalid credentials and remains on the login page\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"negative\",\n" +
            "            \"title\": \"Login Attempt with Non-Existent User\",\n" +
            "            \"steps\": [\n" +
            "                \"Navigate to the login page\",\n" +
            "                \"Enter an email address that is not registered in the system\",\n" +
            "                \"Enter any password value\",\n" +
            "                \"Click the login/submit button\"\n" +
            "            ],\n" +
            "            \"expected_result\": \"System displays a generic error message indicating invalid credentials to prevent user enumeration\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"boundary\",\n" +
            "            \"title\": \"Login with Empty Email Field\",\n" +
            "            \"steps\": [\n" +
            "                \"Navigate to the login page\",\n" +
            "                \"Leave the email field empty\",\n" +
            "                \"Enter a password in the password field\",\n" +
            "                \"Click the login/submit button\"\n" +
            "            ],\n" +
            "            \"expected_result\": \"System displays a validation error indicating the email field is required\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"boundary\",\n" +
            "            \"title\": \"Login with Empty Password Field\",\n" +
            "            \"steps\": [\n" +
            "                \"Navigate to the login page\",\n" +
            "                \"Enter a valid email address in the email field\",\n" +
            "                \"Leave the password field empty\",\n" +
            "                \"Click the login/submit button\"\n" +
            "            ],\n" +
            "            \"expected_result\": \"System displays a validation error indicating the password field is required\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"negative\",\n" +
            "            \"title\": \"Login Attempt with SQL Injection Payload\",\n" +
            "            \"steps\": [\n" +
            "                \"Navigate to the login page\",\n" +
            "                \"Enter a SQL injection payload in the email field (e.g., \\\"admin@example.com' OR '1'='1\\\")\",\n" +
            "                \"Enter any value in the password field\",\n" +
            "                \"Click the login/submit button\"\n" +
            "            ],\n" +
            "            \"expected_result\": \"System rejects the input as invalid and displays an error, preventing database manipulation\"\n" +
            "        }\n" +
            "    ]"
    )
    private List<TestcaseDto> testcases;

}
