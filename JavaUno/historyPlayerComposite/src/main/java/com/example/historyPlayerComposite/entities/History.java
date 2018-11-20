package com.example.historyPlayerComposite.entities;


import lombok.Data;

import java.util.Date;

@Data
public class History {

    private Long id;
    private Date datePlayed;
    private Long firstWinnerId;
    private Long secondWinnerId;
    private Long thirdWinnerId;
    private Long fourthWinnerId;

    public History(Long firstWinnerId, Long secondWinnerId, Long thirdWinnerId, Long fourthWinnerId) {
        this.datePlayed = new Date();
        this.firstWinnerId = firstWinnerId;
        this.secondWinnerId = secondWinnerId;
        this.thirdWinnerId = thirdWinnerId;
        this.fourthWinnerId = fourthWinnerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(Date datePlayed) {
        this.datePlayed = datePlayed;
    }

    public Long getFirstWinnerId() {
        return firstWinnerId;
    }

    public void setFirstWinnerId(Long firstWinnerId) {
        this.firstWinnerId = firstWinnerId;
    }

    public Long getSecondWinnerId() {
        return secondWinnerId;
    }

    public void setSecondWinnerId(Long secondWinnerId) {
        this.secondWinnerId = secondWinnerId;
    }

    public Long getThirdWinnerId() {
        return thirdWinnerId;
    }

    public void setThirdWinnerId(Long thirdWinnerId) {
        this.thirdWinnerId = thirdWinnerId;
    }

    public Long getFourthWinnerId() {
        return fourthWinnerId;
    }

    public void setFourthWinnerId(Long fourthWinnerId) {
        this.fourthWinnerId = fourthWinnerId;
    }
}
