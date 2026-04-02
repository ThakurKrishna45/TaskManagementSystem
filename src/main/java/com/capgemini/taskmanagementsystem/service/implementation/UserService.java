package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.dto.UserRequestDto;
import com.capgemini.taskmanagementsystem.dto.UserResponseDto;
import com.capgemini.taskmanagementsystem.entity.User;
import com.capgemini.taskmanagementsystem.exception.ResourceNotFoundException;
import com.capgemini.taskmanagementsystem.mapper.Mapper;
import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public UserResponseDto getUserByUsername(String username){
        List<User> userList = userRepository.findByUsername(username);
        if (userList ==null){
            throw new ResourceNotFoundException("No User Found for this Username: "+username);
        }
        else{
            return Mapper.userToDto(userList.get(0));
        }
    }

    @Override
    public UserResponseDto updateUser(Integer userId,UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setEmail(userRequestDto.getEmail());
            user.setPassword(userRequestDto.getPassword());
            user.setFullName(userRequestDto.getFullName());
            user.setUsername(userRequestDto.getUsername());

            userRepository.saveAndFlush(user);
            return Mapper.userToDto(user);
        }
        throw new ResourceNotFoundException("No user Found");

    }


}
