package com.capgemini.taskmanagementsystem.interceptor;

import com.capgemini.taskmanagementsystem.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("found") == null) {
            throw new UnauthorizedException("Session expired or user not logged in.");
        }

        return true;
    }
}