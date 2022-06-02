package com.comodo.userapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;

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

    @Column(name = "status")
    private Boolean status;

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


}