package com.edu.quique.repositories;

import lombok.Generated;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;

@MappedSuperclass
@Audited
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseMO {

    @CreatedBy
    @Column(
            name = "cod_user_creation",
            nullable = false,
            updatable = false
    )
    private String createdBy;

    @CreatedDate
    @Column(
            name = "creation_date",
            nullable = false,
            updatable = false
    )
    private OffsetDateTime createdDate;
    @LastModifiedBy
    @Column(
            name = "cod_user_modification"
    )
    private String lastModifiedBy;
    @LastModifiedDate
    @Column(
            name = "modification_date"
    )
    private OffsetDateTime lastModifiedDate;


    @Generated
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Generated
    public OffsetDateTime getCreatedDate() {
        return this.createdDate;
    }

    @Generated
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    @Generated
    public OffsetDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    @Generated
    protected void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    @Generated
    protected void setCreatedDate(final OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Generated
    protected void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Generated
    protected void setLastModifiedDate(final OffsetDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Generated
    public String toString() {
        String var10000 = this.getCreatedBy();
        return "Auditable(createdBy=" + var10000 + ", createdDate=" + this.getCreatedDate() + ", lastModifiedBy=" + this.getLastModifiedBy() + ", lastModifiedDate=" + this.getLastModifiedDate() + ")";
    }
}