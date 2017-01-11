package com.capstone.spring.dao;

import com.capstone.spring.model.ClubRegistrationRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Chan on 10/20/2016.
 */

@Repository
public class ClubRegistrationRequestDAO {
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
     * Add Club Registration Request
     *
     * @param   clubRegistrationRequest clubRegistrationRequest
     */
    public void addClubRegistrationRequest(ClubRegistrationRequest clubRegistrationRequest) {
        getSessionFactory().getCurrentSession().save(clubRegistrationRequest);
    }

    /**
     * Delete Club Registration Request
     *
     * @param   clubRegistrationRequest  clubRegistrationRequest
     */

    public void deleteClubRegistrationRequest(ClubRegistrationRequest clubRegistrationRequest) {
        getSessionFactory().getCurrentSession().delete(clubRegistrationRequest);
    }
    /**
     * Update ClubRegistrationRequest
     *
     * @param  clubRegistrationRequest clubRegistrationRequest
     */

    public void updateClubRegistrationRequest(ClubRegistrationRequest clubRegistrationRequest) {
        getSessionFactory().getCurrentSession().update(clubRegistrationRequest);
    }
    /**
     * Get clubRegistrationRequestById
     *
     * @param  cRequestId int
     * @return clubRegistrationRequest
     */

    public ClubRegistrationRequest getClubRegistrationRequestById(int cRequestId) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from  ClubRegistrationRequest where cRequestId=?")
                .setParameter(0, cRequestId).list();
        return (ClubRegistrationRequest)list.get(0);
    }

    /**
     * Get clubRegistrationRequest List
     *
     * @return List - customer list
     */

    public List<ClubRegistrationRequest> getClubRegistrationRequests() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  ClubRegistrationRequest").list();
        return list;
    }




    /**
     * Get clubRegistrationRequestByUserIdClubId List
     *
     * @return List - customer list
     */
    public List<ClubRegistrationRequest> getClubRegistrationRequestsByUserIdClubId(int userId, int clubId){
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from ClubRegistrationRequest where userId=? and clubId=?")
                .setParameter(0, userId)
                .setParameter(1, clubId)
                .list();
        return list;
    }

}
