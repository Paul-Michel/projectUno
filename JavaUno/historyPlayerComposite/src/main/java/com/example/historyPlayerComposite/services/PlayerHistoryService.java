package com.example.historyPlayerComposite.services;


import com.example.historyPlayerComposite.clients.IHistoryClient;
import com.example.historyPlayerComposite.clients.IPlayerClient;
import com.example.historyPlayerComposite.entities.History;
import com.example.historyPlayerComposite.entities.Player;
import com.example.historyPlayerComposite.entities.PlayerHistory;
import com.example.historyPlayerComposite.entities.PlayerStat;
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
            .target(IPlayerClient.class, "http://localhost:5005/players");

    private IHistoryClient iHistoryClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(String.class))
            .logLevel(Logger.Level.FULL)
            .target(IHistoryClient.class, "http://localhost:5002/playedgames");

    public PlayerHistory getOneById(String username){
        Player player = iPlayerClient.getOneByUsername((username));
        List<History> historyList = iHistoryClient.getAllByfirstWinnerId(player.get_id());
        historyList.addAll(iHistoryClient.getAllLooserId(player.get_id()));
        for(History history : historyList){
            history.setFirstWinnerId(iPlayerClient.getOneById(history.getFirstWinnerId()).getUsername());
            history.setSecondWinnerId(iPlayerClient.getOneById(history.getSecondWinnerId()).getUsername());
            System.out.println(history.getThirdWinnerId());
            if(history.getThirdWinnerId().length() > 15){
                history.setThirdWinnerId(iPlayerClient.getOneById(history.getThirdWinnerId()).getUsername());

            }
            if(history.getThirdWinnerId().length() > 15){
                history.setFourthWinnerId(iPlayerClient.getOneById(history.getFourthWinnerId()).getUsername());
            }
        }
        return new PlayerHistory(player.get_id(), player.getUsername(), historyList);
    }

    public PlayerStat getStatsByUsername(String username){
        Player player = iPlayerClient.getOneByUsername((username));
        Integer wonGames = iHistoryClient.getAllByfirstWinnerId(player.get_id()).size();
        Integer lostGames = iHistoryClient.getAllLooserId(player.get_id()).size();
        PlayerStat playerStat = new PlayerStat(username, wonGames.doubleValue(), lostGames.doubleValue());
        return playerStat;
    }
}
