package com.example.historyPlayerComposite.entities;


import lombok.Data;

import java.util.Date;

@Data
public class History {

    private String id;
    private Date datePlayed;
    private String firstWinnerId;
    private String secondWinnerId;
    private String thirdWinnerId;
    private String fourthWinnerId;

    public History(){

    }

    public History(String firstWinnerId, String secondWinnerId, String thirdWinnerId, String fourthWinnerId) {
        this.datePlayed = new Date();
        this.firstWinnerId = firstWinnerId;
        this.secondWinnerId = secondWinnerId;
        this.thirdWinnerId = thirdWinnerId;
        this.fourthWinnerId = fourthWinnerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(Date datePlayed) {
        this.datePlayed = datePlayed;
    }

    public String getFirstWinnerId() {
        return firstWinnerId;
    }

    public void setFirstWinnerId(String firstWinnerId) {
        this.firstWinnerId = firstWinnerId;
    }

    public String getSecondWinnerId() {
        return secondWinnerId;
    }

    public void setSecondWinnerId(String secondWinnerId) {
        this.secondWinnerId = secondWinnerId;
    }

    public String getThirdWinnerId() {
        return thirdWinnerId;
    }

    public void setThirdWinnerId(String thirdWinnerId) {
        this.thirdWinnerId = thirdWinnerId;
    }

    public String getFourthWinnerId() {
        return fourthWinnerId;
    }

    public void setFourthWinnerId(String fourthWinnerId) {
        this.fourthWinnerId = fourthWinnerId;
    }
}
