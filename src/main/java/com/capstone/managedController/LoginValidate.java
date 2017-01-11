package com.capstone.managedController;

/**
 * Created by  ZhaoC on 10/11/16.
 */
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@ManagedBean(name="loginValidate")
@SessionScoped
public class LoginValidate implements Serializable{
    private Integer loginInputId;
    private String loginInputPassword;
    private String loginInputEmail;

    private Integer userId;
    private String userPassword;
    private String userEmail;

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL_UserId;
    String SQL_UserEmail;

    public void dbData(String loginInputEmail) throws SQLException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sums","root","root");
            statement = connection.createStatement();
            SQL_UserEmail = "Select * from user where userEmail = '"+ loginInputEmail+"'";
            resultSet = statement.executeQuery(SQL_UserEmail);
            resultSet.next();
            userId = Integer.valueOf(resultSet.getString(1).toString());
            userPassword = resultSet.getString(2).toString();
            userEmail = resultSet.getString(7).toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Exception Occurred in the process :" + ex);
        }finally {
            connection.close();
        }
    }

    public String checkValidUserByEmail() throws SQLException, IOException {
        dbData(loginInputEmail);
        if(loginInputEmail.equals(getUserEmail()))
        {
            if(loginInputPassword.equals(userPassword)){
                return "member_homepage"+ "?faces-redirect=true";
            }
            else
            {
                return "login"+ "?faces-redirect=true";
            }
        }
        else
        {
            return "login"+ "?faces-redirect=true";
        }
    }


    public String logout() {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
        HttpSession session = (HttpSession)ectx.getSession(false);
        session.invalidate();
        return "login"+ "?faces-redirect=true";
    }

    public String getLoginInputEmail() {
        return loginInputEmail;
    }

    public void setLoginInputEmail(String loginInputEmail) {
        this.loginInputEmail = loginInputEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getLoginInputId() {
        return loginInputId;
    }

    public void setLoginInputId(Integer loginInputId) {
        this.loginInputId = loginInputId;
    }

    public String getLoginInputPassword() {
        return loginInputPassword;
    }

    public void setLoginInputPassword(String loginInputPassword) {
        this.loginInputPassword = loginInputPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

