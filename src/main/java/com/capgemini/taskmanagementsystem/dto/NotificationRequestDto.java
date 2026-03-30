package com.capgemini.taskmanagementsystem.dto;


import com.capgemini.taskmanagementsystem.entity.Notification;
import com.capgemini.taskmanagementsystem.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationRequestDto {
    private String text;
    private LocalDateTime createdAt;
    private User user;

    NotificationRequestDto() {
    }
}
