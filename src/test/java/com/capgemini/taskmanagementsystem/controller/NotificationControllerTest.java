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

}