package com.tefal.requirement.repository;

import com.tefal.requirement.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement , Long> {
    List<Requirement> findAllByUserIdAndProjectId(String userId , Long projectId);

    List<Requirement> findByProjectId(Long projectId);
}
