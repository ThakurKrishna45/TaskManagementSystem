package com.capgemini.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import com.capgemini.taskmanagementsystem.entity.User;
import com.capgemini.taskmanagementsystem.entity.Task;

import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentID")
    private int commentId;

    @Column(name = "Text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "TaskID", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}