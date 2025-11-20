package com.nc.g49_smartcrm.mapper;

import com.nc.g49_smartcrm.dto.ConversationResponse;
import com.nc.g49_smartcrm.model.Conversation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConversationMapper {

    ConversationResponse toDto(Conversation conversation);

}
