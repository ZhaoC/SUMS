package com.capstone.spring.service;

import com.capstone.spring.dao.ClubRegistrationRequestDAO;
import com.capstone.spring.model.ClubRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Chan on 10/20/2016.
 */

@Service("ClubRegistrationRequestService")
@Transactional(readOnly = true)
public class ClubRegistrationRequestService {

    //ClubRegistrationRequestDAO is injected..
    @Autowired
    ClubRegistrationRequestDAO clubRegistrationRequestDAO;

    /**
     * Add clubRegistrationRequest
     *
     * @param  clubRegistrationRequest clubRegistrationRequest
     */
    @Transactional(readOnly = false)
    public void addClubRegistrationRequest(ClubRegistrationRequest clubRegistrationRequest) {
        getClubRegistrationRequestDAO().addClubRegistrationRequest(clubRegistrationRequest);
    }

    /**
     * Delete clubRegistrationRequest
     *
     * @param   clubRegistrationRequest  clubRegistrationRequest
     */
    @Transactional(readOnly = false)
    public void deleteClubRegistrationRequest(ClubRegistrationRequest clubRegistrationRequest) {
        getClubRegistrationRequestDAO().deleteClubRegistrationRequest(clubRegistrationRequest);
    }

    /**
     * Update clubRegistrationRequest
     *
     * @param clubRegistrationRequest  clubRegistrationRequest
     */
    @Transactional(readOnly = false)
    public void updateClubRegistrationRequest(ClubRegistrationRequest clubRegistrationRequest) {
        getClubRegistrationRequestDAO().updateClubRegistrationRequest(clubRegistrationRequest);
    }

    /**
     * Get clubRegistrationRequests
     *
     * @return List
     */

    public List<ClubRegistrationRequest> getClubRegistrationRequests() {
        return getClubRegistrationRequestDAO().getClubRegistrationRequests();
    }

    /**
     * Get clubRegistrationRequestById
     *
     * @param  cRequestId int ClubRegistrationRequest cRequestId
     */

    public ClubRegistrationRequest getClubRegistrationRequestById(int cRequestId) {
        return getClubRegistrationRequestDAO().getClubRegistrationRequestById(cRequestId);
    }


    /**
     * Get ClubRegistrationRequest DAO
     *
     * @return clubRegistrationRequestDAO - ClubRegistrationRequest DAO
     */
    public ClubRegistrationRequestDAO getClubRegistrationRequestDAO() {
        return clubRegistrationRequestDAO;
    }

    /**
     * Set ClubRegistrationRequest DAO
     *
     * @param  clubRegistrationRequestDAO - ClubRegistrationRequest DAO
     */
    public void setClubRegistrationRequest(ClubRegistrationRequestDAO clubRegistrationRequestDAO) {
        this.clubRegistrationRequestDAO = clubRegistrationRequestDAO;
    }


    /**
     * Get ClubRegistrationRequestByUserIdClubId
     *
     * @param   - ClubRegistrationRequestByUserIdClubId
     */
    public List<ClubRegistrationRequest> getClubRegistrationRequestByUserIdClubId(int userId, int clubId){
        return getClubRegistrationRequestDAO().getClubRegistrationRequestsByUserIdClubId(userId, clubId);

    }





}
