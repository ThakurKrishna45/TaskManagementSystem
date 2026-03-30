package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.dto.TaskResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITaskService {
    List<TaskResponseDto> getTaskByPriorityAndStatus(String priority, String status);

    List<TaskResponseDto> getTaskByCategory(String category);
}
