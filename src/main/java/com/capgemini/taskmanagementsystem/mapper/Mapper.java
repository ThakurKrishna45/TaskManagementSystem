package com.capgemini.taskmanagementsystem.mapper;

import com.capgemini.taskmanagementsystem.dto.*;
import com.capgemini.taskmanagementsystem.entity.*;
import com.capgemini.taskmanagementsystem.entity.Comment;
import com.capgemini.taskmanagementsystem.entity.Notification;
import com.capgemini.taskmanagementsystem.entity.Project;
import com.capgemini.taskmanagementsystem.entity.User;

public class Mapper {

    public static UserResponseDto userToDto(User user){
        return new UserResponseDto(user.getUserId(),user.getUsername(),user.getEmail(),user.getFullName());
    }
    public static User dtoToUser(UserRequestDto userRequestDto){
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setFullName(userRequestDto.getFullName());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }

    public static ProjectResponseDto projectToDto(Project project){
        return new ProjectResponseDto(project.getProjectName(),project.getDescription(),project.getStartDate(),project.getEndDate());
    }

    public static Project dtoToProject(ProjectRequestDto projectRequestDto) {
        Project project = new Project();
        project.setProjectName(projectRequestDto.getProjectName());
        project.setDescription(projectRequestDto.getDescription());
        project.setStartDate(projectRequestDto.getStartDate());
        project.setEndDate(projectRequestDto.getEndDate());
        return project;
    }

    public static NotificationResponseDto notificationToNotificationResponseDto(Notification notification) {
        NotificationResponseDto notificationResponseDto = new NotificationResponseDto();
        notificationResponseDto.setText(notification.getText());
        notificationResponseDto.setCreatedAt(notification.getCreatedAt());
        notificationResponseDto.setNotificationID(notification.getNotificationID());
        return notificationResponseDto;
    }

    public static Notification notificationRequestDtoToNotification(NotificationRequestDto notificationRequestDto) {
        Notification notification = new Notification();
        notification.setUser(notificationRequestDto.getUser());
        notification.setText(notificationRequestDto.getText());
        notification.setCreatedAt(notificationRequestDto.getCreatedAt());
        return notification;
    }

    public static CommentResponseDto mapCommentToResponseDto(Comment comment, CommentResponseDto dto) {

        dto.setCommentId(comment.getCommentId());
        dto.setText(comment.getText());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setTaskId(comment.getTask().getTaskID());
        dto.setUsername(comment.getUser().getUsername());
        dto.setUserId(comment.getUser().getUserId());

        return dto;
    }

    public static TaskResponseDto taskToTaskResponseDto(Task task){
        TaskResponseDto dto=new TaskResponseDto();
        dto.setTaskName(task.getTaskName());
        if(task.getDescription()!=null)dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setPriority(task.getPriority());
        dto.setStatus(task.getStatus());
        dto.setProjectName(task.getProject().getProjectName());
        dto.setUsername(task.getUser().getUsername());
        return dto;
    }

    public static CommentResponseDto mapCommentToResponseDto(Comment comment) {

        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .text(comment.getText())
                .createdAt(comment.getCreatedAt())
                .taskId(comment.getTask().getTaskID())
                .userId(comment.getUser().getUserId())
                .username(comment.getUser().getUsername())
                .build();
    }
}
