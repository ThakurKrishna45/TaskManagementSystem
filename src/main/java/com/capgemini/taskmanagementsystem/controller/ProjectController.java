package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.ProjectResponseDto;
import com.capgemini.taskmanagementsystem.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("project/")
public class ProjectController {

    @Autowired
    IProjectService projectService;

    @GetMapping("allProjectBetweenDuration/{startDate}/{endDate}")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjectByTimeline(@PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
        return new ResponseEntity<List<ProjectResponseDto>>(projectService.getProjectByTimeDuration(startDate,endDate), HttpStatus.OK);
    }

    @GetMapping("allProjectByUserId/{userId}")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjectByUserId(@PathVariable Integer userId){
        return new ResponseEntity<List<ProjectResponseDto>>(projectService.getAllProjectByUserId(userId),HttpStatus.OK);
    }
}
