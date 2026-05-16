package com.tefal.aiservice.service;


import com.tefal.aiservice.dto.AIRequest;
import com.tefal.aiservice.dto.AIResponse;
import com.tefal.aiservice.dto.ModelRequest;

public interface IAIService {
    AIResponse generate(AIRequest aiRequest);
}
