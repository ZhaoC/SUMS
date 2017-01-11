package com.capstone.managedController;

import com.capstone.spring.model.ClubRegistrationRequest;
import com.capstone.spring.service.ClubRegistrationRequestService;
import org.springframework.dao.DataAccessException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Chan on 10/20/2016.
 */

@ManagedBean(name="clubRegistrationRequestMB")
@ViewScoped
public class ClubRegistrationRequestManagedController implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "ViewClubRegistrationRequest";
    private static final String ERROR   = "error";


    @ManagedProperty(value="#{ClubRegistrationRequestService}")
    ClubRegistrationRequestService clubRegistrationRequestService;

    private int cRequestId;
    private int userId;
    private int clubId;
    private String requestCRole;
    private Timestamp requestDate;
    private String requestStatus;

    List<ClubRegistrationRequest> clubRegistrationRequestList;
    List<ClubRegistrationRequest> clubRegistrationRequestListById;
    List<ClubRegistrationRequest> clubRegistrationRequestListByUserIdClubId;



    public ClubRegistrationRequestService getClubRegistrationRequestService(){
        return clubRegistrationRequestService;
    }

    public void setClubRegistrationRequestService(ClubRegistrationRequestService clubRegistrationRequestService){
        this.clubRegistrationRequestService = clubRegistrationRequestService;
    }

    public String addClubRegistrationRequest(){
        try{
            ClubRegistrationRequest request = new ClubRegistrationRequest();
            request.setcRequestId(getcRequestId());
            request.setUserId(getUserId());
            request.setClubId(getClubId());
            request.setRequestCRole(getRequestCRole());
            request.setRequestDate(getRequestDate());
            request.setRequestStatus(getRequestStatus());

            getClubRegistrationRequestService().addClubRegistrationRequest(request);
            return SUCCESS;
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    public void reset(){
        this.setcRequestId(0);
        this.setUserId(0);
        this.setClubId(0);
        this.setRequestCRole("");
        this.setRequestDate((Timestamp) new Date());
        this.setRequestStatus("");
    }


    public List<ClubRegistrationRequest> getClubRegistrationRequestList(){
        clubRegistrationRequestList = new ArrayList<ClubRegistrationRequest>();
        clubRegistrationRequestList.addAll(getClubRegistrationRequestService().getClubRegistrationRequests());
        return clubRegistrationRequestList;
    }

    public List<ClubRegistrationRequest> getClubRegistrationRequestById(int cRequestId)
    {
        clubRegistrationRequestListById = new ArrayList<ClubRegistrationRequest>();
        clubRegistrationRequestListById.add(getClubRegistrationRequestService().getClubRegistrationRequestById(cRequestId));
        return clubRegistrationRequestListById;
    }

    public List<ClubRegistrationRequest> getClubRegistrationRequestByUserIdClubId(int userId, int clubId){
        clubRegistrationRequestListByUserIdClubId = new ArrayList<ClubRegistrationRequest>();
        clubRegistrationRequestListByUserIdClubId.addAll(getClubRegistrationRequestService().getClubRegistrationRequestByUserIdClubId(userId, clubId));
        return clubRegistrationRequestListByUserIdClubId;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getcRequestId() {
        return cRequestId;
    }

    public void setcRequestId(int cRequestId) {
        this.cRequestId = cRequestId;
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

    public String getRequestCRole() {
        return requestCRole;
    }

    public void setRequestCRole(String requestCRole) {
        this.requestCRole = requestCRole;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public void setClubRegistrationRequestList(List<ClubRegistrationRequest> clubRegistrationRequestList) {
        this.clubRegistrationRequestList = clubRegistrationRequestList;
    }

    public List<ClubRegistrationRequest> getClubRegistrationRequestListById() {
        return clubRegistrationRequestListById;
    }

    public void setClubRegistrationRequestListById(List<ClubRegistrationRequest> clubRegistrationRequestListById) {
        this.clubRegistrationRequestListById = clubRegistrationRequestListById;
    }

    public List<ClubRegistrationRequest> getClubRegistrationRequestListByUserIdClubId() {
        return clubRegistrationRequestListByUserIdClubId;
    }

    public void setClubRegistrationRequestListByUserIdClubId(List<ClubRegistrationRequest> clubRegistrationRequestListByUserIdClubId) {
        this.clubRegistrationRequestListByUserIdClubId = clubRegistrationRequestListByUserIdClubId;
    }
}
