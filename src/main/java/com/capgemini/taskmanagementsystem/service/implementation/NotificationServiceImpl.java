package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.dto.ErrorResponseDto;
import com.capgemini.taskmanagementsystem.dto.NotificationRequestDto;
import com.capgemini.taskmanagementsystem.dto.NotificationResponseDto;
import com.capgemini.taskmanagementsystem.entity.Notification;
import com.capgemini.taskmanagementsystem.exception.MissingFieldException;
import com.capgemini.taskmanagementsystem.exception.ResourceNotFoundException;
import com.capgemini.taskmanagementsystem.mapper.UserMapper;
import com.capgemini.taskmanagementsystem.repository.INotificationRepository;
import com.capgemini.taskmanagementsystem.service.INotificationService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements INotificationService {

    final private INotificationRepository notificationRepository;

    public NotificationResponseDto addNotification(NotificationRequestDto notification) {
        Notification notificationEntity = new Notification();

        notificationRepository.save(notificationEntity);
        return null;
    }

    public NotificationResponseDto updateNotification(NotificationRequestDto notification) throws MissingFieldException {
        Notification notificationEntity = UserMapper.notificationRequestDtoToNotification(notification);

        if (notificationRepository.existsById(notificationEntity.getNotificationID())) {
            notificationRepository.save(notificationEntity);
            NotificationResponseDto notificationResponseDto =  UserMapper.notificationToNotificationResponseDto(notificationEntity);;
            return notificationResponseDto;

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
            NotificationResponseDto notificationResponseDto = UserMapper.notificationToNotificationResponseDto(notification.get());
            return notificationResponseDto;
        }

        throw new ResourceNotFoundException("Notification not found with id: " + id);

    }


    @Override
    public List<NotificationResponseDto> getNRecentNotifications(Integer id, Integer n) {

        List<Notification> notifications = notificationRepository.findByUserUserID(id);
        if (notifications.isEmpty()) {
            throw new ResourceNotFoundException("No notifications found for user with id: " + id);
        }

        // Sort notifications by createdAt in descending order and get the top n
        List<NotificationResponseDto> recentNotifications = new ArrayList<>();
        notifications.stream()
                .sorted((n1, n2) -> n2.getCreatedAt().compareTo(n1.getCreatedAt()))
                .limit(n)
                .forEach(notification -> recentNotifications.add(UserMapper.notificationToNotificationResponseDto(notification)));

        return recentNotifications;
    }


}

