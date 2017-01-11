package com.capstone.managedController;

import com.capstone.spring.model.Activity;
import com.capstone.spring.model.ActivityRegistrationRequest;
import com.capstone.spring.model.User;
import com.capstone.spring.service.ActivityRegistrationRequestService;
import org.springframework.dao.DataAccessException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by  ZhaoC on 10/19/16.
 */
@ManagedBean(name="actRequestMB")
@ViewScoped
public class ActRegRequestController implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "ViewActivities";
    private static final String ERROR   = "error";
    private static final String DEFAULT_ACT_STATUS = "Approved";

    @ManagedProperty(value="#{ActivityRegistrationRequestService}")
    ActivityRegistrationRequestService activityRegistrationRequestService;

    @ManagedProperty("#{loginValidate}")
    LoginValidate loginValidate;

    @ManagedProperty(value="#{userMB}")
    private
    UserManagedController userManagedController;

    private List<ActivityRegistrationRequest> requestList;
    private List<ActivityRegistrationRequest> requestListByCurrentUserId;
    private List<Integer> actRequestIdByUserId;

    private int aRequestId;
    private int userId;
    private int actId;
    private Date requestDate;
    private String requestStatus;

    public String addActivityRegistrationRequest(){
        try{
            ActivityRegistrationRequest request = new ActivityRegistrationRequest();
            request.setaRequestId(getaRequestId());
            request.setActId(getActId());
            request.setUserId(getUserId());
            request.setRequestDate(new java.sql.Timestamp(getRequestDate().getTime()));
            request.setRequestStatus(getRequestStatus());

            getActivityRegistrationRequestService().addActivityRegistrationRequest(request);
            return SUCCESS;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return ERROR;
    }



    public ActivityRegistrationRequestService getActivityRegistrationRequestService() {
        return activityRegistrationRequestService;
    }

    public void setActivityRegistrationRequestService(ActivityRegistrationRequestService activityRegistrationRequestService) {
        this.activityRegistrationRequestService = activityRegistrationRequestService;
    }

    public LoginValidate getLoginValidate() {
        return loginValidate;
    }

    public void setLoginValidate(LoginValidate loginValidate) {
        this.loginValidate = loginValidate;
    }

    public List<ActivityRegistrationRequest> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<ActivityRegistrationRequest> requestList) {
        this.requestList = requestList;
    }

    public List<ActivityRegistrationRequest> getRequestListByCurrentUserId() {
        requestListByCurrentUserId = new ArrayList<ActivityRegistrationRequest>();
        int userId = loginValidate.getUserId();
        requestListByCurrentUserId.addAll(getActivityRegistrationRequestService().getActivityRegistrationRequestsByUserId(userId));

        return requestListByCurrentUserId;
    }

    public void setRequestListByCurrentUserId(List<ActivityRegistrationRequest> requestListByCurrentUserId) {
        this.requestListByCurrentUserId = requestListByCurrentUserId;
    }

    public List<Integer> getActRequestIdByUserId() {
        actRequestIdByUserId = new ArrayList<Integer>();

        for(ActivityRegistrationRequest request: getRequestListByCurrentUserId()){
            int actId = request.getActId();
            if(!actRequestIdByUserId.contains(actId)){
                actRequestIdByUserId.add(actId);
            }
        }
        return actRequestIdByUserId;
    }

    public void setActRequestIdByUserId(List<Integer> actRequestIdByUserId) {
        this.actRequestIdByUserId = actRequestIdByUserId;
    }

    public int getaRequestId() {
        return aRequestId;
    }

    public void setaRequestId(int aRequestId) {
        this.aRequestId = aRequestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void addActJoinRequestForUserJoinedAct(Activity joinedActivity){
        try{
            User currentUser = getUserManagedController().getCurrentUser();
            ActivityRegistrationRequest defaultRequest = new ActivityRegistrationRequest();

            defaultRequest.setActId(joinedActivity.getActId());
            defaultRequest.setUserId(currentUser.getUserId());
            defaultRequest.setRequestDate(new java.sql.Timestamp((new Date()).getTime()));
            defaultRequest.setRequestStatus(DEFAULT_ACT_STATUS);

            getActivityRegistrationRequestService().addActivityRegistrationRequest(defaultRequest);

        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    /**
     * create default joinAct request for joined activities
     */
    public void createJoinActRequestForSelectedActivities(List<Activity> activityList){
        for(Activity activity: activityList){
            addActJoinRequestForUserJoinedAct(activity);
        }
    }

    public UserManagedController getUserManagedController() {
        return userManagedController;
    }

    public void setUserManagedController(UserManagedController userManagedController) {
        this.userManagedController = userManagedController;
    }
}
