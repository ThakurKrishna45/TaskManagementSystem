package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.dto.NotificationResponseDto;
import com.capgemini.taskmanagementsystem.entity.Notification;
import com.capgemini.taskmanagementsystem.exception.MissingFieldException;

import java.time.LocalDateTime;
import java.util.List;


public interface INotificationService {
    //Crud operations
    NotificationResponseDto addNotification(Notification notification);
    NotificationResponseDto updateNotification(Notification notification) throws MissingFieldException;
    void  deleteNotification(Notification notification) throws MissingFieldException;

    //Queries
    Notification getNotificationById(Integer id);
    List<NotificationResponseDto> getAllNotificationsOfAProject(Integer projectId);
    List<NotificationResponseDto> getAllNotificationsBetweenARange(LocalDateTime start, LocalDateTime end);
    List<NotificationResponseDto> getallNotificationsOfAUser(Integer userId);
    List<NotificationResponseDto> getNRecentNotifications(Integer id , Integer n);



}
