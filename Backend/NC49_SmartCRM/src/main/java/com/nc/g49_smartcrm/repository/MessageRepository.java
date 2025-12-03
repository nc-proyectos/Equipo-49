package com.nc.g49_smartcrm.repository;

import com.nc.g49_smartcrm.dto.ChannelCountDto;
import com.nc.g49_smartcrm.dto.CountPerDayDto;
import com.nc.g49_smartcrm.dto.MessageResponse;
import com.nc.g49_smartcrm.model.Message;
import com.nc.g49_smartcrm.model.MessageDirection;
import com.nc.g49_smartcrm.model.SenderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByConversationIdOrderByCreatedAtAsc(Long conversationId);

    @Query("""
    SELECT COUNT(m)
    FROM Message m
    WHERE m.conversation.user.id = :userId
    AND m.direction = :direction
    """)
    Long countMessagesByDirectionAndUser(Long userId, MessageDirection direction);

    @Query("""
    SELECT new com.nc.g49_smartcrm.dto.ChannelCountDto(c.channel, COUNT(m))
    FROM Message m
    JOIN m.conversation c
    WHERE c.user.id = :userId
    GROUP BY c.channel
    """)
    List<ChannelCountDto> countMessagesByChannelForUser(Long userId);

    @Query("""
    SELECT new com.nc.g49_smartcrm.dto.CountPerDayDto(
        CAST(m.createdAt AS date), COUNT(m)
    )
    FROM Message m
    WHERE m.conversation.user.id = :userId
    GROUP BY CAST(m.createdAt AS date)
    ORDER BY CAST(m.createdAt AS date)
    """)
    List<CountPerDayDto> countMessagesPerDayByUser(Long userId);

}
