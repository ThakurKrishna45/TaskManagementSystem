package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.entity.Notification;
import com.capgemini.taskmanagementsystem.exception.MissingFieldException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


public interface INotificationService {
    //Crud operations
    Notification addNotification(Notification notification);
    Notification updateNotification(Notification notification) throws MissingFieldException;
    void  deleteNotification(Notification notification) throws MissingFieldException;

    //Queries
    Notification getNotificationById(Integer id);
    List<Notification> getAllNotificationsOfADate(LocalDateTime date);
    List<Notification> getAllNotificationsOfAProject(Integer projectId);
    List<Notification> getAllNotificationsBetweenARange(LocalDateTime start, LocalDateTime end);
    List<Notification> getallNotificationsOfAUser(Integer userId);




}
