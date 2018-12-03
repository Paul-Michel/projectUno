package com.example.historyCore.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@SequenceGenerator(name = "PRIVATE_SEQ_PLAYEDGAME", sequenceName = "private_sequence")
public class PlayedGame {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRIVATE_SEQ_PLAYEDGAME")
    private Long id;
    private Date datePlayed;
    private String firstWinnerId;
    private String secondWinnerId;
    private String thirdWinnerId;
    private String fourthWinnerId;

    public PlayedGame(String firstWinnerId, String secondWinnerId, String thirdWinnerId, String fourthWinnerId) {
        this.datePlayed = new Date();
        this.firstWinnerId = firstWinnerId;
        this.secondWinnerId = secondWinnerId;
        this.thirdWinnerId = thirdWinnerId;
        this.fourthWinnerId = fourthWinnerId;
    }

    public PlayedGame(){}

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
