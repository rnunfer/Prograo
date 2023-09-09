package com.prograo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "freelancer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Freelancer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int rate;

	@Column(length = 1000)
	private String description;

	private String twitter;

	private String facebook;

	private String email;

	private String linkedin;

	@OneToMany(mappedBy = "freelancer")
	@JsonIgnore
	private List<Project> projectList;

	@OneToMany(mappedBy = "freelancer")
	@JsonIgnore
	private List<Proposal> proposalList;

	@OneToMany(mappedBy = "freelancer")
	@JsonIgnore
	private List<FreelancerSkill> freelancer_skills;

	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@Override
	public String toString() {
		return "Freelancer{" +
				"id=" + id +
				", rate=" + rate +
				", description='" + description + '\'' +
				", twitter='" + twitter + '\'' +
				", facebook='" + facebook + '\'' +
				", email='" + email + '\'' +
				", linkedin='" + linkedin + '\'' +
				// exclude circular references
				'}';
	}
}
