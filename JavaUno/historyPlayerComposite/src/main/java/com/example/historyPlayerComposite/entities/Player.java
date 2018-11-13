package com.example.historyPlayerComposite.entities;

import lombok.Data;

@Data
public class Player {

    private Long id;
    private String pseudo;
    private Double winNb;
    private Double playedNb;
    private Double winrate;
    private String email;

    public Player(){}

    public Player(String pseudo, Double winNb, Double playedNb, String email) {
        this.pseudo = pseudo;
        this.winNb = winNb;
        this.playedNb = playedNb;
        winrate = winNb/playedNb*100;
        this.email = email;
    }

}
