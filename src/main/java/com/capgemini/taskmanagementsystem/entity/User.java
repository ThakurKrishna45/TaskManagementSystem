package com.capgemini.taskmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.*;

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



}
