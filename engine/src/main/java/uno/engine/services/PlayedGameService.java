package uno.engine.services;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uno.engine.clients.IPlayedGame;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PlayedGameService {

    private IPlayedGame iPlayedGame = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(String.class))
            .logLevel(Logger.Level.FULL)
            .target(IPlayedGame.class, "http://localhost:5002/playedgames");
    private List<String> playersIdList = new ArrayList<>();
    private Integer j;

    public PlayedGameService() {
        j = 0;
        playersIdList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            playersIdList.add(" ");
        }
    }

    public void updateplayedGame(String playerId) {
        System.out.println(playerId);
        playersIdList.set(this.j, playerId);
        this.j++;
    }

    public void sendPlayedGame() {
        iPlayedGame.setPlayedGame(playersIdList);
    }

}
