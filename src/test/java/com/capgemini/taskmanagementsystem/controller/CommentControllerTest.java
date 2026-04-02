package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.CommentResponseDto;
import com.capgemini.taskmanagementsystem.service.ICommentService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ICommentService commentService;

    // ✅ Test 1: getAllCommentsByTaskId
    @Test
    public void testGetAllCommentsByTaskId_Success() throws Exception {

        CommentResponseDto comment1 = new CommentResponseDto();
        CommentResponseDto comment2 = new CommentResponseDto();

        List<CommentResponseDto> mockList = Arrays.asList(comment1, comment2);

        Mockito.when(commentService.getAllCommentsByTaskId(1))
                .thenReturn(mockList);

        mockMvc.perform(get("/comment/getallcommentsbytaskid/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetAllCommentsByTaskId_Failure() throws Exception {

        Mockito.when(commentService.getAllCommentsByTaskId(1))
                .thenThrow(new RuntimeException("Task not found"));

        mockMvc.perform(get("/comment/getallcommentsbytaskid/1"))
                .andExpect(status().isExpectationFailed());
    }

    // ✅ Test 2: getAllCommentsByUserOnTask
    @Test
    public void testGetAllCommentsByUserOnTask_Success() throws Exception {

        CommentResponseDto comment1 = new CommentResponseDto();
        CommentResponseDto comment2 = new CommentResponseDto();

        List<CommentResponseDto> mockList = Arrays.asList(comment1, comment2);

        Mockito.when(commentService.getAllCommentsByUserOnTask(1, 101))
                .thenReturn(mockList);

        mockMvc.perform(get("/comment/getAllCommentsByUserOnTask")
                        .param("taskId", "1")
                        .param("userId", "101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetAllCommentsByUserOnTask_Failure() throws Exception {

        Mockito.when(commentService.getAllCommentsByUserOnTask(1, 101))
                .thenThrow(new RuntimeException("No comments found"));

        mockMvc.perform(get("/comment/getAllCommentsByUserOnTask")
                        .param("taskId", "1")
                        .param("userId", "101"))
                .andExpect(status().isExpectationFailed());
    }
}