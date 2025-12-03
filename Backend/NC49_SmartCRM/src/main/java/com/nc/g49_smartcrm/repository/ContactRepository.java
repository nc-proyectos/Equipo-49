package com.nc.g49_smartcrm.repository;

import com.nc.g49_smartcrm.dto.CountPerDayDto;
import com.nc.g49_smartcrm.dto.SourceCountDto;
import com.nc.g49_smartcrm.model.Contact;
import com.nc.g49_smartcrm.model.ContactStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByOrderByLastnameAsc();

    List<Contact>findAllByOwnerId(Long ownerId);

    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    List<Contact> findByStatusOrderByLastnameAsc(ContactStatus status);

    Long countByOwner_IdAndStatus(Long userId, ContactStatus status);

    @Query("""
    SELECT new com.nc.g49_smartcrm.dto.SourceCountDto(c.source, COUNT(c))
    FROM Contact c
    WHERE c.owner.id = :userId
    GROUP BY c.source
    """)
    List<SourceCountDto> countContactsBySourceForUser(Long userId);

    @Query("""
    SELECT new com.nc.g49_smartcrm.dto.CountPerDayDto(
        CAST(c.createdAt AS date), COUNT(c)
    )
    FROM Contact c
    WHERE c.owner.id = :userId
    GROUP BY CAST(c.createdAt AS date)
    ORDER BY CAST(c.createdAt AS date)
    """)
    List<CountPerDayDto> countContactsPerDayForUser(Long userId);




}
