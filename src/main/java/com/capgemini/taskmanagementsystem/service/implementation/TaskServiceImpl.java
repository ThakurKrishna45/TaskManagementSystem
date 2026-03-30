package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.dto.TaskResponseDto;
import com.capgemini.taskmanagementsystem.entity.Task;
import com.capgemini.taskmanagementsystem.exception.MissingFieldException;
import com.capgemini.taskmanagementsystem.mapper.Mapper;
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
        if(priority==null||priority.trim()==""||status==null||status.trim()=="")
            throw new MissingFieldException("Priority and status both is required");
        List<Task> taskList= taskRepository.findByPriorityAndStatus(priority,status);
        return taskList.stream().map(task -> Mapper.taskToTaskResponseDto(task)).toList();
    }

    @Override
    public List<TaskResponseDto> getTaskByCategory(String category) {
        if(category==null||category.trim()=="")throw new MissingFieldException("Category is required");
        List<Task> taskList= taskRepository.findByCategories_CategoryName(category);
        return taskList.stream().map(task -> Mapper.taskToTaskResponseDto(task)).toList();
    }
}
