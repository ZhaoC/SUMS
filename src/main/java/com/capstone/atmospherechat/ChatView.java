package com.capstone.atmospherechat;

/**
 * Created by ZhaoC on 1/14/17.
 */
import java.io.Serializable;

import com.capstone.managedController.LoginValidate;
import com.capstone.spring.model.User;
import com.capstone.spring.service.UserService;
import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ChatView implements Serializable {

    //private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();

    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

    @ManagedProperty("#{chatUsers}")
    private ChatUsers users;

    @ManagedProperty("#{loginValidate}")
    private
    LoginValidate loginValidate;

    @ManagedProperty(value="#{UserService}")
    private
    UserService userService;

    private String privateMessage;

    private String globalMessage;

    static private String username;

    private boolean loggedIn;

    private String privateUser;

    private final static String CHANNEL = "/{room}/";

    public ChatUsers getUsers() {
        return users;
    }

    public void setUsers(ChatUsers users) {
        this.users = users;
    }

    public String getPrivateUser() {
        return privateUser;
    }

    public void setPrivateUser(String privateUser) {
        this.privateUser = privateUser;
    }

    public String getGlobalMessage() {
        return globalMessage;
    }

    public void setGlobalMessage(String globalMessage) {
        this.globalMessage = globalMessage;
    }

    public String getPrivateMessage() {
        return privateMessage;
    }

    public void setPrivateMessage(String privateMessage) {
        this.privateMessage = privateMessage;
    }

    public String getUsername() {
//        User user = getUserService().getUserById(getLoginValidate().getUserId());
//        return user.getUserFirstName() + " " + user.getUserLastName();
//
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void sendGlobal() {
        eventBus.publish(CHANNEL + "*", username + ": " + globalMessage);

        globalMessage = null;
    }

    public void sendPrivate() {
        eventBus.publish(CHANNEL + privateUser, "[PM] " + username + ": " + privateMessage);

        privateMessage = null;
    }

    public void login() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
//        username = getUsername();
        if(users.contains(username)) {
            loggedIn = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username taken", "Try with another username."));
            requestContext.update("growl");
        }
        else {
            users.add(username);
            requestContext.execute("PF('subscriber').connect('/" + username + "')");
            loggedIn = true;
        }
    }

    public void disconnect() {
        //remove user and update ui
        users.remove(username);
        RequestContext.getCurrentInstance().update("form:users");

        //push leave information
        eventBus.publish(CHANNEL + "*", username + " left the channel.");

        //reset state
        loggedIn = false;
        username = null;
    }

    public LoginValidate getLoginValidate() {
        return loginValidate;
    }

    public void setLoginValidate(LoginValidate loginValidate) {
        this.loginValidate = loginValidate;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}