package com.example.Uno.services;


import com.example.Uno.client.IPlayerClient;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlayerService {

    private IPlayerClient iPlayerClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(String.class))
                .logLevel(Logger.Level.FULL)
                .target(IPlayerClient.class, "http://localhost:1337/bdd");
}
