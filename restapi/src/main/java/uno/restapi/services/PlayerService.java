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
import uno.restapi.clients.IPlayerClient;


@Service
@Slf4j
public class PlayerService {

    private IPlayerClient iPlayerClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(String.class))
            .logLevel(Logger.Level.FULL)
            .target(IPlayerClient.class, "http://localhost:5001/players");

    public ResponseEntity<Object> getOnePlayer(Long id){
        Object obj = iPlayerClient.getOne(id);
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllPlayer(){
        Object obj = iPlayerClient.getAll();
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

}
