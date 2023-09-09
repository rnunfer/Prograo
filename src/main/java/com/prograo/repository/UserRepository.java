package com.prograo.repository;

import com.prograo.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE user.email = :email AND user.password = :password", nativeQuery = true)
    public Optional<User> login(@Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT U.* FROM user U LEFT JOIN freelancer F ON U.id = F.user_id WHERE F.id = :freelancerId", nativeQuery = true)
    public User getUserByFreelancerId(@Param("freelancerId") Long freelancerId);

    public Optional<User> findByEmail(@Param("email") String email);
}
