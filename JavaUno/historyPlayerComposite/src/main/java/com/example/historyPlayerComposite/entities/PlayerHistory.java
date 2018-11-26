package com.example.historyPlayerComposite.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class PlayerHistory {

    private Long id;
    private String name;
    private Object playedGames;

    public PlayerHistory(Long id, String name, Object playedGames) {
        this.id = id;
        this.name = name;
        this.playedGames = playedGames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(Object playedGames) {
        this.playedGames = playedGames;
    }
}
