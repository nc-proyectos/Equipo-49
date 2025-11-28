package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.TaskRequest;
import com.nc.g49_smartcrm.dto.TaskResponse;
import com.nc.g49_smartcrm.exception.TaskNotFoundException;
import com.nc.g49_smartcrm.mapper.TaskMapper;
import com.nc.g49_smartcrm.model.Task;
import com.nc.g49_smartcrm.model.User;
import com.nc.g49_smartcrm.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final QueueClient queueClient;
    private UserService userService;

    @Transactional
    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {

        Task task = taskMapper.toEntity(taskRequest);
        task.setCreatedAt(Instant.now());

        User user = userService.getCurrentUser();
        task.setUserId(user.getId());
        task.setStatus(Task.Status.PENDING);
        Task saved = taskRepository.save(task);

        queueClient.enqueueReminder(saved.getId(), saved.getUserId(), saved.getReminderAt());

        return taskMapper.toDto(saved);
    }

    @Override
    public List<TaskResponse> listPending(Long userId) {
        List<Task> tasks = taskRepository.findAllByUserIdAndStatus(userId, Task.Status.PENDING);
        return tasks.stream().map(taskMapper::toDto).toList();
    }

    @Transactional
    @Override
    public TaskResponse completeTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(Task.Status.COMPLETED);
        Task saved = taskRepository.save(task);
        return taskMapper.toDto(saved);
    }

    @Override
    public TaskResponse findById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toDto(task);
    }
}