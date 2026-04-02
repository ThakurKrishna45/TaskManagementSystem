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
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "UserRoles",
            joinColumns = @JoinColumn(name = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "UserRoleID")
    )
    private Set<UserRole> roles = new HashSet<>();


    public User(@NonNull String username, @NonNull String password, @NonNull String email, @NonNull String fullName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }
}
