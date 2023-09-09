package com.prograo.repository;

import com.prograo.domain.Proposal;
import com.prograo.dto.ProposalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    @Query(value = "SELECT " +
            "p.id AS proposalId, " +
            "p.title AS proposalTitle, " +
            "p.description AS proposalDescription, " +
            "p.estimated_time AS proposalEstimatedTime, " +
            "p.work_style AS proposalWorkStyle, " +
            "p.status AS proposalStatus, " +
            "p.send_date AS proposalSendDate, " +
            "p.confirm_date AS proposalConfirmDate, " +
            "s.user_id AS userSeekerId, " +
            "su.name AS seekerName, " +
            "su.profile_photo AS seekerProfilePhoto, " +
            "su.title AS seekerTitle, " +
            "l.city AS seekerCity, " +
            "l.country AS seekerCountry, " +
            "f.user_id AS userFreelancerId, " +
            "fu.name AS userFreelancerName " +
            "FROM Proposal p " +
            "LEFT JOIN Seeker s ON p.seeker_id = s.id " +
            "LEFT JOIN User su ON s.user_id = su.id " +
            "LEFT JOIN Freelancer f ON p.freelancer_id = f.id " +
            "LEFT JOIN User fu ON f.user_id = fu.id " +
            "LEFT JOIN Location l ON su.location_id = l.id ", nativeQuery = true)
    public List<ProposalDTO> getAllProposals();

    @Query(value = "SELECT " +
            "p.id AS proposalId, " +
            "p.title AS proposalTitle, " +
            "p.description AS proposalDescription, " +
            "p.estimated_time AS proposalEstimatedTime, " +
            "p.work_style AS proposalWorkStyle, " +
            "p.status AS proposalStatus, " +
            "p.send_date AS proposalSendDate, " +
            "p.confirm_date AS proposalConfirmDate, " +
            "s.user_id AS userSeekerId, " +
            "su.name AS seekerName, " +
            "su.profile_photo AS seekerProfilePhoto, " +
            "su.title AS seekerTitle, " +
            "l.city AS seekerCity, " +
            "l.country AS seekerCountry, " +
            "f.user_id AS userFreelancerId, " +
            "fu.name AS userFreelancerName " +
            "FROM Proposal p " +
            "LEFT JOIN Seeker s ON p.seeker_id = s.id " +
            "LEFT JOIN User su ON s.user_id = su.id " +
            "LEFT JOIN Freelancer f ON p.freelancer_id = f.id " +
            "LEFT JOIN User fu ON f.user_id = fu.id " +
            "LEFT JOIN Location l ON su.location_id = l.id " +
            "WHERE fu.id = :userId OR su.id = :userId", nativeQuery = true)
    public List<ProposalDTO> getProposalsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT " +
            "p.id AS proposalId, " +
            "p.title AS proposalTitle, " +
            "p.description AS proposalDescription, " +
            "p.estimated_time AS proposalEstimatedTime, " +
            "p.work_style AS proposalWorkStyle, " +
            "p.status AS proposalStatus, " +
            "p.send_date AS proposalSendDate, " +
            "p.confirm_date AS proposalConfirmDate, " +
            "s.user_id AS userSeekerId, " +
            "su.name AS seekerName, " +
            "su.profile_photo AS seekerProfilePhoto, " +
            "su.title AS seekerTitle, " +
            "l.city AS seekerCity, " +
            "l.country AS seekerCountry, " +
            "f.user_id AS userFreelancerId, " +
            "fu.name AS userFreelancerName " +
            "FROM Proposal p " +
            "LEFT JOIN Seeker s ON p.seeker_id = s.id " +
            "LEFT JOIN User su ON s.user_id = su.id " +
            "LEFT JOIN Freelancer f ON p.freelancer_id = f.id " +
            "LEFT JOIN User fu ON f.user_id = fu.id " +
            "LEFT JOIN Location l ON su.location_id = l.id " +
            "WHERE p.id = :proposalId", nativeQuery = true)
    public ProposalDTO getProposalById(@Param("proposalId") Long proposalId);

    @Query(value = "SELECT " +
            "p.id AS proposalId, " +
            "p.title AS proposalTitle, " +
            "p.description AS proposalDescription, " +
            "p.estimated_time AS proposalEstimatedTime, " +
            "p.work_style AS proposalWorkStyle, " +
            "p.status AS proposalStatus, " +
            "p.send_date AS proposalSendDate, " +
            "p.confirm_date AS proposalConfirmDate, " +
            "s.user_id AS userSeekerId, " +
            "su.name AS seekerName, " +
            "su.profile_photo AS seekerProfilePhoto, " +
            "su.title AS seekerTitle, " +
            "l.city AS seekerCity, " +
            "l.country AS seekerCountry, " +
            "f.user_id AS userFreelancerId, " +
            "fu.name AS userFreelancerName " +
            "FROM Proposal p " +
            "LEFT JOIN Seeker s ON p.seeker_id = s.id " +
            "LEFT JOIN User su ON s.user_id = su.id " +
            "LEFT JOIN Freelancer f ON p.freelancer_id = f.id " +
            "LEFT JOIN User fu ON f.user_id = fu.id " +
            "LEFT JOIN Location l ON su.location_id = l.id " +
            "WHERE fu.id = :userFreelancerId AND su.id = :userId AND p.status = 'waiting'", nativeQuery = true)
    public ProposalDTO getProposalDTOByUserFreelancerId(@Param("userId") Long userId, @Param("userFreelancerId") Long userFreelancerId);

}
