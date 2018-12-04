package com.example.historyPlayerComposite.entities;

import lombok.Data;

@Data
public class Player {

    private String _id;
    private String username;
    private Double winNb;
    private Double playedNb;
    private Double winrate;
    private String email;

    public Player(){}

    public Player(String username, Double winNb, Double playedNb, String email) {
        this.username = username;
        this.winNb = winNb;
        this.playedNb = playedNb;
        winrate = winNb/playedNb*100;
        this.email = email;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
