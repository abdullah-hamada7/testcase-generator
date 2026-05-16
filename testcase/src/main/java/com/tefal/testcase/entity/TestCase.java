package com.tefal.testcase.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TESTCASE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestCase extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String title;
    @ElementCollection
    @CollectionTable(name = "test_case_steps", joinColumns = @JoinColumn(name = "test_case_id"))
    @Column(name = "step")
    private List<String> steps;
    @Column(columnDefinition = "TEXT")
    private String expectedResult;

    private Long projectId;
    private Long requirementId;
    private boolean deleted;


}
