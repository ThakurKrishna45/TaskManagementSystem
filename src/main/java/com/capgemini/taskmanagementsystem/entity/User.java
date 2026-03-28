package com.capgemini.taskmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="user")
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserId")
    private Integer userId;
    @NonNull
    @Column(name="Username")
    private String username;
    @NonNull
    @Column(name="Password")
    private String password;
    @NonNull
    @Column(name="Email")
    private String email;
    @NonNull
    @Column(name="FullName")
    private String fullName;
    @OneToMany(mappedBy = "user")
    private List<Project> projects = new ArrayList<>();

}
