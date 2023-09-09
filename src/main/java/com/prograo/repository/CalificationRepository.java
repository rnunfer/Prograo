package com.prograo.repository;

import com.prograo.domain.Calification;
import com.prograo.domain.Project;
import com.prograo.dto.CalificationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalificationRepository extends JpaRepository<Calification, Long> {

    @Query(value = "select U.id as idSeeker, U.name as nameSeeker, U.profile_photo as profilePhotoSeeker, C.id as idCalification, C.note as noteCalification, C.description as descriptionCalification, C.image as imageCalification, C.date as dateCalification, C.project_id as projectId from calification C left join project P on C.project_id = P.id left join seeker S on P.seeker_id = S.id left join user U on U.id = S.user_id where P.freelancer_id = :id", nativeQuery = true)
    List<CalificationDTO> getAllCalification(@Param("id") Long id);

    Optional<Calification> getCalificationByProject(Project project);

    @Query(value = "SELECT AVG(c.note) AS totalCalification FROM calification c JOIN project p ON c.project_id = p.id JOIN freelancer f ON p.freelancer_id = f.id WHERE f.id = :freelancerId", nativeQuery = true)
    Double getTotalCalificationFreelancer(@Param("freelancerId") Long freelancerId);

    @Query(value = "SELECT COUNT(*) AS numberCalification FROM calification c JOIN project p ON c.project_id = p.id JOIN freelancer f ON p.freelancer_id = f.id WHERE f.id = :freelancerId", nativeQuery = true)
    Integer getNumberCalificationFreelancer(@Param("freelancerId") Long freelancerId);
}
