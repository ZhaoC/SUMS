package com.capstone.managedController;

import com.capstone.spring.model.Club;
import com.capstone.spring.model.Role;
import com.capstone.spring.service.ClubService;
import com.capstone.spring.service.RoleService;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.dao.DataAccessException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by Chan Yu on 10/10/16.
 */

@ManagedBean(name="clubMB")
@ViewScoped
public class ClubManagedController implements Serializable {

    private static final String DEFAULT_ACTIVE_STATUS = "ACTIVE";
    private static final String DEFAULT_INIT_STATUS = "INIT";
    private static final String RENDER_MEMBER_CLUB = "member_clubs";

    //Spring Customer Service is injected...
    @ManagedProperty(value="#{ClubService}")
    ClubService clubService;

    @ManagedProperty(value="#{RoleService}")
    private
    RoleService roleService;

    @ManagedProperty("#{roleMB}")
    RoleManagedController roleManagedController;

    @ManagedProperty("#{loginValidate}")
    LoginValidate loginValidate;

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "ViewClub";
    private static final String ERROR   = "error";


    /**
     * Club Entity Parameters
     */
    private int clubId;
    private String clubName;
    private String clubCategory;
    private Date clubInitDate;
    private String clubStatus;
    private String clubNote;
    private int clubOwner;

    /**
     * requested parameter
     */
    List<Club> clubList;
    List<Club> currentUserClubList;
    private List<Club> currentUserClubListWithAdminRole;
    private int currentUserId;
    private Club selectedClub;
    private List<Club> selectedClubs;
    private List<Club> currentUserNotJoinedClubList;

