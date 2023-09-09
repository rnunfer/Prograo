package com.prograo.repository;

import com.prograo.domain.Project;
import com.prograo.dto.ProjectDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT" +
            "  p.id AS projectId," +
            "  p.name AS projectTitle," +
            "  p.description AS projectDescription," +
            "  p.work_style AS projectWorkStyle," +
            "  p.status AS projectStatus," +
            "  p.deadline AS projectDeadline," +
            "  p.send_date AS projectSendDate," +
            "  p.start_date AS projectStartDate," +
            "  p.finish_date AS projectFinishDate," +
            "  p.signed_by_seeker AS projectSignedBySeeker," +
            "  p.signed_by_freelancer AS projectSignedByFreelancer," +
            "  p.contract_price AS projectContractPrice," +
            "  su.id AS userSeekerId," +
            "  su.name AS userSeekerName," +
            "  su.profile_photo AS userSeekerProfilePhoto," +
            "  su.title AS userSeekerTitle," +
            "  sl.city AS userSeekerCity," +
            "  sl.country AS userSeekerCountry," +
            "  fu.id AS userFreelancerId," +
            "  fu.name AS userFreelancerName," +
            "  fu.profile_photo AS userFreelancerProfilePhoto," +
            "  fu.title AS userFreelancerTitle," +
            "  fl.city AS userFreelancerCity," +
            "  fl.country AS userFreelancerCountry," +
            "  c.id AS calificationId," +
            "  c.note AS calificationNote," +
            "  c.description AS calificationDescription," +
            "  c.image AS calificationImage," +
            "  c.date AS calificationDate " +
            "FROM Project p " +
            "LEFT JOIN Freelancer f ON p.freelancer_id = f.id " +
            "LEFT JOIN User fu ON f.user_id = fu.id " +
            "LEFT JOIN Seeker s ON p.seeker_id = s.id " +
            "LEFT JOIN User su ON s.user_id = su.id " +
            "LEFT JOIN Location sl ON su.location_id = sl.id " +
            "LEFT JOIN Location fl ON fu.location_id = fl.id " +
            "LEFT JOIN Calification c ON p.id = c.project_id", nativeQuery = true)
    public List<ProjectDTO> getAllProjects();

    @Query(value = "SELECT" +
            "  p.id AS projectId," +
            "  p.name AS projectTitle," +
            "  p.description AS projectDescription," +
            "  p.work_style AS projectWorkStyle," +
            "  p.status AS projectStatus," +
            "  p.deadline AS projectDeadline," +
            "  p.send_date AS projectSendDate," +
            "  p.start_date AS projectStartDate," +
            "  p.finish_date AS projectFinishDate," +
            "  p.signed_by_seeker AS projectSignedBySeeker," +
            "  p.signed_by_freelancer AS projectSignedByFreelancer," +
            "  p.contract_price AS projectContractPrice," +
            "  su.id AS userSeekerId," +
            "  su.name AS userSeekerName," +
            "  su.profile_photo AS userSeekerProfilePhoto," +
            "  su.title AS userSeekerTitle," +
            "  sl.city AS userSeekerCity," +
            "  sl.country AS userSeekerCountry," +
            "  fu.id AS userFreelancerId," +
            "  fu.name AS userFreelancerName," +
            "  fu.profile_photo AS userFreelancerProfilePhoto," +
            "  fu.title AS userFreelancerTitle," +
            "  fl.city AS userFreelancerCity," +
            "  fl.country AS userFreelancerCountry," +
            "  c.id AS calificationId," +
            "  c.note AS calificationNote," +
            "  c.description AS calificationDescription," +
            "  c.image AS calificationImage," +
            "  c.date AS calificationDate " +
            "FROM Project p " +
            "LEFT JOIN Freelancer f ON p.freelancer_id = f.id " +
            "LEFT JOIN User fu ON f.user_id = fu.id " +
            "LEFT JOIN Seeker s ON p.seeker_id = s.id " +
            "LEFT JOIN User su ON s.user_id = su.id " +
            "LEFT JOIN Location sl ON su.location_id = sl.id " +
            "LEFT JOIN Location fl ON fu.location_id = fl.id " +
            "LEFT JOIN Calification c ON p.id = c.project_id " +
            "WHERE fu.id = :userId OR su.id = :userId", nativeQuery = true)
    public List<ProjectDTO> getProjectsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT" +
            "  p.id AS projectId," +
            "  p.name AS projectTitle," +
            "  p.description AS projectDescription," +
            "  p.work_style AS projectWorkStyle," +
            "  p.status AS projectStatus," +
            "  p.deadline AS projectDeadline," +
            "  p.send_date AS projectSendDate," +
            "  p.start_date AS projectStartDate," +
            "  p.finish_date AS projectFinishDate," +
            "  p.signed_by_seeker AS projectSignedBySeeker," +
            "  p.signed_by_freelancer AS projectSignedByFreelancer," +
            "  p.contract_price AS projectContractPrice," +
            "  su.id AS userSeekerId," +
            "  su.name AS userSeekerName," +
            "  su.profile_photo AS userSeekerProfilePhoto," +
            "  su.title AS userSeekerTitle," +
            "  sl.city AS userSeekerCity," +
            "  sl.country AS userSeekerCountry," +
            "  fu.id AS userFreelancerId," +
            "  fu.name AS userFreelancerName," +
            "  fu.profile_photo AS userFreelancerProfilePhoto," +
            "  fu.title AS userFreelancerTitle," +
            "  fl.city AS userFreelancerCity," +
            "  fl.country AS userFreelancerCountry," +
            "  c.id AS calificationId," +
            "  c.note AS calificationNote," +
            "  c.description AS calificationDescription," +
            "  c.image AS calificationImage," +
            "  c.date AS calificationDate " +
            "FROM Project p " +
            "LEFT JOIN Freelancer f ON p.freelancer_id = f.id " +
            "LEFT JOIN User fu ON f.user_id = fu.id " +
            "LEFT JOIN Seeker s ON p.seeker_id = s.id " +
            "LEFT JOIN User su ON s.user_id = su.id " +
            "LEFT JOIN Location sl ON su.location_id = sl.id " +
            "LEFT JOIN Location fl ON fu.location_id = fl.id " +
            "LEFT JOIN Calification c ON p.id = c.project_id " +
            "WHERE p.id = :projectId", nativeQuery = true)
    public ProjectDTO getOneProject(@Param("projectId") Long projectId);

    @Query(value = "SELECT" +
            "  p.id AS projectId," +
            "  p.name AS projectTitle," +
            "  p.description AS projectDescription," +
            "  p.work_style AS projectWorkStyle," +
            "  p.status AS projectStatus," +
            "  p.deadline AS projectDeadline," +
            "  p.send_date AS projectSendDate," +
            "  p.start_date AS projectStartDate," +
            "  p.finish_date AS projectFinishDate," +
            "  p.signed_by_seeker AS projectSignedBySeeker," +
            "  p.signed_by_freelancer AS projectSignedByFreelancer," +
            "  p.contract_price AS projectContractPrice," +
            "  su.id AS userSeekerId," +
            "  su.name AS userSeekerName," +
            "  su.profile_photo AS userSeekerProfilePhoto," +
            "  su.title AS userSeekerTitle," +
            "  sl.city AS userSeekerCity," +
            "  sl.country AS userSeekerCountry," +
            "  fu.id AS userFreelancerId," +
            "  fu.name AS userFreelancerName," +
            "  fu.profile_photo AS userFreelancerProfilePhoto," +
            "  fu.title AS userFreelancerTitle," +
            "  fl.city AS userFreelancerCity," +
            "  fl.country AS userFreelancerCountry," +
            "  c.id AS calificationId," +
            "  c.note AS calificationNote," +
            "  c.description AS calificationDescription," +
            "  c.image AS calificationImage," +
            "  c.date AS calificationDate " +
            "FROM Project p " +
            "LEFT JOIN Freelancer f ON p.freelancer_id = f.id " +
            "LEFT JOIN User fu ON f.user_id = fu.id " +
            "LEFT JOIN Seeker s ON p.seeker_id = s.id " +
            "LEFT JOIN User su ON s.user_id = su.id " +
            "LEFT JOIN Location sl ON su.location_id = sl.id " +
            "LEFT JOIN Location fl ON fu.location_id = fl.id " +
            "LEFT JOIN Calification c ON p.id = c.project_id " +
            "WHERE (fu.id = :userId OR su.id = :userId) AND p.id = :projectId", nativeQuery = true)
    public Optional<ProjectDTO> getOneProjectByUserId(@Param("userId") Long userId, @Param("projectId") Long projectId);

}
