package com.capgemini.taskmanagementsystem.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String username;
    private String password;
    private String email;
    private String fullName;
}
