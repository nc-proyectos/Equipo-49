package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.TaskRequest;
import com.nc.g49_smartcrm.dto.TaskResponse;
import jakarta.transaction.Transactional;

import java.util.List;

public interface TaskService {
    @Transactional
    TaskResponse createTask(TaskRequest taskRequest);

    List<TaskResponse> listPending(Long userId);

    @Transactional
    TaskResponse completeTask(Long id);

    TaskResponse findById(Long id);
}
