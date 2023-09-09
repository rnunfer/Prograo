package com.prograo.repository;

import com.prograo.domain.Freelancer;
import com.prograo.dto.SkillUsed;
import com.prograo.dto.FreelancerBox;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer,Long>{

    @Query(value = "SELECT * FROM freelancer F WHERE F.user_id = :userId", nativeQuery = true)
    Optional<Freelancer> getFreelancerByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT U.id AS userId, U.name AS userName, U.profile_photo AS userProfilePhoto, U.title AS userTitle, L.city AS city, L.country AS country, F.id AS freelancerId, F.rate AS freelancerRate, S.skills AS skills, C.totalCalification AS totalCalification, C.numberCalification AS numberCalification FROM freelancer F JOIN user U ON F.user_id = U.id LEFT JOIN location L ON U.location_id = L.id LEFT JOIN ( SELECT FS.freelancer_id, GROUP_CONCAT(S.name) AS skills FROM freelancer_skill FS JOIN skill S ON FS.skill_id = S.id WHERE FS.outstanding = true GROUP BY FS.freelancer_id ) S ON F.id = S.freelancer_id LEFT JOIN ( SELECT F.id, AVG(C.note) AS totalCalification, COUNT(C.id) AS numberCalification FROM freelancer F LEFT JOIN project P ON F.id = P.freelancer_id LEFT JOIN calification C ON P.id = C.project_id GROUP BY F.id ) C ON F.id = C.id WHERE U.status = 'active'", nativeQuery = true)
    List<FreelancerBox> getAllFreelancerBoxes();
}
