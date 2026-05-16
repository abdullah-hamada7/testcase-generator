package com.tefal.aiservice.mapper;


import com.tefal.aiservice.dto.AIRequest;
import com.tefal.aiservice.dto.ModelRequest;

public class AIMapper {
    public static ModelRequest mapToModelRequest(AIRequest aiRequest){
        ModelRequest modelRequest = new ModelRequest();
        modelRequest.setTitle(aiRequest.getTitle());
        modelRequest.setDescription(aiRequest.getUserStory());
        return modelRequest;
    }
}
