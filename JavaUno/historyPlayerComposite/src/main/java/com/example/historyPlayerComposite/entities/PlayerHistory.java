package com.example.historyPlayerComposite.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlayerHistory {

    private Long id;
    private String name;
    private Object playedGames;
}
