package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.dto.TaskResponseDto;

import java.util.List;

public interface ITaskService {
    List<TaskResponseDto> getTaskByPriorityAndStatus(String priority, String status);
}
