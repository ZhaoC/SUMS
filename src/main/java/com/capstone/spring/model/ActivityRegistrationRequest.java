package com.capstone.spring.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by  ZhaoC on 10/12/16.
 */
@Entity
public class ActivityRegistrationRequest {
    private int aRequestId;
    private int userId;
    private int actId;
    private Timestamp requestDate;
    private String requestStatus;

    @Id
    @Column(name = "aRequestId", nullable = false, insertable = true, updatable = true)
    public int getaRequestId() {
        return aRequestId;
    }

    public void setaRequestId(int aRequestId) {
        this.aRequestId = aRequestId;
    }

    @Basic
    @Column(name = "userId", nullable = false, insertable = true, updatable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "actId", nullable = false, insertable = true, updatable = true)
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Basic
    @Column(name = "requestDate", nullable = false, insertable = true, updatable = true)
    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    @Basic
    @Column(name = "requestStatus", nullable = false, insertable = true, updatable = true, length = 10)
    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityRegistrationRequest that = (ActivityRegistrationRequest) o;

        if (aRequestId != that.aRequestId) return false;
        if (userId != that.userId) return false;
        if (actId != that.actId) return false;
        if (requestDate != null ? !requestDate.equals(that.requestDate) : that.requestDate != null) return false;
        if (requestStatus != null ? !requestStatus.equals(that.requestStatus) : that.requestStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aRequestId;
        result = 31 * result + userId;
        result = 31 * result + actId;
        result = 31 * result + (requestDate != null ? requestDate.hashCode() : 0);
        result = 31 * result + (requestStatus != null ? requestStatus.hashCode() : 0);
        return result;
    }
}
