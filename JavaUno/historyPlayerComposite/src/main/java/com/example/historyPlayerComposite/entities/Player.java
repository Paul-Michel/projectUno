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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Double getWinNb() {
        return winNb;
    }

    public void setWinNb(Double winNb) {
        this.winNb = winNb;
    }

    public Double getPlayedNb() {
        return playedNb;
    }

    public void setPlayedNb(Double playedNb) {
        this.playedNb = playedNb;
    }

    public Double getWinrate() {
        return winrate;
    }

    public void setWinrate(Double winrate) {
        this.winrate = winrate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
