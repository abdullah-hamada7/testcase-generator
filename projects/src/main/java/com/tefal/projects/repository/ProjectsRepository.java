package com.tefal.projects.repository;

import com.tefal.projects.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {

    List<Projects> findAllByUserId(String userId);
    List<Projects> findByUserIdAndNameContainingIgnoreCase(String userId, String keyword);
}
