package com.capstone.spring.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by  ZhaoC on 10/12/16.
 */
@Entity
public class Role {
    private int roleId;
    private String roleTitle;
    private String rolePrivilege;
    private int userId;
    private int clubId;
    private Date roleInitDate;
    private String roleStatus;

    @Id
    @Column(name = "roleId", nullable = false, insertable = true, updatable = true)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "roleTitle", nullable = false, insertable = true, updatable = true, length = 30)
    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    @Basic
    @Column(name = "rolePrivilege", nullable = false, insertable = true, updatable = true, length = 30)
    public String getRolePrivilege() {
        return rolePrivilege;
    }

    public void setRolePrivilege(String rolePrivilege) {
        this.rolePrivilege = rolePrivilege;
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
    @Column(name = "roleInitDate", nullable = false, insertable = true, updatable = true)
    public Date getRoleInitDate() {
        return roleInitDate;
    }

    public void setRoleInitDate(Date roleInitDate) {
        this.roleInitDate = roleInitDate;
    }

    @Basic
    @Column(name = "roleStatus", nullable = false, insertable = true, updatable = true, length = 10)
    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (roleId != role.roleId) return false;
        if (userId != role.userId) return false;
        if (clubId != role.clubId) return false;
        if (roleTitle != null ? !roleTitle.equals(role.roleTitle) : role.roleTitle != null) return false;
        if (rolePrivilege != null ? !rolePrivilege.equals(role.rolePrivilege) : role.rolePrivilege != null)
            return false;
        if (roleInitDate != null ? !roleInitDate.equals(role.roleInitDate) : role.roleInitDate != null) return false;
        if (roleStatus != null ? !roleStatus.equals(role.roleStatus) : role.roleStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (roleTitle != null ? roleTitle.hashCode() : 0);
        result = 31 * result + (rolePrivilege != null ? rolePrivilege.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + clubId;
        result = 31 * result + (roleInitDate != null ? roleInitDate.hashCode() : 0);
        result = 31 * result + (roleStatus != null ? roleStatus.hashCode() : 0);
        return result;
    }
}
