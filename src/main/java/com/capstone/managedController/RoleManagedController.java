package com.capstone.managedController;

import com.capstone.spring.model.Club;
import com.capstone.spring.model.Role;
import com.capstone.spring.model.User;
import com.capstone.spring.service.RoleService;
import org.springframework.dao.DataAccessException;

import javax.faces.bean.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZhaoC on 10/15/16.
 */

@ManagedBean(name="roleMB")
@SessionScoped
public class RoleManagedController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "ViewRole";
    private static final String ERROR   = "error";
    private static final String MANAGER_TITLE = "Manager";
    private static final String ADMIN_ROLE_PRIVILEGE = "ADMIN";
    private static final String DEFAULT_ROLE_STATUS = "ACTIVE";
    private static final String MEMBER_TITLE = "Member";
    private static final String GUEST_ROLE_PRIVILEGE = "GUEST";

    @ManagedProperty(value="#{userMB}")
    private
    UserManagedController userManagedController;

    @ManagedProperty(value="#{RoleService}")
    RoleService roleService;

    @ManagedProperty("#{loginValidate}")
    LoginValidate loginValidate;

    List<Role> roleList;
    List<Role> roleListByCurrentUserId;
    List<Integer> clubIdListByUserId;
    List<Role> roleListBySelectedClubId;
    private int selectedClubId;
    private Boolean isUserAManager;

    /**
     * Entity parameters
     */
    private int roleId;
    private String roleTitle;
    private String rolePrivilege;
    private int userId;
    private int clubId;
    private Date roleInitDate;
    private String roleStatus;

    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }

    private Role selectedRole;
    public RoleService getRoleService(){
        return roleService;
    }

    public void setRoleService(RoleService roleService){
        this.roleService = roleService;
    }

    public String addRole(){
        try{
            Role role = new Role();
            role.setRoleId(getRoleId());
            role.setRoleTitle(getRoleTitle());
            role.setRolePrivilege(getRolePrivilege());
            role.setUserId(getUserId());
            role.setClubId(getSelectedClubId());
            role.setRoleInitDate(new java.sql.Date(getRoleInitDate().getTime()));
            role.setRoleStatus(getRoleStatus());

            getRoleService().addRole(role);
            return SUCCESS;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    public String managerAddRole(){
        try{
            Role role = new Role();
            role.setRoleId(getRoleId());
            role.setRoleTitle(getRoleTitle());
            role.setRolePrivilege(getRolePrivilege());
            role.setUserId(getUserId());
            role.setClubId(getSelectedClubId());
            role.setRoleInitDate(new java.sql.Date(getRoleInitDate().getTime()));
            role.setRoleStatus(getRoleStatus());

            getRoleService().addRole(role);
            return "roleList";
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    public void reset(){
        this.setRoleId(0);
        this.setRoleTitle("");
        this.setRolePrivilege("");
        this.setUserId(0);
        this.setClubId(0);
        this.setRoleInitDate(new Date());
        this.setRoleStatus("");
    }

    public List<Role> getRoleList(){
        roleList = new ArrayList<Role>();
        roleList.addAll(getRoleService().getRoles());
        return roleList;
    }

    public void addManagerRoleToUserCreatedNewClub(Club newClub){
        try{
            User currentUser = getUserManagedController().getCurrentUser();
            Role defaultManagerRole = new Role();

            defaultManagerRole.setRoleTitle(MANAGER_TITLE);
            defaultManagerRole.setRolePrivilege(ADMIN_ROLE_PRIVILEGE);
            defaultManagerRole.setUserId(currentUser.getUserId());
            defaultManagerRole.setClubId(newClub.getClubId());
            defaultManagerRole.setRoleInitDate(new java.sql.Date(newClub.getClubInitDate().getTime()));
            defaultManagerRole.setRoleStatus(DEFAULT_ROLE_STATUS);

            getRoleService().addRole(defaultManagerRole);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }

    public void addMemberRoleToUserJoinedNewClub(Club joinedClub){
        try{
            User currentUser = getUserManagedController().getCurrentUser();
            Role defaultMemberRole = new Role();

            defaultMemberRole.setRoleTitle(MEMBER_TITLE);
            defaultMemberRole.setRolePrivilege(GUEST_ROLE_PRIVILEGE);
            defaultMemberRole.setUserId(currentUser.getUserId());
            defaultMemberRole.setClubId(joinedClub.getClubId());
            defaultMemberRole.setRoleInitDate(new java.sql.Date(joinedClub.getClubInitDate().getTime()));
            defaultMemberRole.setRoleStatus(DEFAULT_ROLE_STATUS);

            getRoleService().addRole(defaultMemberRole);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }

    public List<Integer> getClubIdListByUserId() {
        clubIdListByUserId = new ArrayList<Integer>();
        for(Role role: getRoleListByCurrentUserId()){
            int clubId = role.getClubId();
            if(!clubIdListByUserId.contains(clubId)){
                clubIdListByUserId.add(clubId);
            }
        }
        return clubIdListByUserId;
    }

    public List<Role> getRoleListByCurrentUserId() {
        roleListByCurrentUserId = new ArrayList<Role>();
        int userId = loginValidate.getUserId();
        roleListByCurrentUserId.addAll(getRoleService().getRolesByUserId(userId));

        return roleListByCurrentUserId;
    }

    public void setRoleListByCurrentUserId(List<Role> roleListByCurrentUserId) {
        this.roleListByCurrentUserId = roleListByCurrentUserId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getRolePrivilege() {
        return rolePrivilege;
    }

    public void setRolePrivilege(String rolePrivilege) {
        this.rolePrivilege = rolePrivilege;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public Date getRoleInitDate() {
        return roleInitDate;
    }

    public void setRoleInitDate(Date roleInitDate) {
        this.roleInitDate = roleInitDate;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public LoginValidate getLoginValidate() {
        return loginValidate;
    }

    public void setLoginValidate(LoginValidate loginValidate) {
        this.loginValidate = loginValidate;
    }

    public void setClubIdListByUserId(List<Integer> clubIdListByUserId) {
        this.clubIdListByUserId = clubIdListByUserId;
    }


    public Boolean getIsUserAManager() {
        isUserAManager = false;
        for(Role role: getRoleListByCurrentUserId()){
            if(role.getRolePrivilege().equals("ADMIN")){
                isUserAManager = true;
            }
        }
        return isUserAManager;
    }

    public void setIsUserAManager(Boolean isUserAManager) {
        this.isUserAManager = isUserAManager;
    }


    public UserManagedController getUserManagedController() {
        return userManagedController;
    }

    public void setUserManagedController(UserManagedController userManagedController) {
        this.userManagedController = userManagedController;
    }

    /**
     * create default member role for joined clubs
     */
    public void createMemberRolesForUserSelectedClubs(List<Club> clubList){
        for(Club club: clubList){
            addMemberRoleToUserJoinedNewClub(club);
        }
    }

    /**
     * author: Jason Cobbledick
     * @return
     */
    public List<Role> getRoleListBySelectedClubId() {
        roleListBySelectedClubId = new ArrayList<Role>();
        roleListBySelectedClubId.addAll(getRoleService().getRolesByClubId(selectedClubId));

        return roleListBySelectedClubId;
    }

    /**
     * author: Jason Cobbledick
     * @return
     */
    public void setRoleListBySelectedClubId(List<Role> roleListBySelectedClubId) {
        this.roleListBySelectedClubId = roleListBySelectedClubId;
    }

    /**
     * author: Jason Cobbledick
     * @return
     */
       public String chooseClubForMember(){
                try{
                    System.out.println("***************************************"+selectedClubId);
                    roleListBySelectedClubId = new ArrayList<Role>();
                    roleListBySelectedClubId = getRoleListBySelectedClubId();
                    for(Role role: roleListByCurrentUserId){
                        System.out.println(role.getRoleId()+"***********************************");
                    }
                    return "users";
                   }catch (DataAccessException e){
                       e.printStackTrace();
                   }
              return ERROR;
       }

    public String chooseClub(){
        try{
            System.out.println("***************************************"+selectedClubId);
            roleListBySelectedClubId = new ArrayList<Role>();
            roleListBySelectedClubId = getRoleListBySelectedClubId();
            for(Role role: roleListByCurrentUserId){
                System.out.println(role.getRoleId()+"***********************************");
            }
            return "roleList";
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    public int getSelectedClubId(){
        return selectedClubId;
    }

    public void setSelectedClubId(int selectedClubId){
        this.selectedClubId = selectedClubId;
    }

    public void updateEditedRole(){
        try {
            if(!getRoleTitle().isEmpty()){
                selectedRole.setRoleTitle(getRoleTitle());
            }
            if(!getRolePrivilege().isEmpty()){
                selectedRole.setRolePrivilege(getRolePrivilege());
            }
            if(!getRoleStatus().isEmpty()){
                selectedRole.setRoleStatus(getRoleStatus());
            }


            getRoleService().updateRole(selectedRole);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
