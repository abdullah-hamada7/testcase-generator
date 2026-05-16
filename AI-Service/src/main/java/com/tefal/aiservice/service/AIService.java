package com.tefal.aiservice.service;

import com.tefal.aiservice.dto.AIRequest;
import com.tefal.aiservice.dto.AIResponse;
import com.tefal.aiservice.dto.ModelRequest;
import com.tefal.aiservice.mapper.AIMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AIService implements IAIService{

    @Value("${ai.model.url}")
    private String aiModelUrl;
    private final WebClient webClient;

    @Override
    public AIResponse generate(AIRequest aiRequest) {
        ModelRequest modelRequest = AIMapper.mapToModelRequest(aiRequest);
        return webClient.post()
                .uri(aiModelUrl)
                .bodyValue(modelRequest)
                .retrieve()
                .bodyToMono(AIResponse.class)
                .block();
    }
}
