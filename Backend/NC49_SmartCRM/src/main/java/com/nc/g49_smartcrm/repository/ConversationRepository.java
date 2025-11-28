package com.nc.g49_smartcrm.repository;

import com.nc.g49_smartcrm.dto.ChannelCountDto;
import com.nc.g49_smartcrm.dto.CountPerDayDto;
import com.nc.g49_smartcrm.model.Channel;
import com.nc.g49_smartcrm.model.Conversation;
import com.nc.g49_smartcrm.model.ConversationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findByContact_PhoneAndStatus(String phone, ConversationStatus status);

    Long countConversationsByUser_Id(Long id);

    List<Conversation> findAllByContact_Id(Long id);

    List<Conversation> findAllByUser_Id(Long id);

    @Query("""
    SELECT new com.nc.g49_smartcrm.dto.ChannelCountDto(c.channel, COUNT(c))
    FROM Conversation c
    WHERE c.user.id = :userId
    GROUP BY c.channel
    """)
    List<ChannelCountDto>countConversationsByChannelForUser(Long userId);

    Long countByUser_IdAndStatus(Long userId, ConversationStatus status);

    @Query("""
    SELECT new com.nc.g49_smartcrm.dto.CountPerDayDto(
        CAST(c.createdAt AS date), COUNT(c)
    )
    FROM Conversation c
    WHERE c.user.id = :userId
    GROUP BY CAST(c.createdAt AS date)
    ORDER BY CAST(c.createdAt AS date)
    """)
    List<CountPerDayDto> countConversationsCreatedPerDay(Long userId);

    @Query(value = """
    SELECT AVG(EXTRACT(EPOCH FROM (c.closed_at - c.created_at)))
    FROM tbl_conversations c
    WHERE c.user_id = :userId
    AND c.closed_at IS NOT NULL
    """, nativeQuery = true)
    Double avgResolutionTimeForUser(Long userId);

}
