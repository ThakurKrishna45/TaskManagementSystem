package com.capgemini.taskmanagementsystem.Service;

import com.capgemini.taskmanagementsystem.dto.TaskResponseDto;
import com.capgemini.taskmanagementsystem.entity.Project;
import com.capgemini.taskmanagementsystem.entity.Task;
import com.capgemini.taskmanagementsystem.entity.User;
import com.capgemini.taskmanagementsystem.exception.MissingFieldException;
import com.capgemini.taskmanagementsystem.repository.ITaskRepository;
import com.capgemini.taskmanagementsystem.service.implementation.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private ITaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task1;

    @BeforeEach
    void setUp() {
        Project mockProject = new Project();
        mockProject.setProjectName("Capgi Project");
        User mockUser = new User();
        mockUser.setUsername("test_user");
        task1 = new Task();
        task1.setTaskName("Test Task");
        task1.setPriority("High");
        task1.setStatus("Pending");
        task1.setProject(mockProject);
        task1.setUser(mockUser);
    }

    @Test
    void getTaskByPriorityAndStatus_Success() {
        when(taskRepository.findByPriorityAndStatus("High", "Pending"))
                .thenReturn(Arrays.asList(task1));

        List<TaskResponseDto> result = taskService.getTaskByPriorityAndStatus("High", "Pending");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Task", result.get(0).getTaskName());
        assertEquals("test_user", result.get(0).getUsername());

        verify(taskRepository, times(1)).findByPriorityAndStatus("High", "Pending");
    }

    @Test
    void getTaskByPriorityAndStatus_ThrowsException() {
        MissingFieldException exception = assertThrows(MissingFieldException.class, () -> {
            taskService.getTaskByPriorityAndStatus("", "Pending");
        });

        assertEquals("Priority and status both is required", exception.getMessage());
        verifyNoInteractions(taskRepository);
    }


    @Test
    void getTaskByCategory_Success() {
        when(taskRepository.findByCategories_CategoryName("Work"))
                .thenReturn(Arrays.asList(task1));

        List<TaskResponseDto> result = taskService.getTaskByCategory("Work");

        assertEquals(1, result.size());
        assertEquals("Test Task", result.get(0).getTaskName());
        assertEquals("test_user", result.get(0).getUsername());
        verify(taskRepository, times(1)).findByCategories_CategoryName("Work");
    }

    @Test
    void getTaskByCategory_ThrowsException() {
        assertThrows(MissingFieldException.class, () -> {
            taskService.getTaskByCategory("   ");
        });

        verifyNoInteractions(taskRepository);
    }
}
