package com.comodo.todoapi.entity;

import com.comodo.todoapi.util.PriorityEnum;
import com.comodo.todoapi.util.StatusEnum;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo")
@SQLDelete(sql = "UPDATE todo SET deleted = true WHERE id = ?")
public class Todo extends BaseEntity{

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "todo_name")
    private String todoName;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Todo todo = (Todo) o;
        return getId() != null && Objects.equals(getId(), todo.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
