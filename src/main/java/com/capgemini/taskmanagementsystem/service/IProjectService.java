package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.entity.Project;

import java.time.LocalDate;
import java.util.List;

public interface IProjectService {
    public List<Project> getProjectByTimeDuration(LocalDate startDate,LocalDate endDate);

    public List<Project> getAllProjectByUserId(Integer userId);
}
