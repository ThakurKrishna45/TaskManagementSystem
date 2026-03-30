package com.capgemini.taskmanagementsystem.service;

import com.capgemini.taskmanagementsystem.dto.ProjectResponseDto;
import com.capgemini.taskmanagementsystem.entity.Project;

import java.time.LocalDate;
import java.util.List;

public interface IProjectService {
    public List<ProjectResponseDto> getProjectByTimeDuration(LocalDate startDate, LocalDate endDate);

    public List<ProjectResponseDto> getAllProjectByUserId(Integer userId);
}
