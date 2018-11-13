package com.example.historyPlayerComposite.services;


import com.example.historyPlayerComposite.clients.IHistoryClient;
import com.example.historyPlayerComposite.clients.IPlayerClient;
import com.example.historyPlayerComposite.entities.History;
import com.example.historyPlayerComposite.entities.Player;
import com.example.historyPlayerComposite.entities.PlayerHistory;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.Logger;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlayerHistoryService {

    private IPlayerClient iPlayerClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(String.class))
            .logLevel(Logger.Level.FULL)
            .target(IPlayerClient.class, "http://localhost:5001/players");

    private IHistoryClient iHistoryClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(String.class))
            .logLevel(Logger.Level.FULL)
            .target(IHistoryClient.class, "http://localhost:5002/playedgames");

    public PlayerHistory getOneById(Long playerId){
        log.info("Call to the Player Core - getOneById :" + playerId);
        Player player = iPlayerClient.getOneById((playerId));
        System.out.println("MQSFKDHBF QSDMFHPVQSDLFMB%QSD¨GIUFM BSDJFKLGQSDBFMJHQSDGLVKF");
        log.info("Call to the History Core - getAllByPlayerId : " + playerId);
        Object historyList = iHistoryClient.getAllByfirstWinnerId(playerId);

        return new PlayerHistory(player.getId(), player.getPseudo(), historyList);
    }
}
