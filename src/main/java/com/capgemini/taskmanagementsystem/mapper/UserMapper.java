package com.capgemini.taskmanagementsystem.mapper;

import com.capgemini.taskmanagementsystem.dto.NotificationRequestDto;
import com.capgemini.taskmanagementsystem.dto.NotificationResponseDto;
import com.capgemini.taskmanagementsystem.entity.Notification;

public class UserMapper {

    public static NotificationResponseDto notificationToNotificationResponseDto  (Notification notification){
        NotificationResponseDto notificationResponseDto = new NotificationResponseDto();
        notificationResponseDto.setText(notification.getText());
        notificationResponseDto.setCreatedAt(notification.getCreatedAt());
        notificationResponseDto.setNotificationID(notification.getNotificationID());
        return notificationResponseDto;
    }

    public static Notification notificationRequestDtoToNotification(NotificationRequestDto notificationRequestDto){
        Notification notification = new Notification();
        notification.setUser(notificationRequestDto.getUser());
        notification.setText(notificationRequestDto.getText());
        notification.setCreatedAt(notificationRequestDto.getCreatedAt());
        return notification;
    }

import com.capgemini.taskmanagementsystem.dto.CommentResponseDto;
import com.capgemini.taskmanagementsystem.entity.Comment;

public class UserMapper {
    public static CommentResponseDto mapCommentToResponseDto(Comment comment, CommentResponseDto dto) {

        dto.setCommentId(comment.getCommentId());
        dto.setText(comment.getText());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setTaskId(comment.getTask().getTaskID());
        dto.setUsername(comment.getUser().getUsername());

        return dto;
    }
}
