package com.example.Uno.configurations;

import com.example.Uno.entities.Player;
import com.example.Uno.repositories.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class startDB {

    @Bean
    CommandLineRunner initialize(PlayerRepository playerRepository){
        return args -> {
            playerRepository.save(new Player("Flacype", 150D, 150D,"lkqdsfbjlqs@dsklfhjq.cksqdf"));
            playerRepository.save(new Player("Pyshell", 0D, 256D, "lkqdsfbjlqs@dsklfhjq.cksqdf"));
            playerRepository.save(new Player("Val", 20D, 40D, "lkqdsfbjlqs@dsklfhjq.cksqdf"));
            playerRepository.save(new Player("Thim", 59D, 96D, "lkqdsfbjlqs@dsklfhjq.cksqdf"));
            playerRepository.save(new Player("Vincaca", 65D, 66D, "lkqdsfbjlqs@dsklfhjq.cksqdf"));
        };
    }
}
