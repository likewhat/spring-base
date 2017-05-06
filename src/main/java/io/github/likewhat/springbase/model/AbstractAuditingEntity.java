package io.github.likewhat.springbase.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class AbstractAuditingEntity<PK> implements Serializable {

    /**
     * The primary key for model
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private PK id;

    /**
     * The id of user which created this model
     */
    @Column(nullable = false)
    private Long createdBy;

    /**
     * The creation date of this model
     */
    @Column(nullable = false)
    private Date createdDate;

    /**
     * The id of last user which modified this model
     */
    @Column(nullable = false)
    private Long lastModifiedBy;

    /**
     * The last modified date of this model.
     */
    @Column(nullable = false)
    private Date lastModifiedDate;

    /**
     * Whether this model is been deleted
     */
    @Column(nullable = false)
    private boolean isDeleted;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void markCreated(Long createdBy) {
        Date now = new Date();
        setCreatedDate(now);
        setLastModifiedDate(now);
        setCreatedBy(createdBy);
        setLastModifiedBy(createdBy);
    }

    public void markModified(Long modifiedBy) {
        Date now = new Date();
        setLastModifiedDate(now);
        setLastModifiedBy(modifiedBy);
    }

    public void markDeleted(Long deletedBy) {
        markModified(deletedBy);
        setDeleted(true);
    }
}
