package com.capgemini.taskmanagementsystem;

import com.capgemini.taskmanagementsystem.controller.ProjectController;
import com.capgemini.taskmanagementsystem.dto.ProjectResponseDto;
import com.capgemini.taskmanagementsystem.dto.ProjectSummaryResponseDto;
import com.capgemini.taskmanagementsystem.entity.Project;
import com.capgemini.taskmanagementsystem.entity.Task;
import com.capgemini.taskmanagementsystem.entity.User;
import com.capgemini.taskmanagementsystem.exception.ResourceNotFoundException;
import com.capgemini.taskmanagementsystem.mapper.Mapper;
import com.capgemini.taskmanagementsystem.repository.IProjectRepository;
import com.capgemini.taskmanagementsystem.repository.ITaskRepository;
import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.ILoginService;
import com.capgemini.taskmanagementsystem.service.IProjectService;
import jakarta.servlet.http.HttpSession;
import net.bytebuddy.build.Plugin;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.security.core.parameters.P;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(ProjectController.class)
public class ProjectControllerTesting {
    @Mock
    HttpSession session;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    IProjectService projectService;

    @MockitoBean
    IUserRepository userRepository;

    @MockitoBean
    ILoginService loginService;

    @MockitoBean
    IProjectRepository projectRepository;

    @MockitoBean
    ITaskRepository taskRepository;

    @Test
    void getProjectByUserIdPositiveTest() throws Exception{
        User user = new User("vinay123","vinay123","vinay@gmail.com","Vinay Kumar");
        user.setUserId(101);
        Project project1 = new Project("Task Management","tasks", LocalDate.of(2000,1,1),LocalDate.of(2000,2,2),user);
        Project project2 = new Project("Task Management 1","tasks 1", LocalDate.of(2000,1,1),LocalDate.of(2000,2,2),user);
        List<Project> projects = List.of(project1,project2);

        List<ProjectResponseDto> projectsDto = new ArrayList<>();

        for (Project p:projects){
            projectsDto.add(Mapper.projectToDto(p));
        }

        when(session.getAttribute("found")).thenReturn("abc");
        when(loginService.isLogin(session)).thenReturn(true);
        when(userRepository.existsById(101)).thenReturn(true);
        when(projectService.getAllProjectByUserId(101)).thenReturn(projectsDto);

        mockMvc.perform(get("/project/allProjectByUserId/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].projectName").value("Task Management 1"));
    }


    @Test
    void getProjectByuserIdNegativeTest() throws Exception{

        when(session.getAttribute("found")).thenReturn("abc");
        when(loginService.isLogin(session)).thenReturn(true);
        when(userRepository.existsById(101)).thenReturn(true);
        when(projectService.getAllProjectByUserId(101)).thenThrow(new ResourceNotFoundException("No Project Found for User"));

        mockMvc.perform(get("/project/allProjectByUserId/101"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProjectbetweenDurationPositiveTest() throws Exception{

        User user = new User("vinay123","vinay123","vinay@gmail.com","Vinay Kumar");
        user.setUserId(101);
        Project project1 = new Project("Task Management","tasks", LocalDate.of(2000,1,1),LocalDate.of(2000,2,2),user);
        Project project2 = new Project("Task Management 1","tasks 1", LocalDate.of(2000,1,1),LocalDate.of(2000,2,2),user);
        List<Project> projects = List.of(project1,project2);

        List<ProjectResponseDto> projectsDto = new ArrayList<>();

        for (Project p:projects){
            projectsDto.add(Mapper.projectToDto(p));
        }

        when(session.getAttribute("found")).thenReturn("abc");
        when(loginService.isLogin(session)).thenReturn(true);
        when(projectService.getProjectByTimeDuration(LocalDate.of(2026,1,1),LocalDate.of(2026,2,2))).thenReturn(projectsDto);
        when(projectRepository.findProjectByTimeDuration(LocalDate.of(2026,1,1),LocalDate.of(2026,2,2))).thenReturn(projects);
        mockMvc.perform(get("/project/allProjectBetweenDuration/2026-01-01/2026-02-02"))
                .andExpect(status().isOk());
    }

    @Test
    void getProjectbetweenDurationNegativeTest() throws Exception{

        when(session.getAttribute("found")).thenReturn("abc");
        when(loginService.isLogin(session)).thenReturn(true);
        when(projectService.getProjectByTimeDuration(LocalDate.of(2026,1,1),LocalDate.of(2026,2,2))).thenThrow(new ResourceNotFoundException("No Projects Between this time line!!"));
        when(projectRepository.findProjectByTimeDuration(LocalDate.of(2026,1,1),LocalDate.of(2026,2,2))).thenThrow(new ResourceNotFoundException("No Projects Between this time line!!"));
        mockMvc.perform(get("/project/allProjectBetweenDuration/2026-01-01/2026-02-02"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getSummaryPositiveTest() throws Exception{
        User user = new User("vinay123","vinay123","vinay@gmail.com","Vinay Kumar");
        Project project = new Project("Task Management","tasks", LocalDate.of(2000,1,1),LocalDate.of(2000,2,2),user);
        Task task1 = new Task("task1","task1",LocalDate.of(2000,1,1),"HIGH","In Progress",project,user);
        Task task2 = new Task("task1","task1",LocalDate.of(2000,1,1),"HIGH","In Progress",project,user);
        List<ProjectSummaryResponseDto> summaryList = new ArrayList<>();
        summaryList.add(new ProjectSummaryResponseDto(task1.getUser().getUsername(),task1.getUser().getFullName(),task1.getTaskName(),task1.getStatus()));
        summaryList.add(new ProjectSummaryResponseDto(task2.getUser().getUsername(),task2.getUser().getFullName(),task2.getTaskName(),task2.getStatus()));

        when(taskRepository.findByProjectProjectId(1)).thenReturn(List.of(task1,task2));
        when(projectService.getSummary(1)).thenReturn(summaryList);
        mockMvc.perform(get("/project/summary/1"))
                .andExpect(status().isOk());
    }


    @Test
    void getSummaryNegativeTest() throws Exception{
        when(taskRepository.findByProjectProjectId(1)).thenReturn(null);
        when(projectService.getSummary(1)).thenThrow(new ResourceNotFoundException("No Task Found"));
        mockMvc.perform(get("/project/summary/1"))
                .andExpect(status().isNotFound());
    }
}

