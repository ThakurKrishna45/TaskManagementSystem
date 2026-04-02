package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.UserRequestDto;
import com.capgemini.taskmanagementsystem.dto.UserResponseDto;
import com.capgemini.taskmanagementsystem.exception.MissingFieldException;
import com.capgemini.taskmanagementsystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    IUserService userService;
    @GetMapping("findById/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer userId){
        return new ResponseEntity<UserResponseDto>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("findByUsername/{username}")
    public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<UserResponseDto>(userService.getUserByUsername(username),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> UpdateUser(@RequestParam Integer userId,@RequestBody UserRequestDto userRequestDto){
        if (userId==null || userRequestDto.isNull()) {
            throw new MissingFieldException("UserId can not be null");
        }
        return new ResponseEntity<UserResponseDto>(userService.updateUser(userId,userRequestDto),HttpStatus.OK);
    }
}