package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.NotificationResponseDto;
import com.capgemini.taskmanagementsystem.service.INotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private INotificationService notificationService;

    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        session.setAttribute("found", "test");
    }

    @Test
   public void testGetNotificationById_Success() throws Exception {
        NotificationResponseDto notificationResponseDto = new NotificationResponseDto();
        notificationResponseDto.setNotificationID(1);
        notificationResponseDto.setText("Test Notification");
        notificationResponseDto.setCreatedAt(LocalDateTime.parse("2024-06-01T00:00:00"));

        Mockito.when(notificationService.getNotificationById(1)).thenReturn(notificationResponseDto);

        mockMvc.perform(get("/notification/getnotif/1")
       .contentType(MediaType.APPLICATION_JSON)
       .session(session))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.notificationID").value(1))
                .andExpect((ResultMatcher) jsonPath("$.text").value("Test Notification"))
                .andExpect((ResultMatcher) jsonPath("$.createdAt").value("2024-06-01T00:00:00"));
    }



    @Test
    public void testGetNotificationById_NotFound() throws Exception {

        Mockito.when(notificationService.getNotificationById(999))
               .thenThrow(new RuntimeException("Notification not found"));

        mockMvc.perform(get("/notification/getnotif/999")
                .session(session))
                .andExpect(status().isExpectationFailed());

    }

    @Test
    public void testGetNRecentNotifications_Success() throws Exception {
        // Arrange: Create dummy data
        NotificationResponseDto dto1 = new NotificationResponseDto(1, "Notif 1", LocalDateTime.now(), "John Doe");
        NotificationResponseDto dto2 = new NotificationResponseDto(2, "Notif 2", LocalDateTime.now(), "John Doe");
        List<NotificationResponseDto> responseList = Arrays.asList(dto1, dto2);

        // Mock the service call
        // Path matches: @GetMapping("/getnrecentnotif/{id}/{n}")
        when(notificationService.getNRecentNotifications(1, 2)).thenReturn(responseList);

        // Act & Assert
        mockMvc.perform(get("/getnrecentnotif/1/2")
                .contentType(MediaType.APPLICATION_JSON)
                .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].notificationID").value(1))
                .andExpect(jsonPath("$[1].notificationID").value(2))
                .andExpect(jsonPath("$[0].text").value("Notif 1"));
    }

    @Test
    public void testGetNRecentNotifications_NotFound() throws Exception {
        // Arrange: Mock service throwing an exception (assuming your GlobalExceptionHandler maps this to 404 or 417)
        when(notificationService.getNRecentNotifications(99, 5))
                .thenThrow(new RuntimeException("No notifications found"));

        // Act & Assert
        mockMvc.perform(get("/getnrecentnotif/99/5")
                .session(session))
                // Change the status check to match your actual exception handler (e.g., .isNotFound() or .isExpectationFailed())
                .andExpect(status().isInternalServerError());
    }

}