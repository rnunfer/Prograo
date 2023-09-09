package com.prograo.repository;

import com.prograo.domain.FreelancerSkill;
import com.prograo.dto.FreelancerSkillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreelancerSkillRepository extends JpaRepository<FreelancerSkill,Long> {

    @Query(value = "select S.id, S.name, FS.outstanding from skill S left join freelancer_skill FS on S.id = FS.skill_id left join freelancer F on FS.freelancer_id = F.id where F.id = :id", nativeQuery = true)
    public List<FreelancerSkillDTO> getAllFreelancerSkill(@Param("id") Long id);

    @Query(value = "SELECT FS.* FROM freelancer F RIGHT JOIN freelancer_skill FS ON F.id = FS.freelancer_id LEFT JOIN skill S ON FS.skill_id = S.id WHERE F.id = :freelancerId AND S.id = :skillId", nativeQuery = true)
    public Optional<FreelancerSkill> getFreelancerSkillByFreelancerIdAndSkillId(@Param("freelancerId") Long freelancerId, @Param("skillId") Long skillId);
}
