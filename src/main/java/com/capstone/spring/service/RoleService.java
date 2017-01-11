package com.capstone.spring.service;

import com.capstone.spring.dao.RoleDAO;
import com.capstone.spring.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Chan on 10/15/16.
 */
@Service("RoleService")
@Transactional(readOnly = true)
public class RoleService {

    // RoleDAO is injected...
    @Autowired
    RoleDAO roleDAO;

    /**
     * Add role
     *
     * @param  role role
     */
    @Transactional(readOnly = false)
    public void addRole(Role role) {
        getRoleDAO().addRole(role);
    }

    /**
     * Delete role
     *
     * @param   role  role
     */
    @Transactional(readOnly = false)
    public void deleteRole(Role role) {
        getRoleDAO().deleteRole(role);
    }

    /**
     * Update Role
     *
     * @param role  Role
     */
    @Transactional(readOnly = false)
    public void updateRole(Role role) {
        getRoleDAO().updateRole(role);
    }

    /**
     * Get role
     *
     * @param  id int Role Id
     */

    public Role getRoleById(int id) {
        return getRoleDAO().getRoleById(id);
    }

    /**
     * Get Role List
     *
     */

    public List<Role> getRoles() {
        return getRoleDAO().getRoles();
    }

    /**
     * Get Role DAO
     *
     * @return roleDAO - Role DAO
     */
    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    /**
     * Set Role DAO
     *
     * @param  roleDAO - RoleDAO
     */
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public List<Role> getRolesByUserId(int userId){
        return getRoleDAO().getRolesByUserId(userId);
    }

    /**
     * author: Jason Cobbledick
     * @return
     */
    public List<Role> getRolesByClubId(int clubId){
        return getRoleDAO().getRolesByClubId(clubId);
    }
}
