package com.nc.g49_smartcrm.mapper;

import com.nc.g49_smartcrm.dto.ConversationResponse;
import com.nc.g49_smartcrm.model.Conversation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConversationMapper {

    @Mapping(target = "ownerId", source = "user.id")
    @Mapping(target = "startedAt", source = "createdAt")
    @Mapping(target = "contactId", source = "contact.id")
    ConversationResponse toDto(Conversation conversation);

}
