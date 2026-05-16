package com.tefal.requirement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Requirement extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="requirement_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "user_story" , length = 5000)
    private String userStory;

    @Column(name ="project_id")
    private Long projectId;

    @Column(name ="user_id")
    private String userId;

}
