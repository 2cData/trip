package com.toseedata.wetripout.trip.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter()
@Setter()
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<T> {
    @Column(name = "created_by")
    @CreatedBy
    private T createdBy;

    //TODO Updatable=false is not working
    @Column(name = "created_date", updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "last_modified_by")
    @LastModifiedBy
    private T lastModifiedBy;

    @Column(name = "last_modified_date", nullable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}
