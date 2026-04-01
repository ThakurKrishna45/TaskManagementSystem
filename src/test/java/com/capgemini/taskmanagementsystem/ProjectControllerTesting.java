package com.capgemini.taskmanagementsystem;

import com.capgemini.taskmanagementsystem.controller.ProjectController;
import com.capgemini.taskmanagementsystem.dto.ProjectResponseDto;
import com.capgemini.taskmanagementsystem.entity.Project;
import com.capgemini.taskmanagementsystem.entity.User;
import com.capgemini.taskmanagementsystem.exception.ResourceNotFoundException;
import com.capgemini.taskmanagementsystem.mapper.Mapper;
import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.IProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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

        when(userRepository.existsById(101)).thenReturn(true);
        when(projectService.getAllProjectByUserId(101)).thenReturn(projectsDto);

        mockMvc.perform(get("/project/allProjectByUserId/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].projectName").value("Task Management 1"));
    }


    @Test
    void getProjectByuserIdNegativeTest() throws Exception{

        when(userRepository.existsById(101)).thenReturn(true);
        when(projectService.getAllProjectByUserId(101)).thenThrow(new ResourceNotFoundException("No Project Found for User"));

        mockMvc.perform(get("/project/allProjectByUserId/101"))
                .andExpect(status().isNotFound());
    }
}
