package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.TaskResponseDto;
import com.capgemini.taskmanagementsystem.exception.ResourceNotFoundException;
import com.capgemini.taskmanagementsystem.service.ITaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ITaskService taskService;
    private TaskResponseDto task1;
    private TaskResponseDto task2;

    @BeforeEach
    void setUp() {
        task1 = new TaskResponseDto();
        task1.setTaskName("Task1");
        task1.setDescription("Fix Bug");
        task1.setStatus("Pending");
        task1.setPriority("High");

        task2 = new TaskResponseDto();
        task2.setTaskName("Task2");
        task2.setDescription("Add feature x");
        task2.setStatus("Pending");
        task2.setPriority("High");
    }
    @Test
    void getTaskByPriorityAndStatus_Success() throws Exception{
        List<TaskResponseDto> tasks = Arrays.asList(task1, task2);
        Mockito.when(taskService.getTaskByPriorityAndStatus("High", "Pending"))
                .thenReturn(tasks);
        mockMvc.perform(get("/task/getTaskByPriorityAndStatus")
                        .param("priority", "High")
                        .param("status", "Pending")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].taskName").value("Task1"))
                .andExpect(jsonPath("$[1].taskName").value("Task2"));
    }
    @Test
    void getTaskByPriorityAndStatus_EmptyParams() throws Exception {
        Mockito.when(taskService.getTaskByPriorityAndStatus("", "Pending"))
                .thenThrow(new ResourceNotFoundException("Priority cannot be empty"));

        mockMvc.perform(get("/task/getTaskByPriorityAndStatus")
                        .param("priority", "")
                        .param("status", "Pending"))
                .andExpect(status().isNotFound());
    }
    @Test
    void testGetTaskByCategory() throws Exception {
        List<TaskResponseDto> tasks = Arrays.asList(task1);
        String category = "Development";

        Mockito.when(taskService.getTaskByCategory(category))
                .thenReturn(tasks);

        mockMvc.perform(get("/task/getTaskByCategory/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].taskName").value("Task1"));
    }
}
