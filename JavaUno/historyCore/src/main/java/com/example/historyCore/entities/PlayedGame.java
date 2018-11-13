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
}
