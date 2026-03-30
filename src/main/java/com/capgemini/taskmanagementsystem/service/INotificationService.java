package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.dto.NotificationRequestDto;
import com.capgemini.taskmanagementsystem.dto.NotificationResponseDto;

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
