package com.comodo.todoassignmentgateway.dto;


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
