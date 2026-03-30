package com.capgemini.taskmanagementsystem.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private Integer commentId;
    private String text;
    private LocalDateTime createdAt;
    private Integer taskId;
    private String username;
}