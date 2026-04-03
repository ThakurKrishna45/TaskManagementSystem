package com.capgemini.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSummaryResponseDto {
    private String userName;
    private LocalDate dueDate;
    private String task;
    private String status;
}
