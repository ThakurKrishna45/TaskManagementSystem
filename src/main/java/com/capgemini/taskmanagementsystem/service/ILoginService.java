package com.capgemini.taskmanagementsystem.service;

import jakarta.servlet.http.HttpSession;

public interface ILoginService {
    boolean validateUser(String username,String password);
    boolean isLogin(HttpSession httpSession);
}
