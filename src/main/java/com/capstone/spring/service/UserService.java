package com.capstone.spring.service;

import com.capstone.spring.dao.UserDAO;
import com.capstone.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Chan on 10/11/2016.
 */
@Service("UserService")
@Transactional(readOnly = true)
public class UserService {

    // UserDAO is injected...
    @Autowired
    UserDAO userDAO;

    /**
     * Add user
     *
     * @param  user user
     */
    @Transactional(readOnly = false)
    public void addUser(User user) {
        getUserDAO().addUser(user);
    }

    /**
     * Delete User
     *
     * @param   user  user
     */
    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        getUserDAO().deleteUser(user);
    }

    /**
     * Update User
     *
     * @param user  user
     */
    @Transactional(readOnly = false)
    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }

    /**
     * Get User
     *
     * @param  id int user Id
     */

    public User getUserById(int id) {
        return getUserDAO().getUserById(id);
    }

    /**
     * Get User List
     *
     */

    public List<User> getUsers() {
        return getUserDAO().getUsers();
    }

    /**
     * Get User DAO
     *
     * @return userDAO - User DAO
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * Set User DAO
     *
     * @param  userDAO - UserDAO
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserByEmail(String userEmail) {
        return getUserDAO().getUserByEmail(userEmail);
    }
}
