package com.capstone.managedController;

import com.capstone.spring.model.Club;
import com.capstone.spring.model.ClubNews;
import com.capstone.spring.service.ClubNewsService;
import com.capstone.spring.service.ClubService;
import com.capstone.spring.service.UserService;
import org.springframework.dao.DataAccessException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by  ZhaoC on 11/21/16.
 */
@ManagedBean(name="clubNewsMB")
@SessionScoped
public class ClubNewsController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "club_view_news";
    private static final String ERROR   = "error";

    @ManagedProperty(value="#{ClubNewsService}")
    private ClubNewsService clubNewsService;


    @ManagedProperty(value="#{UserService}")
    private UserService userService;

    @ManagedProperty(value="#{ClubService}")
    private ClubService clubService;

    @ManagedProperty("#{loginValidate}")
    LoginValidate loginValidate;

    @ManagedProperty("#{clubRoleDataMB}")
    private
    ClubRoleDataController clubRoleDataController;

    private int newsId;
    private String newsTitle;
    private String newsContent;
    private String newsCategory;
    private Date effectiveDate;
    private Date releaseDate;
    private int userId;
    private int clubId;

    //requested vars
    private int currentUserId;
    private List<ClubNews> clubsNewsList;
    private ClubNews selectedNews;
    private String newsEditorName;
    private String newsEditorContent;
    private List<Integer> clubIdList;
    private List<Club> adminClubList;

//    public String addClubNews(){
//        try{
//            ClubNews clubNews = new ClubNews();
//            clubNews.setNewsId(getNewsId());
//            clubNews.setNewsTitle(getNewsTitle());
//            clubNews.setNewsContent(getNewsContent());
//            clubNews.setNewsCategory(getNewsCategory());
//            clubNews.setEffectiveDate(new java.sql.Timestamp(getEffectiveDate().getTime()));
//            clubNews.setReleaseDate(new java.sql.Timestamp(getReleaseDate().getTime()));
//            clubNews.setUserId(getUserId());
//            clubNews.setClubId(getClubId());
//
//            getClubNewsService().addClubNews(clubNews);
//            return SUCCESS;
//        }catch(DataAccessException e){
//            e.printStackTrace();
//        }
//        return ERROR;
//    }

    public String addClubNews(){
        try{
            ClubNews clubNews = new ClubNews();
            clubNews.setNewsId(getNewsId());
            clubNews.setNewsTitle(getNewsTitle());
            clubNews.setNewsContent(getNewsContent());
            clubNews.setNewsCategory("Test");
            clubNews.setEffectiveDate(new java.sql.Timestamp((new Date()).getTime()));
            clubNews.setReleaseDate(new java.sql.Timestamp((new Date()).getTime()));
            clubNews.setUserId(getCurrentUserId());
            clubNews.setClubId(getClubId());

            getClubNewsService().addClubNews(clubNews);
            return SUCCESS;
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    public void reset(){
        this.setNewsId(0);
        this.setNewsTitle("");
        this.setNewsContent("");
        this.setNewsCategory("");
        this.setEffectiveDate(new Date());
        this.setReleaseDate(new Date());
        this.setUserId(0);
        this.setClubId(0);
    }

    public ClubNewsService getClubNewsService() {
        return clubNewsService;
    }

    public void setClubNewsService(ClubNewsService clubNewsService) {
        this.clubNewsService = clubNewsService;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }


    public List<ClubNews> getClubsNewsList() {
        clubsNewsList = getClubNewsService().getClubNewsList();
        Collections.reverse(clubsNewsList);
        return clubsNewsList;
    }

    public void setClubsNewsList(List<ClubNews> clubsNewsList) {
        this.clubsNewsList = clubsNewsList;
    }

    public ClubNews getSelectedNews() {
        return selectedNews;
    }

    public void setSelectedNews(ClubNews selectedNews) {
        this.selectedNews = selectedNews;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public LoginValidate getLoginValidate() {
        return loginValidate;
    }

    public void setLoginValidate(LoginValidate loginValidate) {
        this.loginValidate = loginValidate;
    }

    public int getCurrentUserId() {
        return getLoginValidate().getUserId();

    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    public void setNewsEditorContent(String newsEditorContent) {
        this.newsEditorContent = newsEditorContent;
    }

    public ClubService getClubService() {
        return clubService;
    }

    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    public List<Integer> getClubIdList() {

        List<Integer> allMyClubIds = new ArrayList<Integer>();

        for(Club club: getClubRoleDataController().getCurrentUserClubListWithADMIN()){
            allMyClubIds.add(club.getClubId());
        }
        clubIdList = allMyClubIds;
        return clubIdList;
    }

    public void setClubIdList(List<Integer> clubIdList) {
        this.clubIdList = clubIdList;
    }

    public String viewSelectedNews(){

        return "/webpages/club_manager/club_view_per_news" + "?faces-redirect=true&id="+getSelectedNews().getNewsId();
    }


    public String getNewsEditorName() {
        String returnName = getUserService().getUserById(getSelectedNews().getUserId()).getUserFirstName()+ " " +getUserService().getUserById(getSelectedNews().getUserId()).getUserLastName();
        newsEditorName = returnName;
        return newsEditorName;
    }

    public void setNewsEditorName(String newsEditorName) {
        this.newsEditorName = newsEditorName;
    }

    public String getNewsEditorContent() {
        StringBuilder returnString = new StringBuilder();
        returnString.append(getSelectedNews().getNewsContent());
        returnString.append(" |-- Posted by: "+ getNewsEditorName());
        returnString.append(" -|- News Posted On: " + getSelectedNews().getReleaseDate());
        //returnString.append(" --|-- News Effective to: " + getSelectedNews().getEffectiveDate()+"-|");
        newsEditorContent = returnString.toString();
        return newsEditorContent;
    }


    public ClubRoleDataController getClubRoleDataController() {
        return clubRoleDataController;
    }

    public void setClubRoleDataController(ClubRoleDataController clubRoleDataController) {
        this.clubRoleDataController = clubRoleDataController;
    }


    public List<Club> getAdminClubList() {
        List<Club> allMyAdminClubs = new ArrayList<Club>();
        allMyAdminClubs = getClubRoleDataController().getCurrentUserClubListWithADMIN();
        adminClubList = allMyAdminClubs;
        return adminClubList;
    }

    public void setAdminClubList(List<Club> adminClubList) {
        this.adminClubList = adminClubList;
    }
}
