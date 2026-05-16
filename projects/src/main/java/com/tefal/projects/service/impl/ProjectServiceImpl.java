package com.tefal.projects.service.impl;

import com.tefal.projects.dto.ProjectRequest;
import com.tefal.projects.dto.ProjectResponse;
import com.tefal.projects.entity.Projects;
import com.tefal.projects.exception.ProjectNotFoundException;
import com.tefal.projects.mapper.ProjectMapper;
import com.tefal.projects.repository.ProjectsRepository;
import com.tefal.projects.service.IProjectsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements IProjectsService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectsRepository projectsRepository;

    @Override
    public void createProject(ProjectRequest projectRequest, String userId) {
        Projects projects = ProjectMapper.mapToProjects(projectRequest , userId);
        projectsRepository.save(projects);
    }

    @Override
    public ProjectResponse fetchProject(Long projectId, String userId) throws RuntimeException {
        Projects projects = projectsRepository.findById(projectId).orElseThrow(()-> new ProjectNotFoundException("project with id " + projectId + "not found"));
        if(projects.getUserId().equals(userId)){
            return ProjectMapper.mapToProjectResponse(projects);
        }else{

            logger.warn("Unauthorized access attempt by user {}", userId);
            throw new RuntimeException("Not authorized");
        }

    }

    @Override
    public List<ProjectResponse> getAllProjects(String userId) {

        return projectsRepository.findAllByUserId(userId).stream()
                .map(ProjectMapper::mapToProjectResponse)
                .toList();
    }

    @Override
    public void deleteProject(Long projectId, String userId) throws RuntimeException {
        Projects projects = projectsRepository.findById(projectId).orElseThrow(()-> new ProjectNotFoundException("project with id " + projectId + "not found"));
        if(projects.getUserId().equals(userId)){
            projectsRepository.delete(projects);

        }else{
            logger.warn("Unauthorized access attempt by user {}", userId);
            throw new RuntimeException("User is not authorized....");
        }
    }

    @Override
    public boolean updateProject(ProjectRequest projectRequest, Long projectId, String userId) throws RuntimeException {
        boolean isUpdated = false;
        Projects projects = projectsRepository.findById(projectId).orElseThrow(()-> new ProjectNotFoundException("project with id " + projectId + "not found"));
        if(projects.getUserId().equals(userId)){
            projects.setName(projectRequest.getName());
            projects.setDescription(projectRequest.getDescription());
            projectsRepository.save(projects);
            isUpdated = true;
        }else{
            logger.warn("Unauthorized access attempt by user {}", userId);
            throw new RuntimeException("Not authorized");
        }
        return isUpdated;
    }

    @Override
    public List<ProjectResponse> searchProjects(String keyword, String userId) {
        return projectsRepository
                .findByUserIdAndNameContainingIgnoreCase(userId, keyword)
                .stream()
                .map(ProjectMapper::mapToProjectResponse)
                .toList();
    }


}
