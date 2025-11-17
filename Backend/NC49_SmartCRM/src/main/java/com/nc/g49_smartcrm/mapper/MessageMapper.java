package com.nc.g49_smartcrm.mapper;

import com.nc.g49_smartcrm.dto.MessageRequest;
import com.nc.g49_smartcrm.dto.MessageResponse;
import com.nc.g49_smartcrm.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    MessageResponse toDto(Message message);

    Message toEntity(MessageRequest messageRequest);
}
