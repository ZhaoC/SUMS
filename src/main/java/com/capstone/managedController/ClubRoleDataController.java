package com.capstone.managedController;

import com.capstone.spring.model.Club;
import com.capstone.spring.model.Role;
import com.capstone.spring.service.ClubService;
import com.capstone.spring.service.RoleService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  ZhaoC on 11/28/16.
 */

@ManagedBean(name="clubRoleDataMB")
@SessionScoped
public class ClubRoleDataController {
    @ManagedProperty(value="#{ClubService}")
    private
    ClubService clubService;

    @ManagedProperty(value="#{RoleService}")
    private
    RoleService roleService;

    @ManagedProperty("#{loginValidate}")
    private
    LoginValidate loginValidate;

    private List<Club> currentUserClubListWithADMIN;

    public ClubService getClubService() {
        return clubService;
    }

    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public LoginValidate getLoginValidate() {
        return loginValidate;
    }

    public void setLoginValidate(LoginValidate loginValidate) {
        this.loginValidate = loginValidate;
    }

    public List<Club> getCurrentUserClubListWithADMIN() {
        currentUserClubListWithADMIN = new ArrayList<Club>();
        int userId = loginValidate.getUserId();

        List<Role> roleListByCurrentUserId = new ArrayList<Role>();

        for(Role role: getRoleService().getRolesByUserId(userId)){
            if(role.getRolePrivilege().equals("ADMIN")){
                roleListByCurrentUserId.add(role);
            }
        }

        List<Integer> clubIdListByUserId = new ArrayList<Integer>();
        for(Role role: roleListByCurrentUserId){
            int clubId = role.getClubId();
            if(!clubIdListByUserId.contains(clubId)){
                clubIdListByUserId.add(clubId);
            }
        }

        for(Integer clubId: clubIdListByUserId){
            currentUserClubListWithADMIN.add(getClubService().getClubById(clubId));
        }
        return currentUserClubListWithADMIN;
    }

    public void setCurrentUserClubListWithADMIN(List<Club> currentUserClubList) {
        this.currentUserClubListWithADMIN = currentUserClubList;
    }

}
