package com.nc.g49_smartcrm.repository;

import com.nc.g49_smartcrm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.deleted = false AND u.email = :email")
    Optional<User> findActiveByEmail(String email);
}
