package com.capstone.spring.dao;

import com.capstone.spring.model.ActivityRegistrationRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Created by  ZhaoC on 10/19/16.
 */
@Repository
public class ActivityRegistrationRequestDAO {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Get Hibernate Session Factory
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Set Hibernate Session Factory
     *
     * @param sessionFactory SessionFactory - Hibernate Session Factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addActivityRegistrationRequest(ActivityRegistrationRequest activityRegistrationRequest) {
        getSessionFactory().getCurrentSession().save(activityRegistrationRequest);
    }


    public void deleteActivityRegistrationRequest(ActivityRegistrationRequest activityRegistrationRequest) {
        getSessionFactory().getCurrentSession().delete(activityRegistrationRequest);
    }

    public void updateActivityRegistrationRequest(ActivityRegistrationRequest activityRegistrationRequest) {
        getSessionFactory().getCurrentSession().update(activityRegistrationRequest);
    }

    public ActivityRegistrationRequest getActivityRegistrationRequestById(int aRequestId) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from ActivityRegistrationRequest  where aRequestId=?")
                .setParameter(0, aRequestId).list();
        return (ActivityRegistrationRequest)list.get(0);
    }

    public List<ActivityRegistrationRequest> getRequests(){
        List list = getSessionFactory().getCurrentSession().createQuery("from ActivityRegistrationRequest").list();
        return list;
    }

    public List<ActivityRegistrationRequest> getRequestsByUserId(int userId){
        List list = getSessionFactory().getCurrentSession().createQuery("from ActivityRegistrationRequest" +
                " where userId=? and requestStatus=?").setParameter(0,userId).setParameter(1, "Approved").list();
        return list;
    }
}
