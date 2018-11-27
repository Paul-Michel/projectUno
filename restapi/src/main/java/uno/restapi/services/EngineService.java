package uno.restapi.services;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uno.restapi.clients.IEngineClient;
import uno.restapi.models.CardPlayed;
import uno.restapi.structs.Result;


import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class EngineService {


    private IEngineClient iEngineClient = (IEngineClient) Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(String.class))
            .logLevel(Logger.Level.FULL)
            .target(IEngineClient.class, "http://localhost:7000");


    public void newGame(Integer[] players) {
        System.out.println("Service: " + players.length);
        String playersS = "";
        for (Integer i : players) {
            playersS += i.toString() + ',';
        }
        iEngineClient.setNewGame(playersS);
    }

    public Result newTurn(Integer playerIdx) {
        return iEngineClient.getNewTurn(playerIdx);
    }

    public Result newEffect(CardPlayed cardPlayed) {
        return iEngineClient.getEffect(cardPlayed);
    }
}
