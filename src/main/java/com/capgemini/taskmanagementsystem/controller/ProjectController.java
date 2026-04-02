package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.ProjectResponseDto;
import com.capgemini.taskmanagementsystem.dto.ProjectSummaryResponseDto;
import com.capgemini.taskmanagementsystem.service.ILoginService;
import com.capgemini.taskmanagementsystem.service.IProjectService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("project/")
public class ProjectController {

    @Autowired
    IProjectService projectService;

    @Autowired
    ILoginService loginService;

    @GetMapping("/allProjectBetweenDuration/{startDate}/{endDate}")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjectByTimeline(@PathVariable LocalDate startDate,@PathVariable LocalDate endDate,HttpSession httpSession){
        return new ResponseEntity<List<ProjectResponseDto>>(projectService.getProjectByTimeDuration(startDate,endDate), HttpStatus.OK);
    }

    @GetMapping("allProjectByUserId/{userId}")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjectByUserId(@PathVariable Integer userId,HttpSession httpSession){
        return new ResponseEntity<List<ProjectResponseDto>>(projectService.getAllProjectByUserId(userId),HttpStatus.OK);
    }

    @GetMapping("summary/{projectId}")
    public ResponseEntity<List<ProjectSummaryResponseDto>> getSummary(@PathVariable Integer projectId){
        return new ResponseEntity<List<ProjectSummaryResponseDto>>(projectService.getSummary(projectId),HttpStatus.OK);
    }

}