    public String addClub() {
        try {
            Club club = new Club();
            club.setClubId(getClubId());
            club.setClubName(getClubName());
            club.setClubCategory(getClubCategory());
            club.setClubInitDate(new java.sql.Date(getClubInitDate().getTime()));
            club.setClubStatus(getClubStatus());
            club.setClubNote(getClubNote());
            club.setClubOwner(getClubOwner());

            getClubService().addClub(club);

            return "member_clubs";
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return ERROR;
    }

    public String currentUserCreateNewClub() {
        try {
            Club club = new Club();
            club.setClubId(getClubId());
            club.setClubName(getClubName());
            club.setClubCategory(getClubCategory());
            club.setClubInitDate(new java.sql.Date(getClubInitDate().getTime()));
            //club.setClubStatus(getClubStatus());
            club.setClubStatus(DEFAULT_INIT_STATUS);
            club.setClubNote(getClubNote());
            club.setClubOwner(getCurrentUserId());

            //get all club list
            List<Club> allClubsInDB = getClubService().getClubs();
            boolean isExist = false;
            for(Club myClub: clubList){
                if(club.getClubName().equalsIgnoreCase(myClub.getClubName())){
                    isExist = true;
                }
            }

            if(isExist){
                return SUCCESS;
            } else {

                //associate with Slack
                //associateSlack();

                //add club
                getClubService().addClub(club);
                Club justCreateClub = getClubService().getClubByStatusAndInitUser(DEFAULT_INIT_STATUS);

                //add manager role to user and update club status to active
                roleManagedController.addManagerRoleToUserCreatedNewClub(justCreateClub);
                justCreateClub.setClubStatus(DEFAULT_ACTIVE_STATUS);
                getClubService().updateClub(justCreateClub);

                return SUCCESS;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return ERROR;
    }

//    private String associateSlack() {
//
//        if(!SlackController.getUserByEmail(this.getLoginValidate().getUserEmail()).equalsIgnoreCase("DO NOT FOUND") ){
//
//            //Get userId for current user
//            JSONObject user = new JSONObject(SlackController.getUserByEmail(this.getLoginValidate().getUserEmail()));
//            String userId = user.getString("id");
//
//            //Add new private channel/group in slack for the new club
//            JSONObject response = new JSONObject( SlackController.createGroup(getClubName()));
//            if( response.getBoolean("ok")) {
//                //Get groupId for the club
//                JSONObject channel = new JSONObject(SlackController.getGroupByName(getClubName()));
//                String groupId = channel.getString("id");
//
//
//                //Invite the club founder into the channel
//                SlackController.inviteUserToGroup(groupId, userId);
//                System.out.println("-------------***************************************");
//                return SUCCESS;
//            }
//            else{//the club name is taken for other one already
//                return ERROR;
//            }
//        }else {  //user doesn't set Slack account yet
//
//            return ERROR;
//        }
//
//    }

    public void deleteClub(){
        try {
            Club club = new Club();
            club = getClubService().getClubById(getClubId());
            getClubService().deleteClub(club);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reset Fields
     *
     */
    public void reset() {
        this.setClubId(0);
        this.setClubName("");
        this.setClubCategory("");
        this.setClubInitDate(new Date());
        this.setClubStatus("");
        this.setClubNote("");
    }

    public void resetCreateClubFormFields() {
        this.setClubName("");
        this.setClubCategory("");
        this.setClubInitDate(new Date());
        this.setClubNote("");
    }

    /**
     * Get Club List
     *
     * @return List - Club List
     */
    public List<Club> getClubList() {
        clubList = new ArrayList<Club>();
        clubList.addAll(getClubService().getClubs());
        return clubList;
    }

    public ClubService getClubService() {
        return clubService;
    }

    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    public void setClubList(List<Club> clubList) {
        this.clubList = clubList;
    }



    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubCategory() {
        return clubCategory;
    }

    public void setClubCategory(String clubCategory) {
        this.clubCategory = clubCategory;
    }

    public Date getClubInitDate() {
        return clubInitDate;
    }

    public void setClubInitDate(Date clubInitDate) {
        this.clubInitDate = clubInitDate;
    }

    public String getClubStatus() {
        return clubStatus;
    }

    public void setClubStatus(String clubStatus) {
        this.clubStatus = clubStatus;
    }

    public String getClubNote() {
        return clubNote;
    }

    public void setClubNote(String clubNote) {
        this.clubNote = clubNote;
    }

    public int getClubOwner() {
        return clubOwner;
    }

    public void setClubOwner(int clubOwner) {
        this.clubOwner = clubOwner;
    }

    public List<Club> getCurrentUserClubList() {
        currentUserClubList = new ArrayList<Club>();

        for(Integer clubId: roleManagedController.getClubIdListByUserId()){
            currentUserClubList.add(getClubService().getClubById(clubId));
        }
        return currentUserClubList;
    }

    public void setCurrentUserClubList(List<Club> currentUserClubList) {
        this.currentUserClubList = currentUserClubList;
    }

    public RoleManagedController getRoleManagedController() {
        return roleManagedController;
    }

    public void setRoleManagedController(RoleManagedController roleManagedController) {
        this.roleManagedController = roleManagedController;
    }

    public LoginValidate getLoginValidate() {
        return loginValidate;
    }

    public void setLoginValidate(LoginValidate loginValidate) {
        this.loginValidate = loginValidate;
    }

    public int getCurrentUserId() {
        return getLoginValidate().getUserId();

    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    /**
     *Requested Parameters
     */
    public Club getSelectedClub() {
        return selectedClub;
    }

    public void setSelectedClub(Club selectedClub) {
        this.selectedClub = selectedClub;
    }

    public List<Club> getSelectedClubs() {
        return selectedClubs;
    }

    public void setSelectedClubs(List<Club> selectedClubs) {
        this.selectedClubs = selectedClubs;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Club Selected", ((Club) event.getObject()).getClubName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Club Unselected", ((Club) event.getObject()).getClubName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * join selected clubs for the current user
     */
    public String joinSelectedClubsForCurrentUser(){
        List<Club> userSelectedClubs = getSelectedClubs();
        getRoleManagedController().createMemberRolesForUserSelectedClubs(userSelectedClubs);

        //Chan
        //Invite user into Slack group when they join in the club
//        //Get user id
//        if(!SlackController.getUserByEmail(this.getLoginValidate().getUserEmail()).equalsIgnoreCase("DO NOT FOUND")){
//            JSONObject user = new JSONObject(SlackController.getUserByEmail(this.getLoginValidate().getUserEmail()));
//            String userId = user.getString("id");
//            System.out.print(userId);
//
//            for( Club c : userSelectedClubs){
//                //Get each group id
//                String clubName = c.getClubName().toLowerCase();
//                clubName = clubName.replace(" ", "-");
//                JSONObject group = new JSONObject(SlackController.getGroupByName(clubName));
//                String groupId = group.getString("id");
//                SlackController.inviteUserToGroup(groupId, userId);
//            }
//        }
//        else{
//            return ERROR;
//        }

        return SUCCESS;
    }

    public List<Club> getCurrentUserNotJoinedClubList() {
        List<Club> allClubs = getClubList();
        List<Club> userJoinedClubs = getCurrentUserClubList();
        currentUserNotJoinedClubList= new ArrayList<Club>();

        for(int i=0; i<allClubs.size(); i++){
            if(!userJoinedClubs.contains(allClubs.get(i))){
                currentUserNotJoinedClubList.add(allClubs.get(i));
            }
        }
        return currentUserNotJoinedClubList;
    }

    public void setCurrentUserNotJoinedClubList(List<Club> currentUserNotJoinedClubList) {
        this.currentUserNotJoinedClubList = currentUserNotJoinedClubList;
    }



    /**
     * author: Jason Cobbledick
     */
    public void updateEditedClub(){
        try {
            if(!getClubName().isEmpty()){
                selectedClub.setClubName(getClubName());
            }
            if(!getClubCategory().isEmpty()){
                selectedClub.setClubCategory(getClubCategory());
            }
            if(!getClubStatus().isEmpty()){
                selectedClub.setClubStatus(getClubStatus());
            }
            if(!getClubNote().isEmpty()){
                selectedClub.setClubNote(getClubNote());
            }

            getClubService().updateClub(selectedClub);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public List<Club> getCurrentUserClubListWithAdminRole() {
        currentUserClubListWithAdminRole = new ArrayList<Club>();
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
            currentUserClubListWithAdminRole.add(getClubService().getClubById(clubId));
        }

        List<Club> AdminRoleClubList = new ArrayList<Club>();
        AdminRoleClubList = getCurrentUserClubList();
        return currentUserClubListWithAdminRole;
    }

    public void setCurrentUserClubListWithAdminRole(List<Club> currentUserClubListWithAdminRole) {
        this.currentUserClubListWithAdminRole = currentUserClubListWithAdminRole;
    }
}