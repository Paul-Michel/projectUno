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
import uno.restapi.clients.ICardClient;


@Service
@Slf4j
public class CardService {

    private ICardClient iCardClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(String.class))
            .logLevel(Logger.Level.FULL)
            .target(ICardClient.class, "http://localhost:5000/cards");

    public ResponseEntity<Object> getOneCard(Long id){
        Object obj = iCardClient.getOne(id);
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllCards(){
        Object obj = iCardClient.getAll();
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

}
