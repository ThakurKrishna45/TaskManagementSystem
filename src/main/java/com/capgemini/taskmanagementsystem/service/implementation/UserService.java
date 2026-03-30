package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.dto.UserResponseDto;
import com.capgemini.taskmanagementsystem.entity.User;
import com.capgemini.taskmanagementsystem.exception.ResourceNotFoundException;
import com.capgemini.taskmanagementsystem.mapper.Mapper;
import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserResponseDto getUserById(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            return Mapper.userToDto(optionalUser.get());
        }
        else{
            throw new ResourceNotFoundException("User Not Found Exception");
        }
    }
}
