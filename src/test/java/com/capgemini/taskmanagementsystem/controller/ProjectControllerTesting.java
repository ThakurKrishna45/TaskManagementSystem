package com.capgemini.taskmanagementsystem.controller;

import com.capgemini.taskmanagementsystem.dto.ProjectResponseDto;
import com.capgemini.taskmanagementsystem.dto.ProjectSummaryResponseDto;
import com.capgemini.taskmanagementsystem.dto.ProjectSummaryWrapperDto;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.mock.web.MockHttpSession;
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

    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        session.setAttribute("found", "abc");
    }

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

        when(loginService.isLogin(session)).thenReturn(true);
        when(userRepository.existsById(101)).thenReturn(true);
        when(projectService.getAllProjectByUserId(101)).thenReturn(projectsDto);

        mockMvc.perform(get("/project/allProjectByUserId/101")
                .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].projectName").value("Task Management 1"));
    }


    @Test
    void getProjectByuserIdNegativeTest() throws Exception{

        when(loginService.isLogin(session)).thenReturn(true);
        when(userRepository.existsById(101)).thenReturn(true);
        when(projectService.getAllProjectByUserId(101)).thenThrow(new ResourceNotFoundException("No Project Found for User"));

        mockMvc.perform(get("/project/allProjectByUserId/101")
                .session(session))
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

        when(loginService.isLogin(session)).thenReturn(true);
        when(projectService.getProjectByTimeDuration(LocalDate.of(2026,1,1),LocalDate.of(2026,2,2))).thenReturn(projectsDto);
        when(projectRepository.findProjectByTimeDuration(LocalDate.of(2026,1,1),LocalDate.of(2026,2,2))).thenReturn(projects);
        mockMvc.perform(get("/project/allProjectBetweenDuration/2026-01-01/2026-02-02")
                .session(session))
                .andExpect(status().isOk());
    }

    @Test
    void getProjectbetweenDurationNegativeTest() throws Exception{

        when(loginService.isLogin(session)).thenReturn(true);
        when(projectService.getProjectByTimeDuration(LocalDate.of(2026,1,1),LocalDate.of(2026,2,2))).thenThrow(new ResourceNotFoundException("No Projects Between this time line!!"));
        when(projectRepository.findProjectByTimeDuration(LocalDate.of(2026,1,1),LocalDate.of(2026,2,2))).thenThrow(new ResourceNotFoundException("No Projects Between this time line!!"));
        mockMvc.perform(get("/project/allProjectBetweenDuration/2026-01-01/2026-02-02")
                .session(session))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProjectSummaryPositiveTest() throws Exception {

        ProjectSummaryResponseDto dto1 =
                new ProjectSummaryResponseDto("john_doe", LocalDate.of(2022, 2, 1), "Task One", "In Progress");

        ProjectSummaryResponseDto dto2 =
                new ProjectSummaryResponseDto("jane_smith", LocalDate.of(2023, 4, 1), "Task Two", "Pending");

        List<ProjectSummaryResponseDto> summaries = List.of(dto1, dto2);

        ProjectSummaryWrapperDto wrapper = new ProjectSummaryWrapperDto(
                "Task Management System",
                LocalDate.of(2026, 4, 1),
                LocalDate.of(2026, 4, 30),
                summaries
        );

        when(projectService.getSummary(1)).thenReturn(wrapper);

        mockMvc.perform(get("/project/summary/1")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectName").value("Task Management System"))
                .andExpect(jsonPath("$.summaries[1].task").value("Task Two"))
                .andExpect(jsonPath("$.summaries[0].userName").value("john_doe"));
    }


    @Test
    void getSummaryNegativeTest() throws Exception {

        when(projectService.getSummary(1))
                .thenThrow(new ResourceNotFoundException("No Task Found"));

        mockMvc.perform(get("/project/summary/1")
                        .session(session))
                .andExpect(status().isNotFound());
    }
}
