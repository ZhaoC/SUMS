package com.capstone.prime.faces.beans;

/**
 * Created by  ZhaoC on 10/7/16.
 */
import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.capstone.managedController.ActivityManagedController;
import com.capstone.spring.model.Activity;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {

    private ScheduleModel eventModel;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    @ManagedProperty("#{actMB}")
    private
    ActivityManagedController activityManagedController;
    private List<Activity> currentUserActivityList;

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        String[] colors = {"EventStyle1", "EventStyle2", "EventStyle3"};
        Random random = new Random();
        //add user's activity to the calendar
        for(Activity activity: getCurrentUserActivityList()){
            int index = random.nextInt(colors.length);
            eventModel.addEvent(new DefaultScheduleEvent(activity.getActName(), new java.util.Date(activity.getActStartDate().getTime()),new java.util.Date(activity.getActEndDate().getTime()), colors[index]));
        }
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }


    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }


    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public List<Activity> getCurrentUserActivityList() {
        return getActivityManagedController().getCurrentUserActivityList();
    }

    public void setCurrentUserActivityList(List<Activity> currentUserActivityList) {
        this.currentUserActivityList = currentUserActivityList;
    }

    public ActivityManagedController getActivityManagedController() {
        return activityManagedController;
    }

    public void setActivityManagedController(ActivityManagedController activityManagedController) {
        this.activityManagedController = activityManagedController;
    }
}