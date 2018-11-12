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
    private String firstWinner;
    private String secondWinner;
    private String thirdWinner;
    private String fourthWinner;

    public PlayedGame(String firstWinner, String secondWinner, String thirdWinner, String fourthWinner) {
        this.datePlayed = new Date();
        this.firstWinner = firstWinner;
        this.secondWinner = secondWinner;
        this.thirdWinner = thirdWinner;
        this.fourthWinner = fourthWinner;
    }

    public PlayedGame(){}
}
