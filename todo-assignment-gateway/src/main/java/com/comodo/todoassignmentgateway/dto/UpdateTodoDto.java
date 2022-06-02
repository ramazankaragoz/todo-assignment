package com.comodo.todoassignmentgateway.dto;



import lombok.Data;

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
