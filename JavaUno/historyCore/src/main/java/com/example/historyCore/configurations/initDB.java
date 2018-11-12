package com.example.historyCore.configurations;


import com.example.historyCore.entities.PlayedGame;
import com.example.historyCore.repositories.PlayedGameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class initDB {

    @Bean
    CommandLineRunner initialize(PlayedGameRepository playedGameRepository){
        return args -> {
            playedGameRepository.save(new PlayedGame("Flacype", "Thim", "Val", "Pichel"));
            playedGameRepository.save(new PlayedGame("Val", "Thim", "Flacype", "Pichel"));
            playedGameRepository.save(new PlayedGame("Thim", "Flacype", "Val", "Pichel"));
            playedGameRepository.save(new PlayedGame("Flacype", "Thim", "Val", "Pichel"));

        };
    }
}
