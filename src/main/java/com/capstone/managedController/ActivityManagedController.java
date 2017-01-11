package com.capstone.managedController;


import com.capstone.spring.model.Activity;
import com.capstone.spring.service.ActivityService;
import org.springframework.dao.DataAccessException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by  ZhaoC on 10/13/16.
 */

@ManagedBean(name = "actMB")
@ViewScoped
public class ActivityManagedController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "ViewActivities";
    private static final String ERROR = "error";

    //Spring Customer Service is injected...
    @ManagedProperty(value = "#{ActivityService}")
    ActivityService activityService;

    @ManagedProperty("#{loginValidate}")
    private
    LoginValidate loginValidate;

    @ManagedProperty("#{actRequestMB}")
    private
    ActRegRequestController actRegRequestController;

    /**
     * Entity Parameters
     */
    private int actId;
    private int clubId;
    private String actName;
    private Date actStartDate;
    private Date actEndDate;
    private String actStatus;
    private int actCapacity;
    private String actLocation;
    private Integer actBudget;
    private int actAssignee;
    private String actNote;

    private Activity selectedAct;
    private List<Activity> selectedActs;

    /**
     * Requested
     */
    private List<Activity> activityList;
    private List<Activity> currentUserActivityList;

    private Activity selectedActivity;
    private List<Activity> selectedActivities;
    private List<Activity> currentUserNotJoinedActivityList;

    /**
     * Add Customer
     *
     * @return String - Response Message
     */
    public String addActivity() {
        try {
            Activity activity = new Activity();
            activity.setActId(getActId());
            activity.setClubId(getClubId());
            activity.setActName(getActName());
            activity.setActStartDate(new java.sql.Timestamp(getActStartDate().getTime()));
            activity.setActEndDate(new java.sql.Timestamp(getActEndDate().getTime()));
            activity.setActStatus(getActStatus());
            activity.setActCapacity(getActCapacity());
            activity.setActLocation(getActLocation());
            activity.setActBudget(getActBudget());
            activity.setActAssignee(getActAssignee());
            activity.setActNote(getActNote());

            getActivityService().addActivity(activity);
            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return ERROR;
    }

    public void deleteActivity() {
        try {
            Activity activity = new Activity();
            activity = getActivityService().getActivityById(getActId());
            getActivityService().deleteActivity(activity);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public void updateActivity() {
        try {
            Activity activity = new Activity();
            activity.setClubId(getClubId());
            activity.setActName(getActName());
            activity.setActStartDate(new java.sql.Timestamp(getActStartDate().getTime()));
            activity.setActEndDate(new java.sql.Timestamp(getActEndDate().getTime()));
            activity.setActStatus(getActStatus());
            activity.setActCapacity(getActCapacity());
            activity.setActLocation(getActLocation());
            activity.setActBudget(getActBudget());
            activity.setActAssignee(getActAssignee());
            activity.setActNote(getActNote());

            getActivityService().updateActivity(activity);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reset Fields
     */
    public void reset() {

        this.setActId(0);
        this.setClubId(0);
        this.setActName("");
        this.setActStartDate(new Date());
        this.setActEndDate(new Date());
        this.setActStatus("");
        this.setActCapacity(0);
        this.setActLocation("");
        this.setActBudget(0);
        this.setActAssignee(0);
        this.setActNote("");
    }

    /**
     * Get Activity List
     *
     * @return List - Activity List
     */
    public List<Activity> getActivityList() {
        activityList = new ArrayList<Activity>();
        activityList.addAll(getActivityService().getActivitys());
        return activityList;
    }


    public List<Activity> getCurrentUserActivityList() {
        currentUserActivityList = new ArrayList<Activity>();

        for (Integer actId : actRegRequestController.getActRequestIdByUserId()) {
            currentUserActivityList.add(getActivityService().getActivityById(actId));
        }

        return currentUserActivityList;
    }

    public void setCurrentUserActivityList(List<Activity> currentUserActivityList) {
        this.currentUserActivityList = currentUserActivityList;
    }

    public List<Activity> getActivityListByUserID() {
        activityList = new ArrayList<Activity>();
        activityList.addAll(getActivityService().getActivitys());
        return activityList;
    }

    public LoginValidate getLoginValidate() {
        return loginValidate;
    }

    public void setLoginValidate(LoginValidate loginValidate) {
        this.loginValidate = loginValidate;
    }

    public ActRegRequestController getActRegRequestController() {
        return actRegRequestController;
    }

    public void setActRegRequestController(ActRegRequestController actRegRequestController) {
        this.actRegRequestController = actRegRequestController;
    }

    public ActivityService getActivityService() {
        return activityService;
    }

    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public Date getActStartDate() {
        return actStartDate;
    }

    public void setActStartDate(Date actStartDate) {
        this.actStartDate = actStartDate;
    }

    public Date getActEndDate() {
        return actEndDate;
    }

    public void setActEndDate(Date actEndDate) {
        this.actEndDate = actEndDate;
    }

    public String getActStatus() {
        return actStatus;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

    public int getActCapacity() {
        return actCapacity;
    }

    public void setActCapacity(int actCapacity) {
        this.actCapacity = actCapacity;
    }

    public String getActLocation() {
        return actLocation;
    }

    public void setActLocation(String actLocation) {
        this.actLocation = actLocation;
    }

    public Integer getActBudget() {
        return actBudget;
    }

    public void setActBudget(Integer actBudget) {
        this.actBudget = actBudget;
    }

    public int getActAssignee() {
        return actAssignee;
    }

    public void setActAssignee(int actAssignee) {
        this.actAssignee = actAssignee;
    }

    public String getActNote() {
        return actNote;
    }

    public void setActNote(String actNote) {
        this.actNote = actNote;
    }

    public Activity getSelectedActivity() {
        return selectedActivity;
    }

    public void setSelectedActivity(Activity selectedActivity) {
        this.selectedActivity = selectedActivity;
    }

    public List<Activity> getCurrentUserNotJoinedActivityList() {
        List<Activity> allActivitiesForClubUserJoined = getActivityList();
        List<Activity> userJoinedActivities = getCurrentUserActivityList();
        currentUserNotJoinedActivityList = new ArrayList<Activity>();

        for (int i = 0; i < allActivitiesForClubUserJoined.size(); i++) {
            if (!userJoinedActivities.contains(allActivitiesForClubUserJoined.get(i))) {
                currentUserNotJoinedActivityList.add(allActivitiesForClubUserJoined.get(i));
            }
        }
        return currentUserNotJoinedActivityList;
    }

    public void setCurrentUserNotJoinedActivityList(List<Activity> currentUserNotJoinedActivityList) {
        this.currentUserNotJoinedActivityList = currentUserNotJoinedActivityList;
    }

    public List<Activity> getSelectedActivities() {
        return selectedActivities;
    }

    public void setSelectedActivities(List<Activity> selectedActivities) {
        this.selectedActivities = selectedActivities;
    }

    /**
     * join selected clubs for the current user
     */
    public String joinSelectedActivitiesForCurrentUser() {
        List<Activity> userSelectedActivities = getSelectedActivities();
        getActRegRequestController().createJoinActRequestForSelectedActivities(userSelectedActivities);
        return SUCCESS;
    }
}
