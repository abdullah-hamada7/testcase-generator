package com.tefal.projects.mapper;

import com.tefal.projects.dto.ProjectRequest;
import com.tefal.projects.dto.ProjectResponse;
import com.tefal.projects.entity.Projects;

import java.time.LocalDateTime;

public class ProjectMapper {
    public static Projects mapToProjects(ProjectRequest request , String userId){
        if(request == null) return null;
        Projects projects = new Projects();
        projects.setName(request.getName());
        projects.setDescription(request.getDescription());
        projects.setUserId(userId);
        return projects;
    }
    public static ProjectResponse mapToProjectResponse(Projects projects){
        if(projects == null) return null;
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setName(projects.getName());
        projectResponse.setDescription(projects.getDescription());
        projectResponse.setId(projects.getId());
        projectResponse.setCreatedAt(projects.getCreatedAt());
        projectResponse.setLastModifiedAt(projects.getUpdatedAt());
        return projectResponse;
    }

}
