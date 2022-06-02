package com.comodo.todoassignmentgateway.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Boolean enabled;
}
