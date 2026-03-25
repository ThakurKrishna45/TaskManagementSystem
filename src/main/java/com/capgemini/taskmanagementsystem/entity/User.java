package com.capgemini.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="user")
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

    public User(){};

    public User(Integer userId, @NonNull String username, @NonNull String password, @NonNull String email, @NonNull String fullName) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }
}
