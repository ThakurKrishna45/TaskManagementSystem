package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.CommentResponseDto;
import com.capgemini.taskmanagementsystem.service.ICommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping("/getallcommentsbytaskid/{taskId}")
    public List<CommentResponseDto> getAllCommentsByTaskId(@PathVariable Integer taskId) {

        return commentService.getAllCommentsByTaskId(taskId);
    }

    @GetMapping("/getAllCommentsByUserOnTask")
    public List<CommentResponseDto> getAllCommentsByUserOnTask(
            @RequestParam Integer taskId,
            @RequestParam Integer userId) {

        return commentService.getAllCommentsByUserOnTask(taskId, userId);
    }
}