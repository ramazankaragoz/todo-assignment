package com.comodo.userapi.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Boolean enabled;
}
