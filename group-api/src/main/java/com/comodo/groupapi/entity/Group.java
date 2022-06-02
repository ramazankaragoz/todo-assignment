package com.comodo.groupapi.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo_group")
@SQLDelete(sql = "UPDATE todo_group SET deleted = true WHERE id = ?")
public class Group extends BaseEntity{

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;
}
