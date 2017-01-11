package com.capstone.spring.dao;

import java.util.List;
import com.capstone.spring.model.Club;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * Created by  ZhaoC on 10/10/16.
 */

@Repository
public class ClubDAO  {
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
     * Add club
     *
     * @param   club club
     */

    public void addClub(Club club) {
        getSessionFactory().getCurrentSession().save(club);
    }

    /**
     * Delete club
     *
     * @param   club  club
     */

    public void deleteClub(Club club) {
        getSessionFactory().getCurrentSession().delete(club);
    }

    /**
     * Update club
     *
     * @param  club club
     */

    public void updateClub(Club club) {
        getSessionFactory().getCurrentSession().update(club);
    }

    /**
     * Get club
     *
     * @param  clubId int
     * @return club
     */

    public Club getClubById(int clubId) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Club  where clubId=?")
                .setParameter(0, clubId).list();
        return (Club)list.get(0);
    }

    /**
     * Get club List
     *
     * @return List - customer list
     */

    public List<Club> getClubs() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  Club").list();
        return list;
    }


    public Club getClubByStatusAndInitUser(String clubStatus){
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Club  where clubStatus=?")
                .setParameter(0, clubStatus).list();
        return (Club)list.get(0);
    }

}