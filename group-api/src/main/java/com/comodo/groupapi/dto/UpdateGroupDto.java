package com.comodo.groupapi.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UpdateGroupDto implements Serializable {

    private static final long serialVersionUID = 1795967128300950693L;

    private Long userId;
    private String name;
}
