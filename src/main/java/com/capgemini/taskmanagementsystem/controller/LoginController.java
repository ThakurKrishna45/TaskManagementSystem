package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.exception.UnauthorizedException;
import com.capgemini.taskmanagementsystem.service.ILoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/login/{username}/{password}")
    public void login(@PathVariable String username, @PathVariable String password, HttpSession httpSession){
        if (httpSession.getAttribute("found") != null){
            throw new UnauthorizedException("Already Login");
        }
        if (loginService.validateUser(username,password)){
            httpSession.setAttribute("found",username);
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
