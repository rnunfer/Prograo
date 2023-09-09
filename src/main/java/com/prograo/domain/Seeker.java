package com.prograo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "seeker")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Seeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "seeker", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Project> projectList;

    @OneToMany(mappedBy = "seeker", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Proposal> proposalList;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "Seeker{" +
                "id=" + id +
                '}';
    }

}
