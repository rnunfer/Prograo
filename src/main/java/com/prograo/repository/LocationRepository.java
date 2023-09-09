package com.prograo.repository;

import com.prograo.domain.Location;
import com.prograo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {


    @Query(value = "SELECT l from Location l WHERE l.city = :city AND l.country = :country")
    public Optional<Location> findByNameAndCity(@Param("city") String city, @Param("country") String country);

    @Query(value = "SELECT L.* FROM location L LEFT JOIN user U ON U.location_id = L.id LEFT JOIN freelancer F ON U.id = F.user_id WHERE F.id = :freelancerId", nativeQuery = true)
    public Location getLocationByFreelancerId(@Param("freelancerId") Long freelancerId);
}
