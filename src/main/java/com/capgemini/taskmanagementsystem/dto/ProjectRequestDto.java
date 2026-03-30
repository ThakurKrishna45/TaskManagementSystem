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
public class ProjectRequestDto {
    private String projectName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
