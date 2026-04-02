package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.LoginRequestDto;
import com.capgemini.taskmanagementsystem.dto.UserResponseDto;
import com.capgemini.taskmanagementsystem.exception.MissingFieldException;
import com.capgemini.taskmanagementsystem.exception.UnauthorizedException;
import com.capgemini.taskmanagementsystem.service.ILoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto user, HttpSession httpSession){
        if (httpSession.getAttribute("found") != null){
            throw new UnauthorizedException("Already Login");
        }
        if (user.getUsername()==null || user.getPassword()==null){
            throw new MissingFieldException("Username and Password cann't be null");
        }
        if (loginService.validateUser(user.getUsername(),user.getPassword())){
            httpSession.setAttribute("found",user.getUsername());
        }
    }

    @GetMapping("/logout")
    public void logout(HttpSession httpSession){
        if (httpSession.getAttribute("found") != null){
            httpSession.invalidate();
        }
        else{
            throw new UnauthorizedException("First Login for Logout");
        }
    }
}
