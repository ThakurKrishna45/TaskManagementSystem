package com.capgemini.taskmanagementsystem.mapper;

import com.capgemini.taskmanagementsystem.dto.CommentResponseDto;
import com.capgemini.taskmanagementsystem.entity.Comment;

public class Mapper {
    public static CommentResponseDto mapCommentToResponseDto(Comment comment, CommentResponseDto dto) {

        dto.setCommentId(comment.getCommentId());
        dto.setText(comment.getText());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setTaskId(comment.getTask().getTaskID());
        dto.setUsername(comment.getUser().getUsername());

        return dto;
    }
}
