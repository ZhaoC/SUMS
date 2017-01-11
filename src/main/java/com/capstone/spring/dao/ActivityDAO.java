package com.capstone.spring.dao;

import java.util.List;
import com.capstone.spring.model.Activity;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * Created by  ZhaoC on 10/13/16.
 */

@Repository
public class ActivityDAO {
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

    /**
     * Add Activity
     *
     * @param   activity activity
     */

    public void addActivity(Activity activity) {
        getSessionFactory().getCurrentSession().save(activity);
    }

    /**
     * Delete activity
     *
     * @param   activity  activity
     */

    public void deleteActivity(Activity activity) {
        getSessionFactory().getCurrentSession().delete(activity);
    }

    /**
     * Update activity
     *
     * @param  activity activity
     */

    public void updateActivity(Activity activity) {
        getSessionFactory().getCurrentSession().update(activity);
    }

    /**
     * Get activity
     *
     * @param  actId int
     * @return activity
     */

    public Activity getActivityById(int actId) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Activity  where actId=?")
                .setParameter(0, actId).list();
        return (Activity)list.get(0);
    }

    /**
     * Get activity List
     *
     * @return List - customer list
     */

    public List<Activity> getActivitys() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  Activity").list();
        return list;
    }
}
