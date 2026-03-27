package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.dto.TaskResponseDto;
import com.capgemini.taskmanagementsystem.entity.Task;
import com.capgemini.taskmanagementsystem.repository.ITaskRepository;
import com.capgemini.taskmanagementsystem.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {
    final private ITaskRepository taskRepository;
    @Override
    public List<TaskResponseDto> getTaskByPriorityAndStatus(String priority, String status) {
        List<Task> taskList= taskRepository.findByPriorityAndStatus(priority,status);
        return null;
    }
}
