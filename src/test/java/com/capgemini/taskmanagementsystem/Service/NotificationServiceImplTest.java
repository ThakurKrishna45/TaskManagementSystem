package com.capgemini.taskmanagementsystem.Service;

import com.capgemini.taskmanagementsystem.dto.NotificationResponseDto;
import com.capgemini.taskmanagementsystem.entity.Notification;
import com.capgemini.taskmanagementsystem.exception.ResourceNotFoundException;
import com.capgemini.taskmanagementsystem.repository.INotificationRepository;
import com.capgemini.taskmanagementsystem.service.INotificationService;
import com.capgemini.taskmanagementsystem.service.implementation.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private INotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    private Notification notification;

     @BeforeEach
    void setUp() {
         notification = new Notification();
         notification.setNotificationID(1);
         notification.setText("Test Notification");
         notification.setCreatedAt(LocalDateTime.parse("2026-02-27T00:00:00"));
     }

     @Test
    public void testGetNotificationById_Success() throws Exception {
         when(notificationRepository.findById(1))
                 .thenReturn(Optional.ofNullable(notification));

         NotificationResponseDto result = notificationService.getNotificationById(1);
          assertNotNull(result);
          assertEquals("Test Notification", result.getText());
          assertEquals(LocalDateTime.parse("2026-02-27T00:00:00"), result.getCreatedAt());
     }

     @Test
    public void testGetNotificationById_NotFound() {
        int invalidId = 999;

        when(notificationRepository.findById(invalidId)).thenReturn(Optional.empty());

        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            notificationService.getNotificationById(invalidId);
        });

        assertEquals("Notification not found with id: "+invalidId, exception.getMessage());
    }

    @Test
public void testGetNRecentNotifications_Success() {
    Notification n1 = new Notification();
    n1.setCreatedAt(LocalDateTime.parse("2026-02-20T10:00:00"));
    n1.setText("Oldest");

    Notification n2 = new Notification();
    n2.setCreatedAt(LocalDateTime.parse("2026-02-25T10:00:00"));
    n2.setText("Newest");

    Notification n3 = new Notification();
    n3.setCreatedAt(LocalDateTime.parse("2026-02-22T10:00:00"));
    n3.setText("Middle");

    List<Notification> mockList = List.of(n1, n2, n3);

    when(notificationRepository.findByUserUserID(1)).thenReturn(mockList);

    List<NotificationResponseDto> result = notificationService.getNRecentNotifications(1, 2);

    assertNotNull(result);
    assertEquals(2, result.size(), "Should only return 'n' notifications");

    assertEquals("Newest", result.get(0).getText());
    assertEquals("Middle", result.get(1).getText());
}

@Test
public void testGetNRecentNotifications_UserNotFound() {
    int userId = 1;
    when(notificationRepository.findByUserUserID(userId)).thenReturn(new ArrayList<>());

    ResourceNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
        ResourceNotFoundException.class,
        () -> notificationService.getNRecentNotifications(userId, 5)
    );

    assertEquals("No notifications found for user with id: " + userId, exception.getMessage());
}



}