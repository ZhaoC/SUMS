package com.capstone.spring.service;

import com.capstone.spring.dao.ClubNewsDAO;
import com.capstone.spring.model.ClubNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by  ZhaoC on 11/21/16.
 */

@Service("ClubNewsService")
@Transactional(readOnly = true)
public class ClubNewsService {

    // ClubNewsDAO is injected...
    @Autowired
    ClubNewsDAO clubNewsDAO;

    /**
     * Add clubNews
     *
     * @param  clubNews clubNews
     */
    @Transactional(readOnly = false)
    public void addClubNews(ClubNews clubNews) {
        getClubNewsDAO().addClubNews(clubNews);
    }

    /**
     * Delete ClubNews
     *
     * @param   clubNews  ClubNews
     */
    @Transactional(readOnly = false)
    public void deleteClubNews(ClubNews clubNews) {
        getClubNewsDAO().deleteClubNews(clubNews);
    }

    /**
     * Update ClubNews
     *
     * @param clubNews  ClubNews
     */
    @Transactional(readOnly = false)
    public void updateClubNews(ClubNews clubNews) {
        getClubNewsDAO().updateClubNews(clubNews);
    }

    /**
     * Get ClubNews
     *
     * @param  id int news Id
     */

    public ClubNews getClubNewsById(int id) {
        return getClubNewsDAO().getClubNewsById(id);
    }

    /**
     * Get ClubNews List
     *
     */

    public List<ClubNews> getClubNewsList() {
        return getClubNewsDAO().getClubNewsList();
    }

    /**
     * Get ClubNews DAO
     *
     * @return clubDAO - Club DAO
     */
    public ClubNewsDAO getClubNewsDAO() {
        return clubNewsDAO;
    }

    /**
     * Set ClubNews DAO
     *
     * @param  clubNewsDAO - ClubNewsDAO
     */
    public void setClubNewsDAO(ClubNewsDAO clubNewsDAO) {
        this.clubNewsDAO = clubNewsDAO;
    }

}
