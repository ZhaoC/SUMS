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
public class ClubRegistrationRequest {
    private int cRequestId;
    private int userId;
    private int clubId;
    private String requestCRole;
    private Timestamp requestDate;
    private String requestStatus;

    @Id
    @Column(name = "cRequestId", nullable = false, insertable = true, updatable = true)
    public int getcRequestId() {
        return cRequestId;
    }

    public void setcRequestId(int cRequestId) {
        this.cRequestId = cRequestId;
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
    @Column(name = "clubId", nullable = false, insertable = true, updatable = true)
    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    @Basic
    @Column(name = "requestCRole", nullable = false, insertable = true, updatable = true, length = 30)
    public String getRequestCRole() {
        return requestCRole;
    }

    public void setRequestCRole(String requestCRole) {
        this.requestCRole = requestCRole;
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
    @Column(name = "requestStatus", nullable = true, insertable = true, updatable = true, length = 10)
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

        ClubRegistrationRequest that = (ClubRegistrationRequest) o;

        if (cRequestId != that.cRequestId) return false;
        if (userId != that.userId) return false;
        if (clubId != that.clubId) return false;
        if (requestCRole != null ? !requestCRole.equals(that.requestCRole) : that.requestCRole != null) return false;
        if (requestDate != null ? !requestDate.equals(that.requestDate) : that.requestDate != null) return false;
        if (requestStatus != null ? !requestStatus.equals(that.requestStatus) : that.requestStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cRequestId;
        result = 31 * result + userId;
        result = 31 * result + clubId;
        result = 31 * result + (requestCRole != null ? requestCRole.hashCode() : 0);
        result = 31 * result + (requestDate != null ? requestDate.hashCode() : 0);
        result = 31 * result + (requestStatus != null ? requestStatus.hashCode() : 0);
        return result;
    }
}
