package com.tefal.aiservice.controller;

import com.tefal.aiservice.dto.AIRequest;
import com.tefal.aiservice.dto.AIResponse;
import com.tefal.aiservice.service.IAIService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Generate Testcase Endpoint in Testcase Generator",
        description = "Generate Testcase Endpoint in Testcase Generator to Generate Testcase"
)
@RestController
@RequestMapping(value = "api" , produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AIController {

    private final IAIService aiService;
    @PostMapping("/generate")
    public ResponseEntity<AIResponse> generateTestcase(@RequestBody AIRequest aiRequest){
        return ResponseEntity.status(HttpStatus.OK)
                .body(aiService.generate(aiRequest));
    }
}

