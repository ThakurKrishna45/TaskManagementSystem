package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.dto.ErrorResponseDto;
import com.capgemini.taskmanagementsystem.dto.NotificationResponseDto;
import com.capgemini.taskmanagementsystem.entity.Notification;
import com.capgemini.taskmanagementsystem.exception.MissingFieldException;
import com.capgemini.taskmanagementsystem.exception.ResourceNotFoundException;
import com.capgemini.taskmanagementsystem.repository.INotificationRepository;
import com.capgemini.taskmanagementsystem.service.INotificationService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements INotificationService {

    final private INotificationRepository notificationRepository;

    public NotificationResponseDto addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public NotificationResponseDto updateNotification(Notification notification) throws MissingFieldException {
        if (notificationRepository.existsById(notification.getNotificationID())) {
            return notificationRepository.save(notification);
        }

        //     //     //     // Add Exception handling for notification not found
        throw new MissingFieldException("Notification not found , unable to update");
    }

    public void deleteNotification(Notification notification) throws MissingFieldException {
        if (notificationRepository.existsById(notification.getNotificationID())) {
            notificationRepository.deleteById(notification.getNotificationID());
        }

        throw new MissingFieldException("Notification not found , unable to delete");

    }

    public NotificationResponseDto getNotificationById(Integer id){

        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            return notification.get();
        }

        throw new ResourceNotFoundException("Notification not found with id: " + id);

    }

    @Override
    public List<NotificationResponseDto> getAllNotificationsOfADate(LocalDateTime date) {
        List<Notification> notifications = notificationRepository.findAllNotificationsByCreatedAt(date);
        if(notifications==null) {
            throw new ResourceNotFoundException("No notifications found for the given date: " + date);
        }

        return notifications;
    }

    @Override
    public List<NotificationResponseDto> getAllNotificationsOfAProject(Integer id) {
        List<Notification> notifications = notificationRepository.getAllNotificationsOfAProject(id);
        if(notifications==null) {
            throw new ResourceNotFoundException("No notifications found for the given project id: " + id );
        }

        return notifications;
    }

    @Override
    public List<NotificationResponseDto> getAllNotificationsBetweenARange(LocalDateTime start, LocalDateTime end) {
        List<Notification> notifications = notificationRepository.findAllNotificationsBetweenDates(start,end);
        if(notifications==null) {
            throw new ResourceNotFoundException("No notifications found for the given project between this date range");
        }

        return notifications;
    }

    @Override
    public List<NotificationResponseDto> getallNotificationsOfAUser(Integer id) {
        List<Notification> notifications = notificationRepository.findAllNotificationsByUserID(id);
         if(notifications==null) {
            throw new ResourceNotFoundException("No notifications found for user with user id: " + id);
        }

        return notifications;
    }

    @Override
    public List<NotificationResponseDto> getNRecentNotifications(Integer id, Integer n) {
        return List.of();
    }


}

