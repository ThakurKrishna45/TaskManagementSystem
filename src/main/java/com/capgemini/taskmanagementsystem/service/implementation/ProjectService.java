package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.dto.ProjectResponseDto;
import com.capgemini.taskmanagementsystem.entity.Project;
import com.capgemini.taskmanagementsystem.entity.User;
import com.capgemini.taskmanagementsystem.exception.ResourceNotFoundException;
import com.capgemini.taskmanagementsystem.mapper.Mapper;
import com.capgemini.taskmanagementsystem.repository.IProjectRepository;
import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {
    @Autowired
    IProjectRepository projectRepository;
    @Autowired
    IUserRepository userRepository;


    @Override
    public List<ProjectResponseDto> getProjectByTimeDuration(LocalDate startDate, LocalDate endDate) {
        List<Project> projectsBetweenDuration = projectRepository.findProjectByTimeDuration(startDate,endDate);
        List<ProjectResponseDto> projectsDtos = new ArrayList<>();
        if (projectsBetweenDuration != null){
            for (Project project:projectsBetweenDuration){
                projectsDtos.add(Mapper.projectToDto(project));
            }
            return projectsDtos;
        }
        else{
            throw new ResourceNotFoundException("No Projects Between this time line!!");
        }
    }

    @Override
    public List<ProjectResponseDto> getAllProjectByUserId(Integer userId){
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User Not Found");
        }
        List<Project> allProjectByUser = projectRepository.findAllByUserId(userId);
        List<ProjectResponseDto> projectDtos = new ArrayList<>();
        if (allProjectByUser != null){
            for (Project p:allProjectByUser){
                projectDtos.add(Mapper.projectToDto(p));
            }
            return projectDtos;
        }
        else{
            throw new ResourceNotFoundException("No Projects given to this user");
        }
    }
}
