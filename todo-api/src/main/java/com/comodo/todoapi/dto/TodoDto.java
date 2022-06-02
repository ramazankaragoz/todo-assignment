package com.comodo.todoapi.dto;


import com.comodo.todoapi.util.PriorityEnum;
import com.comodo.todoapi.util.StatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TodoDto implements Serializable {

    private static final long serialVersionUID = -3534089799557782875L;
    private Long Id;

    private Long groupId;

    private String todoName;

    private Date dueDate;

    private StatusEnum status;

    private PriorityEnum priority;

}
