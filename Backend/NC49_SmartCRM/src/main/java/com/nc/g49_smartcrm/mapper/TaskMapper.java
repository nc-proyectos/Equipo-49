package com.nc.g49_smartcrm.mapper;

import com.nc.g49_smartcrm.dto.TaskRequest;
import com.nc.g49_smartcrm.dto.TaskResponse;
import com.nc.g49_smartcrm.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    Task toEntity(TaskRequest taskRequest);

    TaskResponse toDto(Task task);
}
