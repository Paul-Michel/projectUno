package com.example.historyCore.configurations;


import com.example.historyCore.entities.PlayedGame;
import com.example.historyCore.repositories.PlayedGameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


@Configuration
@Slf4j
public class initDB {

    @Bean
    CommandLineRunner initialize(PlayedGameRepository playedGameRepository){
        return args -> {

        };
    }
}
