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
    private Long firstWinnerId;
    private Long secondWinnerId;
    private Long thirdWinnerId;
    private Long fourthWinnerId;

    public PlayedGame(Long firstWinnerId, Long secondWinnerId, Long thirdWinnerId, Long fourthWinnerId) {
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
