package com.capgemini.taskmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ProjectID")
    private Integer projectId;
    @Column(name="ProjectName",nullable = false)
    private String projectName;
    @Column(name="Description")
    private String description;
    @Column(name="StartDate")
    private LocalDate startDate;
    @Column(name = "EndDate")
    private LocalDate endDate;

    @ManyToOne()
    @JoinColumn(name="UserId",nullable = false)
    private User user;
}
