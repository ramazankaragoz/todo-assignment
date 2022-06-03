package com.comodo.todoassignmentgateway.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupDto implements Serializable {
    private static final long serialVersionUID = 7493980435820008405L;

    private Long id;

    private Long userId;

    private String name;
}
