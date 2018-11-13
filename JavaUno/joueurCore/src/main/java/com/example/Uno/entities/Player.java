package com.example.Uno.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name="PRIVATE_SEQ_PLAYER", sequenceName = "private_sequence")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRIVATE_SEQ_PLAYER")
        private Long id;
        private String pseudo;
        private Double winNb;
        private Double playedNb;
        private Double winrate;
        private String email;

    public Player(String pseudo, Double winNb, Double playedNb, String email) {
        this.pseudo = pseudo;
        this.winNb = winNb;
        this.playedNb = playedNb;
        winrate = winNb/playedNb*100;
        this.email = email;
    }

    public Player() {
    }
}
