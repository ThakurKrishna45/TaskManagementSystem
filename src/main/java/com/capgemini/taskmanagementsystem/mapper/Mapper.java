package com.capgemini.taskmanagementsystem.mapper;

import com.capgemini.taskmanagementsystem.dto.ProjectRequestDto;
import com.capgemini.taskmanagementsystem.dto.ProjectResponseDto;
import com.capgemini.taskmanagementsystem.dto.UserRequestDto;
import com.capgemini.taskmanagementsystem.dto.UserResponseDto;
import com.capgemini.taskmanagementsystem.entity.Project;
import com.capgemini.taskmanagementsystem.entity.User;

public class Mapper {

    public static UserResponseDto userToDto(User user){
        return new UserResponseDto(user.getUserId(),user.getUsername(),user.getEmail(),user.getFullName());
    }
    public static User dtoToUser(UserRequestDto userRequestDto){
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setFullName(userRequestDto.getFullName());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }

    public static ProjectResponseDto projectToDto(Project project){
        return new ProjectResponseDto(project.getProjectName(),project.getDescription(),project.getStartDate(),project.getEndDate());
    }

    public static Project dtoToProject(ProjectRequestDto projectRequestDto) {
        Project project = new Project();
        project.setProjectName(projectRequestDto.getProjectName());
        project.setDescription(projectRequestDto.getDescription());
        project.setStartDate(projectRequestDto.getStartDate());
        project.setEndDate(projectRequestDto.getEndDate());
        return project;
    }
}
