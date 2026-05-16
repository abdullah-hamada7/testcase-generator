package com.tefal.requirement.mapper;


import com.tefal.requirement.dto.RequirementRequest;
import com.tefal.requirement.dto.RequirementResponse;
import com.tefal.requirement.entity.Requirement;

public class RequirementMapper {

    public static RequirementResponse mapToRequirementResponse(Requirement requirement){
        RequirementResponse requirementResponse = new RequirementResponse();
        requirementResponse.setId(requirement.getId());
        requirementResponse.setTitle(requirement.getTitle());
        requirementResponse.setUserStory(requirement.getUserStory());
        requirementResponse.setProjectId(requirement.getProjectId());
        requirementResponse.setCreatedAt(requirement.getCreatedAt());
        requirementResponse.setLasModifiedAt(requirement.getUpdatedAt());
        return requirementResponse;
    }

    public static Requirement mapToRequirement(RequirementRequest request , String userId){
        Requirement requirement = new Requirement();
        requirement.setTitle(request.getTitle());
        requirement.setUserStory(request.getUserStory());
        requirement.setProjectId(request.getProjectId());
        requirement.setUserId(userId);
        return requirement;
    }
}
