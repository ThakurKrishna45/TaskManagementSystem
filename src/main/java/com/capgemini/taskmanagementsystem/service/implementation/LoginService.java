package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.entity.User;
import com.capgemini.taskmanagementsystem.exception.UnauthorizedException;
import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.ILoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService implements ILoginService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public boolean validateUser(String username, String password) {
        List<User> userList = userRepository.findByUsername(username);
        if (userList==null){
            throw new UnauthorizedException("No user found with this username");
        }
        for (User user:userList){
            if (user.getPassword().equals(password)){
                return true;
            }
        }
        throw new UnauthorizedException("Password Incorrect");
    }

    @Override
    public boolean isLogin(HttpSession httpSession) {
        if (httpSession.getAttribute("found") != null){
            return true;
        }
        throw new UnauthorizedException("First Login For Access");
    }
}
