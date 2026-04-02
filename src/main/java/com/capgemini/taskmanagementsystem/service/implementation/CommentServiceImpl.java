package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.dto.CommentResponseDto;
import com.capgemini.taskmanagementsystem.entity.Comment;
import com.capgemini.taskmanagementsystem.entity.Task;
import com.capgemini.taskmanagementsystem.mapper.Mapper;
import com.capgemini.taskmanagementsystem.repository.ICommentRepository;
import com.capgemini.taskmanagementsystem.repository.ITaskRepository;
import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepository commentRepository;
    @Autowired
    ITaskRepository taskRepository;
    @Autowired
    IUserRepository userRepository;

    @Override
    public List<CommentResponseDto> getAllCommentsByTaskId(Integer taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        List<Comment> comments = commentRepository.findByTaskTaskID(taskId);

        List<CommentResponseDto> responseList = new ArrayList<>();

        for (Comment comment : comments) {
            CommentResponseDto dto = new CommentResponseDto();
            dto = Mapper.mapCommentToResponseDto(comment, dto);
            responseList.add(dto);
        }

        return responseList;

    }

    @Override
    public List<CommentResponseDto> getAllCommentsByUserOnTask(Integer taskId, Integer userId) {

        List<Comment> comments =
                commentRepository.findByTaskTaskIDAndUserUserId(taskId, userId);

        return comments.stream()
                .map(Mapper::mapCommentToResponseDto)
                .collect(Collectors.toList());
    }
}
