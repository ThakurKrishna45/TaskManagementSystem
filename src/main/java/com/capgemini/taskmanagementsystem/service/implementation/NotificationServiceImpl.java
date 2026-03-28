package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.dto.ErrorResponseDto;
import com.capgemini.taskmanagementsystem.entity.Notification;
import com.capgemini.taskmanagementsystem.exception.notification.NotificationNotFoundException;
import com.capgemini.taskmanagementsystem.repository.INotificationRepository;
import com.capgemini.taskmanagementsystem.service.INotificationService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class NotificationServiceImpl implements INotificationService {

    INotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(INotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Notification notification) {
        if (notificationRepository.existsById(notification.getNotificationID())) {
            return notificationRepository.save(notification);
        }

        //     //     //     // Add Exception handling for notification not found
        throw new MissingFieldException("Notification not found , unable to update");
    }

    public void deleteNotification(Notification notification) {
        if (notificationRepository.existsById(notification.getNotificationID())) {
            notificationRepository.deleteById(notification.getNotificationID());
        }

        throw new MissingFieldException("Notification not found , unable to delete");

    }

    public Notification getNotificationById(Integer id){

        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            return notification.get();
        }

        throw ResourceNotFoundException("Notification not found with id: " + id);

    }

    @Override
    public List<Notification> getAllNotificationsOfADate(LocalDateTime date) {
        List<Notification> notifications = notificationRepository.findAllNotificationsByCreatedAt(date);
        if(notifications==null) {
            throw ResourceNotFoundException("No notifications found for the given date: " + date);
        }

        return notifications;
    }

    @Override
    public List<Notification> getAllNotificationsOfAProject(Integer id) {
        List<Notification> notifications = notificationRepository.getAllNotificationsOfAProject(id);
        if(notifications==null) {
            throw ResourceNotFoundException("No notifications found for the given project id: " + id );
        }

        return notifications;
    }

    @Override
    public List<Notification> getAllNotificationsBetweenARange(LocalDateTime start, LocalDateTime end) {
        List<Notification> notifications = notificationRepository.findAllNotificationsBetweenDates(start,end);
        if(notifications==null) {
            throw ResourceNotFoundException("No notifications found for the given project between this date range");
        }

        return notifications;
    }

    @Override
    public List<Notification> getallNotificationsOfAUser(Integer id) {
        List<Notification> notifications = notificationRepository.findAllNotificationsByUserID(id);
         if(notifications==null) {
            throw ResourceNotFoundException("No notifications found for user with user id: " + id);
        }

        return notifications;
    }


}