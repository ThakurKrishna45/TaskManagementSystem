package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.dto.UserResponseDto;

public interface IUserService {
    public UserResponseDto getUserById(Integer userId);
    public UserResponseDto getUserByUsername(String username);
}
