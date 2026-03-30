package com.capgemini.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSummarry {
    private String userName;
    private String name;
    private String task;
    private String status;
}
