package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.TaskResponseDto;
import com.capgemini.taskmanagementsystem.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    final private ITaskService taskService;
    @GetMapping("/getTaskByPriorityAndStatus")
    public ResponseEntity<TaskResponseDto> getTaskByPriorityAndStatus(@RequestParam String priority,
                                                                      @RequestParam String status){
        List<TaskResponseDto> taskResponseDto=taskService.getTaskByPriorityAndStatus(priority,status);
        return null;
    }
}
