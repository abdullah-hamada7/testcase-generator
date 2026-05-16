package com.tefal.projects.service;

import com.tefal.projects.dto.ProjectRequest;
import com.tefal.projects.dto.ProjectResponse;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface IProjectsService {
    /**
     *
     * @param projectRequest
     * @param userId
     */
    void createProject(ProjectRequest projectRequest , String userId);

    /**
     *
     * @param projectId
     * @param userId
     * @return
     */
    ProjectResponse fetchProject(Long projectId , String userId) throws RuntimeException;

    /**
     *
     * @param userId
     * @return
     */
    List<ProjectResponse> getAllProjects(String userId);

    /**
     *
     * @param projectId
     * @param userId
     */
    void deleteProject(Long projectId, String userId) throws RuntimeException;

    /**
     *
     * @param projectRequest
     * @param projectId
     * @param userId
     * @return
     */
    boolean updateProject(ProjectRequest projectRequest , Long projectId , String userId) throws RuntimeException;


    /**
     *
     * @param keyword
     * @param userId
     * @return
     */
    List<ProjectResponse> searchProjects(String keyword , String userId);

}
