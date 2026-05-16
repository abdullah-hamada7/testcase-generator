package com.tefal.requirement.service.client;

import com.tefal.requirement.dto.AIRequest;
import com.tefal.requirement.dto.AIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AI-Service" , fallback = AIFallback.class)
public interface AIFeignClient {
    @PostMapping(value = "api/generate" , consumes = "application/json")
    public AIResponse generateTestcase(@RequestBody AIRequest aiRequest);
}
