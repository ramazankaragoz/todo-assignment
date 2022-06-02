package com.comodo.todoapi.entity;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    @CreatedDate
    private Instant createDate = Instant.now();

    @Column(name = "update_date")
    @LastModifiedDate
    private Instant updateDate;

    @Column(name = "deleted")
    private Boolean deleted=Boolean.TRUE;

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    private String lastModifiedBy;

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    private String createdBy;

    @PreUpdate
    public void setUpdateDate() {
        this.updateDate = Instant.now();
    }

    public Long getId() {
        return id;
    }
}