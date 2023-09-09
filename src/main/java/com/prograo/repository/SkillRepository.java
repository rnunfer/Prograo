package com.prograo.repository;

import com.prograo.domain.Skill;
import com.prograo.dto.SkillUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SkillRepository extends JpaRepository<Skill,Long> {

    @Query(value = "SELECT IF(COUNT(DISTINCT fs.skill_id) >= 1, 1, null) AS has_skills FROM freelancer f LEFT JOIN freelancer_skill fs ON f.id = fs.freelancer_id WHERE f.id = :freelancerId GROUP BY f.id;", nativeQuery = true)
    public Integer countSkillsByFreelancerId(@Param("freelancerId") Long freelancerId);

    @Query(value = "SELECT IF(COUNT(DISTINCT fs.skill_id) >= 1, 1, null) AS has_skills FROM freelancer f LEFT JOIN freelancer_skill fs ON f.id = fs.freelancer_id WHERE f.id = :freelancerId AND fs.outstanding = 1 GROUP BY f.id", nativeQuery = true)
    public Integer countOutstandingSkillsByFreelancerId(@Param("freelancerId") Long freelancerId);

    @Query(value = "select S.name, FS.outstanding, Count(PS.id_project) as numUsed from freelancer_skill FS left join skill S on FS.skill_id = S.id left join project_skill PS on S.id = PS.id_skill where FS.freelancer_id = :id group by FS.skill_id;", nativeQuery = true)
    List<SkillUsed> getFreelancerDetailsSkills(@Param("id") Long id);

    @Query(value = "select S.* from skill S left join project_skill PS on PS.id_skill = S.id where PS.id_project = :projectId", nativeQuery = true)
    Set<Skill> getAllSkillsProject(@Param("projectId") Long projectId);
}
