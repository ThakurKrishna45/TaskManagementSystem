package com.capgemini.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryID;

    @Column(nullable = false)
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private Set<Task> tasks = new HashSet<>();
}