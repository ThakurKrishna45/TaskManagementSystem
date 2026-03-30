package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.dto.NotificationRequestDto;
import com.capgemini.taskmanagementsystem.dto.NotificationResponseDto;
import com.capgemini.taskmanagementsystem.entity.Notification;
import com.capgemini.taskmanagementsystem.exception.MissingFieldException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


public interface INotificationService {
    //Crud operations
    NotificationResponseDto addNotification(NotificationRequestDto notification);
    NotificationResponseDto updateNotification(NotificationRequestDto notification) throws MissingFieldException;
    void  deleteNotification(Notification notification) throws MissingFieldException;

    //Queries
    NotificationResponseDto getNotificationById(Integer id);
    List<NotificationResponseDto> getNRecentNotifications(Integer id , Integer n);




}
