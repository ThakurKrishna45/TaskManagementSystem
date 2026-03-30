package com.capgemini.taskmanagementsystem.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Integer userId;
    private String username;
    private String email;
    private String fullName;

}
