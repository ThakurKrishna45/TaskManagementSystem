package com.capgemini.taskmanagementsystem.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TaskDto {

    private String taskName;

    private String description;

    private LocalDate dueDate;

    private String priority;

    private String status;

    private Integer projectId;

    private Integer userId;
}
