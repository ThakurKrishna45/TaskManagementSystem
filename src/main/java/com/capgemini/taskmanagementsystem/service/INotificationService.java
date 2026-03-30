package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.dto.NotificationResponseDto;

import java.util.List;


public interface INotificationService {

    NotificationResponseDto getNotificationById(Integer id);
    List<NotificationResponseDto> getNRecentNotifications(Integer id , Integer n);


}
