package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.dto.CommentResponseDto;

import java.util.List;

public interface ICommentService {
    List<CommentResponseDto> getAllCommentsByTaskId(Integer taskId);
}
