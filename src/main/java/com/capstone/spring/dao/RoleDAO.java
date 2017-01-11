package com.capstone.spring.dao;

import com.capstone.spring.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Chan on 10/15/16.
 */
@Repository
public class RoleDAO {
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
     * Add role
     *
     * @param   role role
     */

    public void addRole(Role role) {
        getSessionFactory().getCurrentSession().save(role);
    }

    /**
     * Delete role
     *
     * @param   role  role
     */

    public void deleteRole(Role role) {
        getSessionFactory().getCurrentSession().delete(role);
    }

    /**
     * Update role
     *
     * @param  role role
     */

    public void updateRole(Role role) {
        getSessionFactory().getCurrentSession().update(role);
    }

    /**
     * Get role
     *
     * @param  roleId int
     * @return role
     */

    public Role getRoleById(int roleId) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Role  where roleId=?")
                .setParameter(0, roleId).list();
        return (Role)list.get(0);
    }

    /**
     * Get role List
     *
     * @return List - role list
     */

    public List<Role> getRoles() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  Role").list();
        return list;
    }

    public List<Role> getRolesByUserId(int userId) {
        List list = getSessionFactory().getCurrentSession().createQuery("from  Role where userId=? and roleStatus=? ")
                .setParameter(0, userId).setParameter(1, "ACTIVE").list();
        return list;
    }

    /**
     * author: Jason Cobbledick
     * @return
     */
    public List<Role> getRolesByClubId(int clubId) {
        List list = getSessionFactory().getCurrentSession().createQuery("from  Role where clubId=?")
                .setParameter(0, clubId).list();
        return list;
    }
}
