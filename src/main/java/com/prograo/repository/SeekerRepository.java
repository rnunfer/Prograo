package com.prograo.repository;

import com.prograo.domain.Freelancer;
import com.prograo.domain.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeekerRepository extends JpaRepository<Seeker, Long> {

    @Query(value = "SELECT * FROM seeker S WHERE S.user_id = :userId", nativeQuery = true)
    Optional<Seeker> getSeekerByUserId(@Param("userId") Long userId);
}
