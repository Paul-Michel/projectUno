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

    public void RefreshWR(Player player){
        player.setWinrate(player.getWinNb()/player.getPlayedNb()*100);
    }
    public void AddPlayedGame(Player player){
        player.setPlayedNb(player.getPlayedNb()+1);
        RefreshWR(player);
    }
    public void AddVictory(Player player){
        player.setWinNb(player.getWinNb()+1);
        AddPlayedGame(player);
    }


}
