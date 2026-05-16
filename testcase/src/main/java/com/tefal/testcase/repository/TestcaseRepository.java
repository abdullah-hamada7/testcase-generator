package com.tefal.testcase.repository;

import com.tefal.testcase.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestcaseRepository extends JpaRepository<TestCase , Long> {
    List<TestCase> findAllByDeletedFalse();

    List<TestCase> findAllByRequirementIdAndDeletedFalse(
            Long requirementId
    );

    List<TestCase> findAllByProjectIdAndDeletedFalse(
            Long projectId
    );
    Optional<TestCase> findByIdAndDeletedFalse(Long id);

}
