package com.kai.ecommercebackendsystem.model.base;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private Integer createdBy = 0;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private Integer updatedBy = 0;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time", nullable = false)
    private Date updatedTime;
}
