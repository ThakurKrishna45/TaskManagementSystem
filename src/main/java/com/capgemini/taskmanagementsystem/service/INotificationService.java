package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.entity.Notification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


public interface INotificationService {
    //Crud operations
    Notification addNotification(Notification notification);
    Notification updateNotification(Notification notification);
    void  deleteNotification(Notification notification);

    //Queries
    Notification getNotificationById(Integer id);
    List<Notification> getAllNotificationsOfADate(LocalDateTime date);
    List<Notification> getAllNotificationsOfAProject(Integer projectId);
    List<Notification> getAllNotificationsBetweenARange(LocalDateTime start, LocalDateTime end);
    List<Notification> getallNotificationsOfAUser(Integer userId);




}
