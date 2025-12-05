package com.nc.g49_smartcrm.mapper;

import com.nc.g49_smartcrm.dto.UserRequest;
import com.nc.g49_smartcrm.dto.UserResponse;
import com.nc.g49_smartcrm.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(UserRequest userRequest);

    UserResponse toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntityFromRequest(UserRequest userRequest, @MappingTarget User user);
}
