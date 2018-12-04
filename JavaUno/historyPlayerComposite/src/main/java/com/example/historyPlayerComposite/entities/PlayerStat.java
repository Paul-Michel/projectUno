package com.example.historyPlayerComposite.entities;


import lombok.Data;

import java.util.Date;

@Data
public class PlayerStat {

    private String username;
    private Double wonGames;
    private Double lostGames;
    private Double winRate;

    public PlayerStat(){

    }

    public PlayerStat(String username, Double wonGames, Double lostGames) {
        this.username = username;
        this.wonGames = wonGames;
        this.lostGames = lostGames;
        this.winRate = wonGames / lostGames;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getWonGames() {
        return wonGames;
    }

    public void setWonGames(Double wonGames) {
        this.wonGames = wonGames;
    }

    public Double getLostGames() {
        return lostGames;
    }

    public void setLostGames(Double lostGames) {
        this.lostGames = lostGames;
    }

    public Double getWinRate() {
        return winRate;
    }

    public void setWinRate(Double winRate) {
        this.winRate = winRate;
    }
}

