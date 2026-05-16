package com.tefal.requirement.service.client;

import com.tefal.requirement.dto.AIRequest;
import com.tefal.requirement.dto.AIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AIFallback implements AIFeignClient{

    @Override
    public AIResponse generateTestcase(AIRequest aiRequest) {
        return null;
    }
}
