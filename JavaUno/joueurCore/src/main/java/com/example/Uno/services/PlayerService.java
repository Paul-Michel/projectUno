package com.example.Uno.services;


import com.example.Uno.client.IPlayerClient;
import com.example.Uno.entities.Player;
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

import java.util.List;

@Service
@Slf4j
public class PlayerService {

    private IPlayerClient iPlayerClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(String.class))
                .logLevel(Logger.Level.FULL)
                .target(IPlayerClient.class, "http://localhost:1337/user");

    public ResponseEntity<Object> getOneByUsername(String username){
        List<Player> players = iPlayerClient.getOneByUsername(username);
        return players.size() == 0 ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(players.get(0), HttpStatus.OK);
    }

    public ResponseEntity<Object> getOneById(String id){
        List<Player> players = iPlayerClient.getOneById(id);
        return players.size() == 0 ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(players.get(0), HttpStatus.OK);
    }

    public ResponseEntity<Object> getAll(){
        Object object = iPlayerClient.getAll();
        return new ResponseEntity<Object>(object, HttpStatus.OK);
    }

}
