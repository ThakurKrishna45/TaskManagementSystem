package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.entity.User;
import com.capgemini.taskmanagementsystem.exception.ResourceNotFoundException;
import com.capgemini.taskmanagementsystem.mapper.Mapper;
import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTesting {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    IUserService userService;

    @MockitoBean
    IUserRepository userRepository;

    @Test
    void getByIdPositiveTest() throws Exception{
        User user = new User("vinay123","vinay123","vinay@gmail.com","Vinay Kumar");
        when(userService.getUserById(101)).thenReturn(Mapper.userToDto(user));
        mockMvc.perform(get("/user/findById/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("vinay123"));
    }

    @Test
    void getByIdNegativeTest() throws Exception{
        when(userService.getUserById(101)).thenThrow(new ResourceNotFoundException("User Not Found"));
        mockMvc.perform(get("/user/findById/101"))
                .andExpect(status().isNotFound());
    }


}
