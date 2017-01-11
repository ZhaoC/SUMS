package com.capstone.spring.service;

import java.util.List;

import com.capstone.spring.model.Activity;
import com.capstone.spring.dao.ActivityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Created by  ZhaoC on 10/13/16.
 */

@Service("ActivityService")
@Transactional(readOnly = true)
public class ActivityService {

    // ActivityDAO is injected...
    @Autowired
    ActivityDAO activityDAO;

    /**
     * Add activity
     *
     * @param  activity activity
     */
    @Transactional(readOnly = false)
    public void addActivity(Activity activity) {
        getActivityDAO().addActivity(activity);
    }

    /**
     * Delete activity
     *
     * @param   activity  activity
     */
    @Transactional(readOnly = false)
    public void deleteActivity(Activity activity) {
        getActivityDAO().deleteActivity(activity);
    }

    /**
     * Update Activity
     *
     * @param activity  Activity
     */
    @Transactional(readOnly = false)
    public void updateActivity(Activity activity) {
        getActivityDAO().updateActivity(activity);
    }

    /**
     * Get activity
     *
     * @param  id int Activity Id
     */

    public Activity getActivityById(int id) {
        return getActivityDAO().getActivityById(id);
    }

    /**
     * Get Activity List
     *
     */

    public List<Activity> getActivitys() {
        return getActivityDAO().getActivitys();
    }

    /**
     * Get Activity DAO
     *
     * @return activityDAO - Activity DAO
     */
    public ActivityDAO getActivityDAO() {
        return activityDAO;
    }

    /**
     * Set Activity DAO
     *
     * @param  activityDAO - ActivityDAO
     */
    public void setActivityDAO(ActivityDAO activityDAO) {
        this.activityDAO = activityDAO;
    }
}
