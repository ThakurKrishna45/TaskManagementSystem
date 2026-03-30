package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.TaskResponseDto;
import com.capgemini.taskmanagementsystem.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    final private ITaskService taskService;
    @GetMapping("/getTaskByPriorityAndStatus")
    public ResponseEntity<List<TaskResponseDto>> getTaskByPriorityAndStatus(@RequestParam String priority,
                                                                      @RequestParam String status){
        List<TaskResponseDto> taskResponseDto=taskService.getTaskByPriorityAndStatus(priority,status);
        return ResponseEntity.ok(taskResponseDto);
    }
    @GetMapping("/getTaskByCategory")
    public ResponseEntity<List<TaskResponseDto>> getTaskByCategory(@PathVariable String category){
        List<TaskResponseDto> taskResponseDto=taskService.getTaskByCategory(category);
        return ResponseEntity.ok(taskResponseDto);
    }
}
