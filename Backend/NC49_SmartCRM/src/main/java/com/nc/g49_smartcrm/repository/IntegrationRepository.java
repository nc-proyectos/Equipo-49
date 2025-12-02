package com.nc.g49_smartcrm.repository;

import com.nc.g49_smartcrm.model.Channel;
import com.nc.g49_smartcrm.model.Integration;
import com.nc.g49_smartcrm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntegrationRepository extends JpaRepository<Integration, Long> {
    Optional<Integration> findByType(Channel type);

    Optional<Integration> findByExternalId(String externalId);

    Optional<Integration> findByTypeAndUserId(Channel type, Long userId);
}
