package com.prograo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "freelancer_skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreelancerSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freelancer_id", nullable = false)
    private Freelancer freelancer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(nullable = false)
    private boolean outstanding;
}
