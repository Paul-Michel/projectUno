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
