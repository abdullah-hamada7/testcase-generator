package com.tefal.requirement.service.client;

import com.tefal.requirement.dto.ProjectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.AccessDeniedException;
import java.util.List;

@FeignClient(name = "projects" ,fallback = ProjectsFallback.class)
public interface ProjectsFeignClient {
    @GetMapping(value = "api/fetch/{projectId}" , consumes = "application/json")
    public ResponseEntity<ProjectResponse> fetchProject(@PathVariable Long projectId , @RequestHeader("X-User-Id") String userId);
}
