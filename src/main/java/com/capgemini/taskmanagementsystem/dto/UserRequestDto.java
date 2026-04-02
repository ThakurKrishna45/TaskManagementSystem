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

    public boolean isNull(){
        return (this.username==null || this.email==null || this.email==null || this.fullName==null);
    }
}
