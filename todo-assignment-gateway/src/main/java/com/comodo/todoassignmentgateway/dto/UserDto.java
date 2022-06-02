package com.comodo.todoassignmentgateway.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Boolean enabled;
}
