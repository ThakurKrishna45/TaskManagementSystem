package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.dto.UserRequestDto;
import com.capgemini.taskmanagementsystem.dto.UserResponseDto;
import com.capgemini.taskmanagementsystem.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface IUserService {
    public UserResponseDto getUserById(Integer userId);
    public UserResponseDto getUserByUsername(String username);
    public UserResponseDto updateUser(Integer userId,UserRequestDto userRequestDto);
}
