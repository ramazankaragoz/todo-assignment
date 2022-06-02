package com.comodo.todoassignmentgateway.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class CreateTodoDto implements Serializable {

    private static final long serialVersionUID = 529650592861234364L;
    @NotNull
    private Long groupId;

    @NotNull
    private String todoName;

    @NotNull
    private Date dueDate;

    @NotNull
    private StatusEnum status;

    @NotNull
    private PriorityEnum priority;

}
