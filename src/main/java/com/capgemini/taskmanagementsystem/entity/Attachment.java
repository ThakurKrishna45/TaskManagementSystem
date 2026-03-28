package com.capgemini.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import com.capgemini.taskmanagementsystem.entity.Task;

@Entity
@Table(name = "Attachment")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttachmentID")
    private Integer attachmentId;

    @Column(name = "FileName", nullable = false)
    private String fileName;

    @Column(name = "FilePath", nullable = false)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "TaskID")
    private Task task;
}