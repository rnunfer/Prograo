package com.prograo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "skill")
    @JsonIgnore
    private List<FreelancerSkill> freelancer_skills;

    @ManyToMany(
            mappedBy = "skills", fetch = FetchType.EAGER)
    @JsonIgnore
    Set<Project> projects = new HashSet<>();

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
