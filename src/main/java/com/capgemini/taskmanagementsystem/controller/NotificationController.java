package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.NotificationRequestDto;
import com.capgemini.taskmanagementsystem.dto.NotificationResponseDto;
import com.capgemini.taskmanagementsystem.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    INotificationService notificationService;

    @Autowired
    public NotificationController(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/getnotif/{id}")
    public ResponseEntity<NotificationResponseDto> getNotificationById(@PathVariable Integer id) {
        return new ResponseEntity<NotificationResponseDto>(notificationService.getNotificationById(id), HttpStatus.OK);
    }

    @GetMapping("/getnrecentnotif/{id}/{n}")
    public ResponseEntity<List<NotificationResponseDto>> getNRecentNotifications(@PathVariable Integer id, @PathVariable Integer n) {
        return new ResponseEntity<List<NotificationResponseDto>>(notificationService.getNRecentNotifications(id, n),HttpStatus.OK);
    }

}
