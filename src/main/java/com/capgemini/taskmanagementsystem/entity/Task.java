package com.capgemini.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer TaskID;

    @Column(nullable = false)
    private String TaskName;

    @Lob
    private String Description;

    private LocalDate DueDate;

    private String Priority;
    private String Status;


    @ManyToOne
    @JoinColumn(name = "ProjectID")
    private Project project;

    @ManyToOne
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
}