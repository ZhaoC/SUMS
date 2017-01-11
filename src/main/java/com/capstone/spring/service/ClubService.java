package com.capstone.spring.service;

import java.util.List;

import com.capstone.spring.model.Club;
import com.capstone.spring.dao.ClubDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by  ZhaoC on 10/10/16.
 */

@Service("ClubService")
@Transactional(readOnly = true)
public class ClubService {

    // ClubDAO is injected...
    @Autowired
    ClubDAO clubDAO;

    /**
     * Add club
     *
     * @param  club club
     */
    @Transactional(readOnly = false)
    public void addClub(Club club) {
        getClubDAO().addClub(club);
    }

    /**
     * Delete club
     *
     * @param   club  club
     */
    @Transactional(readOnly = false)
    public void deleteClub(Club club) {
        getClubDAO().deleteClub(club);
    }

    /**
     * Update Club
     *
     * @param club  Club
     */
    @Transactional(readOnly = false)
    public void updateClub(Club club) {
        getClubDAO().updateClub(club);
    }

    /**
     * Get club
     *
     * @param  id int Club Id
     */

    public Club getClubById(int id) {
        return getClubDAO().getClubById(id);
    }

    /**
     * Get Club List
     *
     */

    public List<Club> getClubs() {
        return getClubDAO().getClubs();
    }

    /**
     * Get Club DAO
     *
     * @return clubDAO - Club DAO
     */
    public ClubDAO getClubDAO() {
        return clubDAO;
    }

    /**
     * Set Club DAO
     *
     * @param  clubDAO - ClubDAO
     */
    public void setClubDAO(ClubDAO clubDAO) {
        this.clubDAO = clubDAO;
    }

    public Club getClubByStatusAndInitUser(String clubStatus){
        return getClubDAO().getClubByStatusAndInitUser(clubStatus);
    }
}
