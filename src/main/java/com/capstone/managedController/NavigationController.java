package com.capstone.managedController;

import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by  ZhaoC on 10/17/16.
 */
@ManagedBean(name = "navigationController")
@RequestScoped
public class NavigationController implements Serializable {

    @ManagedProperty("#{roleMB}")
    RoleManagedController roleManagedController;

    private String viewSwitchButtonValue;

    public String moveToManagerPage(){
//        CurrentTheme currentTheme = new CurrentTheme();
//        currentTheme.setTheme("glass-x");
        return "manager_homepage"+ "?faces-redirect=true";
    }

    public String moveToMemberPage(){
        return "member_homepage"+ "?faces-redirect=true";
    }

    public String moveToRegisterPage(){
        return "register"+ "?faces-redirect=true";
    }

    public String moveToLoginPage(){
        return "login"+ "?faces-redirect=true";
    }

    public RoleManagedController getRoleManagedController() {
        return roleManagedController;
    }

    public void setRoleManagedController(RoleManagedController roleManagedController) {
        this.roleManagedController = roleManagedController;
    }

    public String getViewSwitchButtonValue() {
        viewSwitchButtonValue = "none";
        if(getRoleManagedController().getIsUserAManager()){
            viewSwitchButtonValue = "block";
        }

        return viewSwitchButtonValue;
    }

    public void setViewSwitchButtonValue(String viewSwitchButtonValue) {
        this.viewSwitchButtonValue = viewSwitchButtonValue;
    }
}
