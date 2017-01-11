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
public class Activity {
    private int actId;
    private int clubId;
    private String actName;
    private Timestamp actStartDate;
    private Timestamp actEndDate;
    private String actStatus;
    private int actCapacity;
    private String actLocation;
    private Integer actBudget;
    private int actAssignee;
    private String actNote;

    @Id
    @Column(name = "actId", nullable = false, insertable = true, updatable = true)
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
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
    @Column(name = "actName", nullable = false, insertable = true, updatable = true, length = 30)
    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    @Basic
    @Column(name = "actStartDate", nullable = false, insertable = true, updatable = true)
    public Timestamp getActStartDate() {
        return actStartDate;
    }

    public void setActStartDate(Timestamp actStartDate) {
        this.actStartDate = actStartDate;
    }

    @Basic
    @Column(name = "actEndDate", nullable = false, insertable = true, updatable = true)
    public Timestamp getActEndDate() {
        return actEndDate;
    }

    public void setActEndDate(Timestamp actEndDate) {
        this.actEndDate = actEndDate;
    }

    @Basic
    @Column(name = "actStatus", nullable = false, insertable = true, updatable = true, length = 15)
    public String getActStatus() {
        return actStatus;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

    @Basic
    @Column(name = "actCapacity", nullable = false, insertable = true, updatable = true)
    public int getActCapacity() {
        return actCapacity;
    }

    public void setActCapacity(int actCapacity) {
        this.actCapacity = actCapacity;
    }

    @Basic
    @Column(name = "actLocation", nullable = false, insertable = true, updatable = true, length = 300)
    public String getActLocation() {
        return actLocation;
    }

    public void setActLocation(String actLocation) {
        this.actLocation = actLocation;
    }

    @Basic
    @Column(name = "actBudget", nullable = true, insertable = true, updatable = true)
    public Integer getActBudget() {
        return actBudget;
    }

    public void setActBudget(Integer actBudget) {
        this.actBudget = actBudget;
    }

    @Basic
    @Column(name = "actAssignee", nullable = false, insertable = true, updatable = true)
    public int getActAssignee() {
        return actAssignee;
    }

    public void setActAssignee(int actAssignee) {
        this.actAssignee = actAssignee;
    }

    @Basic
    @Column(name = "actNote", nullable = true, insertable = true, updatable = true, length = 500)
    public String getActNote() {
        return actNote;
    }

    public void setActNote(String actNote) {
        this.actNote = actNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (actId != activity.actId) return false;
        if (clubId != activity.clubId) return false;
        if (actCapacity != activity.actCapacity) return false;
        if (actAssignee != activity.actAssignee) return false;
        if (actName != null ? !actName.equals(activity.actName) : activity.actName != null) return false;
        if (actStartDate != null ? !actStartDate.equals(activity.actStartDate) : activity.actStartDate != null)
            return false;
        if (actEndDate != null ? !actEndDate.equals(activity.actEndDate) : activity.actEndDate != null) return false;
        if (actStatus != null ? !actStatus.equals(activity.actStatus) : activity.actStatus != null) return false;
        if (actLocation != null ? !actLocation.equals(activity.actLocation) : activity.actLocation != null)
            return false;
        if (actBudget != null ? !actBudget.equals(activity.actBudget) : activity.actBudget != null) return false;
        if (actNote != null ? !actNote.equals(activity.actNote) : activity.actNote != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actId;
        result = 31 * result + clubId;
        result = 31 * result + (actName != null ? actName.hashCode() : 0);
        result = 31 * result + (actStartDate != null ? actStartDate.hashCode() : 0);
        result = 31 * result + (actEndDate != null ? actEndDate.hashCode() : 0);
        result = 31 * result + (actStatus != null ? actStatus.hashCode() : 0);
        result = 31 * result + actCapacity;
        result = 31 * result + (actLocation != null ? actLocation.hashCode() : 0);
        result = 31 * result + (actBudget != null ? actBudget.hashCode() : 0);
        result = 31 * result + actAssignee;
        result = 31 * result + (actNote != null ? actNote.hashCode() : 0);
        return result;
    }
}
