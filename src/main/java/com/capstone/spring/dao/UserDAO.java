package com.capstone.spring.dao;

import com.capstone.spring.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Chan on 10/11/2016.
 */

@Repository
public class UserDAO  {
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
     * Add user
     *
     * @param   user user
     */

    public void addUser(User user) {
        getSessionFactory().getCurrentSession().save(user);
    }

    /**
     * Delete User
     *
     * @param   user user
     */

    public void deleteUser(User user) {
        getSessionFactory().getCurrentSession().delete(user);
    }

    /**
     * Update User
     *
     * @param  user user
     */

    public void updateUser(User user) {
        getSessionFactory().getCurrentSession().update(user);
    }

    /**
     * Get User
     *
     * @param  userId int
     * @return user
     */

    public User getUserById(int userId) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from User  where userId=?")
                .setParameter(0, userId).list();
        return (User)list.get(0);
    }

    /**
     * Get user List
     *
     * @return List - customer list
     */

    public List<User> getUsers() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  User").list();
        return list;
    }

    public User getUserByEmail(String userEmail) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from User  where userEmail=?")
                .setParameter(0, userEmail).list();
//        User user = new User();
 //       user = (User)list.get(0);
//        System.out.println(user.getUserId() + " |"+user.getUserEmail());
       return (User)list.get(0);
    }

}
