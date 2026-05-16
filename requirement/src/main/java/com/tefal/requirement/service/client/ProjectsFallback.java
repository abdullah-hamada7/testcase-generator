package com.tefal.requirement.service.client;

import com.tefal.requirement.dto.ProjectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Component
public class ProjectsFallback implements ProjectsFeignClient{
    @Override
    public ResponseEntity<ProjectResponse> fetchProject(Long projectId, String userId) throws RuntimeException {
        return null;
    }
}
