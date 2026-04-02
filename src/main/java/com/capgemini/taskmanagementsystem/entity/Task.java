package com.capgemini.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskID")
    private Integer taskID;

    @Column(nullable = false, name = "TaskName")
    private String taskName;

    @Lob
    @Column(name = "Description")
    private String description;
    @Column(name = "DueDate")
    private LocalDate dueDate;

    @Column(name = "Priority")
    private String priority;

    @Column(name = "Status")
    private String status;


    @ManyToOne
    @JoinColumn(name = "ProjectID")
    private Project project;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "TaskCategory",
            joinColumns = @JoinColumn(name = "TaskID"),
            inverseJoinColumns = @JoinColumn(name = "CategoryID")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "task")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "task")
    private List<Attachment> attachments = new ArrayList<>();

    public Task(String taskName, String description, LocalDate dueDate, String priority, String status, Project project, User user) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.project = project;
        this.user = user;
    }
}