package com.capstone.managedController;

import com.capstone.slackChat.SlackController;
import com.capstone.spring.model.User;
import com.capstone.spring.service.UserService;
import org.springframework.dao.DataAccessException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chan on 10/11/2016.
 */
@ManagedBean(name="userMB")
@SessionScoped
public class UserManagedController implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "ViewUser";
    private static final String ERROR   = "error";
    private static final String REGISTER_SUCCESS = "register_success";
    private static final String REGISTER_FAIL = "register_error";

    @ManagedProperty(value="#{UserService}")
    UserService userService;

    @ManagedProperty("#{loginValidate}")
    LoginValidate loginValidate;

    List<User> userList;

    /**
     * User Entity Parameters
     */
    private int userId;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userGender;
    private Date userBirth;
    private String userEmail;
    private String userPhone;

    /**
     * request parameters
     */
    private String currentUserName;

    private User registeredUser;

    private User currentUser;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public String addUser() {
        try {
            User user = new User();
            user.setUserId(getUserId());
            user.setUserFirstName(getUserFirstName());
            user.setUserLastName(getUserLastName());
            user.setUserPassword(getUserPassword());
            user.setUserGender(getUserGender());
            user.setUserBirth(new java.sql.Date(getUserBirth().getTime()));
            user.setUserEmail(getUserEmail());
            user.setUserPhone(getUserPhone());

            getUserService().addUser(user);
            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return ERROR;
    }

    public String registerUser() {
        try {
//          if(getUserService().getUserByEmail(getUserEmail()) == null){
            User user = new User();
            user.setUserId(getUserId());
            user.setUserFirstName(getUserFirstName());
            user.setUserLastName(getUserLastName());
            user.setUserPassword(getUserPassword());
            user.setUserGender(getUserGender());
            user.setUserBirth(new java.sql.Date(getUserBirth().getTime()));
            user.setUserEmail(getUserEmail());
            user.setUserPhone(getUserPhone());

            getUserService().addUser(user);
            //get the registered user
            registeredUser = user;


            //Invite the new registered user to Slack by Email
            //SlackController.inviteUserByEmail(getUserEmail());

            //Send email for new user
            //RegisterEmailController.sendEmail(getUserEmail(), getUserFirstName(), getUserLastName(), getUserPassword());

            return REGISTER_SUCCESS;
//
//            } else{
//                return REGISTER_FAIL;
//            }

        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return REGISTER_FAIL;
    }

    /**
     * Reset Fields
     *
     */
    public void reset() {
        this.setUserId(0);
        this.setUserFirstName("");
        this.setUserLastName("");
        this.setUserPassword("");
        this.setUserGender("");
        this.setUserBirth(new Date());
        this.setUserEmail("");
        this.setUserPhone("");
    }

    /**
     * Reset Fields
     *
     */
    public void resetRegisterField() {
        this.setUserFirstName("");
        this.setUserLastName("");
        this.setUserPassword("");
        this.setUserGender("");
        this.setUserBirth(new Date());
        this.setUserEmail("");
        this.setUserPhone("");
    }
    /**
     * Update user
     *
     *
     */
    public void updateUser(){
        try {
            User updatedUser = getCurrentUser();

            if(!getUserFirstName().isEmpty()){
                updatedUser.setUserFirstName(getUserFirstName());
            }
            if(!getUserLastName().isEmpty()){
                updatedUser.setUserLastName(getUserLastName());
            }
            if(!getUserEmail().isEmpty()){
                updatedUser.setUserEmail(getUserEmail());
            }
            if(!getUserPhone().isEmpty()){
                updatedUser.setUserPhone(getUserPhone());
            }

            getUserService().updateUser(updatedUser);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentUserNameById(){
        User user = getUserService().getUserById(getLoginValidate().getUserId());
        return user.getUserFirstName() + " " + user.getUserLastName();
    }

    public String getCurrentUserName() {
        User user = getUserService().getUserById(getLoginValidate().getUserId());
        currentUserName = user.getUserFirstName() + " " + user.getUserLastName();
        return currentUserName;
    }

    /**
     * Update user password
     */
    public void updateUserPassword(){

        try{
            User updatedUser = getCurrentUser();

            if(!getUserPassword().isEmpty() && getUserPassword().equals(updatedUser.getUserPassword())){
                if(!getUserFirstName().isEmpty() && !getUserLastName().isEmpty() && getUserFirstName().equals(getUserLastName())){
                    updatedUser.setUserPassword(getUserFirstName());
                }
            }
            getUserService().updateUser(updatedUser);
        }catch (DataAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get User List
     *
     * @return List - User List
     */
    public List<User> getUserList() {
        userList = new ArrayList<User>();
        userList.addAll(getUserService().getUsers());
        return userList;
    }


    public void setUserList(List<User> userList) {
        this.userList = userList;
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

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Date getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public LoginValidate getLoginValidate() {
        return loginValidate;
    }

    public void setLoginValidate(LoginValidate loginValidate) {
        this.loginValidate = loginValidate;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public User getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(User registeredUser) {
        this.registeredUser = registeredUser;
    }

    public User getCurrentUser() {
       return getUserService().getUserById(getLoginValidate().getUserId());

    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
