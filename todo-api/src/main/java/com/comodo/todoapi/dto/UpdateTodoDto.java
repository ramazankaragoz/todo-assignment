package com.comodo.todoapi.dto;


import com.comodo.todoapi.util.PriorityEnum;
import com.comodo.todoapi.util.StatusEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateTodoDto implements Serializable {

    private Long groupId;
    private String todoName;
    private Date dueDate;
    private StatusEnum status;
    private PriorityEnum priority;

}
