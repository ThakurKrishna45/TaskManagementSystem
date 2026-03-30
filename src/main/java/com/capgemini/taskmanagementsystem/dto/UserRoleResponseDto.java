package com.capgemini.taskmanagementsystem.dto;


import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleResponseDto {
    private String roleName;
}
