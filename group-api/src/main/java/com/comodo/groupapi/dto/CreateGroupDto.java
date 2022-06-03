package com.comodo.groupapi.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CreateGroupDto implements Serializable {

    private static final long serialVersionUID = 3938028036213589574L;

    @NotNull
    private Long userId;

    @NotNull
    private String name;
}
