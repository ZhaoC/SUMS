package com.capstone.spring.service;

import com.capstone.spring.dao.ActivityRegistrationRequestDAO;
import com.capstone.spring.model.ActivityRegistrationRequest;
import com.capstone.spring.model.ActivityRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Created by  ZhaoC on 10/19/16.
 */
@Service("ActivityRegistrationRequestService")
@Transactional(readOnly = true)
public class ActivityRegistrationRequestService {
    @Autowired
    ActivityRegistrationRequestDAO activityRegistrationRequestDAO;

    @Transactional(readOnly = false)
    public void addActivityRegistrationRequest(ActivityRegistrationRequest activityRegistrationRequest) {
        getActivityRegistrationRequestDAO().addActivityRegistrationRequest(activityRegistrationRequest);
    }

    @Transactional(readOnly = false)
    public void deleteActivityRegistrationRequest(ActivityRegistrationRequest request) {
        getActivityRegistrationRequestDAO().deleteActivityRegistrationRequest(request);
    }

    @Transactional(readOnly = false)
    public void updateActivityRegistrationRequest(ActivityRegistrationRequest request) {
        getActivityRegistrationRequestDAO().updateActivityRegistrationRequest(request);
    }

    public ActivityRegistrationRequest getActivityRegistrationRequestById(int id) {
        return getActivityRegistrationRequestDAO().getActivityRegistrationRequestById(id);
    }

    public List<ActivityRegistrationRequest> getActivityRegistrationRequests() {
        return getActivityRegistrationRequestDAO().getRequests();
    }

    public List<ActivityRegistrationRequest> getActivityRegistrationRequestsByUserId(int userId){
        return getActivityRegistrationRequestDAO().getRequestsByUserId(userId);
    }


    public ActivityRegistrationRequestDAO getActivityRegistrationRequestDAO() {
        return activityRegistrationRequestDAO;
    }

    public void setActivityRegistrationRequestDAO(ActivityRegistrationRequestDAO activityRegistrationRequestDAO) {
        this.activityRegistrationRequestDAO = activityRegistrationRequestDAO;
    }
}
