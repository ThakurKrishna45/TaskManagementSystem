package com.capgemini.taskmanagementsystem.mapper;

import com.capgemini.taskmanagementsystem.dto.TaskResponseDto;
import com.capgemini.taskmanagementsystem.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static TaskResponseDto taskToTaskResponseDto(Task task){
        TaskResponseDto dto=new TaskResponseDto();
        dto.setTaskName(task.getTaskName());
        if(task.getDescription()!=null)dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setPriority(task.getPriority());
        dto.setStatus(task.getStatus());
        dto.setProjectName(task.getProject().getProjectName());
        dto.setUsername(task.getUser().getUsername());
        return dto;
    }
}
