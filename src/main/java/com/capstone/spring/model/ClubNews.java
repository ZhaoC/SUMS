package com.capstone.spring.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by  ZhaoC on 11/21/16.
 */
@Entity
public class ClubNews {
    private int newsId;
    private String newsTitle;
    private String newsContent;
    private String newsCategory;
    private Timestamp effectiveDate;
    private Timestamp releaseDate;
    private int userId;
    private int clubId;

    @Id
    @Column(name = "newsId", nullable = false, insertable = true, updatable = true)
    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    @Basic
    @Column(name = "newsTitle", nullable = false, insertable = true, updatable = true, length = 500)
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @Basic
    @Column(name = "newsContent", nullable = false, insertable = true, updatable = true, length = 3000)
    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    @Basic
    @Column(name = "newsCategory", nullable = false, insertable = true, updatable = true, length = 100)
    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    @Basic
    @Column(name = "effectiveDate", nullable = false, insertable = true, updatable = true)
    public Timestamp getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Timestamp effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Basic
    @Column(name = "releaseDate", nullable = false, insertable = true, updatable = true)
    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClubNews clubnews = (ClubNews) o;

        if (newsId != clubnews.newsId) return false;
        if (userId != clubnews.userId) return false;
        if (clubId != clubnews.clubId) return false;
        if (newsTitle != null ? !newsTitle.equals(clubnews.newsTitle) : clubnews.newsTitle != null) return false;
        if (newsContent != null ? !newsContent.equals(clubnews.newsContent) : clubnews.newsContent != null)
            return false;
        if (newsCategory != null ? !newsCategory.equals(clubnews.newsCategory) : clubnews.newsCategory != null)
            return false;
        if (effectiveDate != null ? !effectiveDate.equals(clubnews.effectiveDate) : clubnews.effectiveDate != null)
            return false;
        if (releaseDate != null ? !releaseDate.equals(clubnews.releaseDate) : clubnews.releaseDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = newsId;
        result = 31 * result + (newsTitle != null ? newsTitle.hashCode() : 0);
        result = 31 * result + (newsContent != null ? newsContent.hashCode() : 0);
        result = 31 * result + (newsCategory != null ? newsCategory.hashCode() : 0);
        result = 31 * result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + clubId;
        return result;
    }
}
