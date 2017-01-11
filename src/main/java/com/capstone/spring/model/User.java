package com.capstone.spring.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by  ZhaoC on 10/15/16.
 */
@Entity
public class User {
    private int userId;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userGender;
    private Date userBirth;
    private String userEmail;
    private String userPhone;

    @Id
    @Column(name = "userId", nullable = false, insertable = true, updatable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userPassword", nullable = false, insertable = true, updatable = true, length = 255)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "userFirstName", nullable = false, insertable = true, updatable = true, length = 30)
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    @Basic
    @Column(name = "userLastName", nullable = false, insertable = true, updatable = true, length = 30)
    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    @Basic
    @Column(name = "userGender", nullable = false, insertable = true, updatable = true, length = 5)
    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    @Basic
    @Column(name = "userBirth", nullable = false, insertable = true, updatable = true)
    public Date getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }

    @Basic
    @Column(name = "userEmail", nullable = false, insertable = true, updatable = true, length = 30)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "userPhone", nullable = true, insertable = true, updatable = true, length = 30)
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (userPassword != null ? !userPassword.equals(user.userPassword) : user.userPassword != null) return false;
        if (userFirstName != null ? !userFirstName.equals(user.userFirstName) : user.userFirstName != null)
            return false;
        if (userLastName != null ? !userLastName.equals(user.userLastName) : user.userLastName != null) return false;
        if (userGender != null ? !userGender.equals(user.userGender) : user.userGender != null) return false;
        if (userBirth != null ? !userBirth.equals(user.userBirth) : user.userBirth != null) return false;
        if (userEmail != null ? !userEmail.equals(user.userEmail) : user.userEmail != null) return false;
        if (userPhone != null ? !userPhone.equals(user.userPhone) : user.userPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userFirstName != null ? userFirstName.hashCode() : 0);
        result = 31 * result + (userLastName != null ? userLastName.hashCode() : 0);
        result = 31 * result + (userGender != null ? userGender.hashCode() : 0);
        result = 31 * result + (userBirth != null ? userBirth.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        return result;
    }
}
