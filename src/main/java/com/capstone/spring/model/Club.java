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
public class Club {
    private int clubId;
    private String clubName;
    private String clubCategory;
    private Date clubInitDate;
    private String clubStatus;
    private String clubNote;
    private int clubOwner;

    @Id
    @Column(name = "clubId", nullable = false, insertable = true, updatable = true)
    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    @Basic
    @Column(name = "clubName", nullable = false, insertable = true, updatable = true, length = 30)
    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    @Basic
    @Column(name = "clubCategory", nullable = false, insertable = true, updatable = true, length = 30)
    public String getClubCategory() {
        return clubCategory;
    }

    public void setClubCategory(String clubCategory) {
        this.clubCategory = clubCategory;
    }

    @Basic
    @Column(name = "clubInitDate", nullable = false, insertable = true, updatable = true)
    public Date getClubInitDate() {
        return clubInitDate;
    }

    public void setClubInitDate(Date clubInitDate) {
        this.clubInitDate = clubInitDate;
    }

    @Basic
    @Column(name = "clubStatus", nullable = false, insertable = true, updatable = true, length = 15)
    public String getClubStatus() {
        return clubStatus;
    }

    public void setClubStatus(String clubStatus) {
        this.clubStatus = clubStatus;
    }

    @Basic
    @Column(name = "clubNote", nullable = true, insertable = true, updatable = true, length = 500)
    public String getClubNote() {
        return clubNote;
    }

    public void setClubNote(String clubNote) {
        this.clubNote = clubNote;
    }

    @Id
    @Column(name = "clubOwner", nullable = false, insertable = true, updatable = true)
    public int getClubOwner() {
        return clubOwner;
    }

    public void setClubOwner(int clubOwner) {
        this.clubOwner = clubOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Club club = (Club) o;

        if (clubId != club.clubId) return false;
        if (clubName != null ? !clubName.equals(club.clubName) : club.clubName != null) return false;
        if (clubCategory != null ? !clubCategory.equals(club.clubCategory) : club.clubCategory != null) return false;
        if (clubInitDate != null ? !clubInitDate.equals(club.clubInitDate) : club.clubInitDate != null) return false;
        if (clubStatus != null ? !clubStatus.equals(club.clubStatus) : club.clubStatus != null) return false;
        if (clubNote != null ? !clubNote.equals(club.clubNote) : club.clubNote != null) return false;
        if (clubOwner != club.clubOwner) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clubId;
        result = 31 * result + (clubName != null ? clubName.hashCode() : 0);
        result = 31 * result + (clubCategory != null ? clubCategory.hashCode() : 0);
        result = 31 * result + (clubInitDate != null ? clubInitDate.hashCode() : 0);
        result = 31 * result + (clubStatus != null ? clubStatus.hashCode() : 0);
        result = 31 * result + (clubNote != null ? clubNote.hashCode() : 0);
        result = 31 * result + clubOwner;
        return result;
    }
}
