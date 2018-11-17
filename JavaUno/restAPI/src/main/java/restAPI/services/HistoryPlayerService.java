package restAPI.services;

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
import restAPI.clients.IHistoryPlayerClient;
import restAPI.clients.IPlayedGameClient;


@Service
@Slf4j
public class HistoryPlayerService {

    private IHistoryPlayerClient iHistoryPlayerClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(String.class))
            .logLevel(Logger.Level.FULL)
            .target(IHistoryPlayerClient.class, "http://localhost:5003/wonbyplayer");

    public ResponseEntity<Object> getOneHistoryPlayer(Long id){
        Object obj = iHistoryPlayerClient.getOne(id);
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

}
