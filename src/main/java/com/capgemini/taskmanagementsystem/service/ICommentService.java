package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.dto.CommentResponseDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ICommentService {

    List<CommentResponseDto> getAllCommentsByTaskId(@PathVariable("taskId") Integer taskId);
}
