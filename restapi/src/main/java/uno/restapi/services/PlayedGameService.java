package uno.restapi.services;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uno.restapi.clients.IPlayedGameClient;


@Service
@Slf4j
public class PlayedGameService {

    private IPlayedGameClient iPlayedGameClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(String.class))
            .logLevel(Logger.Level.FULL)
            .target(IPlayedGameClient.class, "http://localhost:8002/playedgames");

    public ResponseEntity<Object> getOnePlayedGame(Long id){
        Object obj = iPlayedGameClient.getOne(id);
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllPlayedGame(){
        Object obj = iPlayedGameClient.getAll();
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

}
