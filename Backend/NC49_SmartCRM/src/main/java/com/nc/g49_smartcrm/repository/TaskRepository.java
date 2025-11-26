package com.nc.g49_smartcrm.repository;

import com.nc.g49_smartcrm.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserIdAndStatus(Long userId, Task.Status status);
}
