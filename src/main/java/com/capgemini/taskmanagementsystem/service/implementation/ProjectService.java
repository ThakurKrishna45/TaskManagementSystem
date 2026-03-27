package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.entity.Project;
import com.capgemini.taskmanagementsystem.exception.ResourseNotFoundException;
import com.capgemini.taskmanagementsystem.repository.IProjectRepository;
import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {
    @Autowired
    IProjectRepository projectRepository;


    @Override
    public List<Project> getProjectByTimeDuration(LocalDate startDate, LocalDate endDate) {
        List<Project> projectsBetweenDuration = projectRepository.findProjectByTimeDuration(startDate,endDate);
        if (projectsBetweenDuration != null){
            return projectsBetweenDuration;
        }
        else{
            throw new ResourseNotFoundException("No Projects Between this time line!!");
        }
    }

    @Override
    public List<Project> getAllProjectByUserId(Integer userId){
        List<Project> allProjectByUser = projectRepository.findAllByUserId(userId);
        if (allProjectByUser != null){
            return allProjectByUser;
        }
        else{
            throw new ResourseNotFoundException("No Projects given to this user");
        }
    }
}
