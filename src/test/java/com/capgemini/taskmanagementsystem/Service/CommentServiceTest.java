package com.capgemini.taskmanagementsystem.Service;

import com.capgemini.taskmanagementsystem.dto.CommentResponseDto;
import com.capgemini.taskmanagementsystem.entity.Comment;
import com.capgemini.taskmanagementsystem.entity.Task;
import com.capgemini.taskmanagementsystem.entity.User;
import com.capgemini.taskmanagementsystem.repository.ICommentRepository;
import com.capgemini.taskmanagementsystem.repository.ITaskRepository;
import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.implementation.CommentServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private ICommentRepository commentRepository;

    @Mock
    private ITaskRepository taskRepository;

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Task task;
    private Comment comment;
    private User user;

    @BeforeEach
    void setUp() {

        task = new Task();
        task.setTaskID(1);

        user = new User();
        user.setUserId(10);
        user.setUsername("Parth");

        comment = new Comment();
        comment.setCommentId(101);
        comment.setTask(task);
        comment.setUser(user);
    }

    @Test
    void getAllCommentsByTaskId_Success() {

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        when(commentRepository.findByTaskTaskID(1))
                .thenReturn(Arrays.asList(comment));

        List<CommentResponseDto> result =
                commentService.getAllCommentsByTaskId(1);

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(taskRepository).findById(1);
        verify(commentRepository).findByTaskTaskID(1);
    }

    @Test
    void getAllCommentsByTaskId_Failure_TaskNotFound() {

        when(taskRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                commentService.getAllCommentsByTaskId(1)
        );

        assertEquals("Task not found with id: 1", ex.getMessage());

        verify(taskRepository).findById(1);
        verify(commentRepository, never()).findByTaskTaskID(anyInt());
    }

    @Test
    void getAllCommentsByUserOnTask_Success() {

        when(commentRepository.findByTaskTaskIDAndUserUserId(1, 10))
                .thenReturn(Arrays.asList(comment));

        List<CommentResponseDto> result =
                commentService.getAllCommentsByUserOnTask(1, 10);

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(commentRepository)
                .findByTaskTaskIDAndUserUserId(1, 10);
    }

    @Test
    void getAllCommentsByUserOnTask_Failure_EmptyList() {

        when(commentRepository.findByTaskTaskIDAndUserUserId(1, 10))
                .thenReturn(Collections.emptyList());

        List<CommentResponseDto> result =
                commentService.getAllCommentsByUserOnTask(1, 10);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(commentRepository)
                .findByTaskTaskIDAndUserUserId(1, 10);
    }
}