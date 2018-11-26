package com.example.Uno.controllers;

import com.example.Uno.entities.Player;
import com.example.Uno.exceptions.PlayerNotFoundException;
import com.example.Uno.repositories.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
public class PlayerController {



    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    List<Player> getAll(){return playerRepository.findAll();}

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    Player newPlayer (@RequestBody Player newPlayer){return playerRepository.save(newPlayer);}

    @RequestMapping(value = "/players/{id}", method = RequestMethod.GET)
    Player getOne (@PathVariable Long id){
        return playerRepository.findById(id)
                .orElseThrow(()-> new PlayerNotFoundException(id));
    }

    @RequestMapping(value = "/players/pseudo/{pseudo}", method = RequestMethod.GET)
    Player getOneByPseudo(@PathVariable String pseudo){
        return playerRepository.findByPseudo(pseudo);
    }

    @PutMapping(value ="/players/{id}")
    Player replacePlayer (@RequestBody Player newPlayer, @PathVariable Long id){
        return  playerRepository.findById(id)
                .map(player -> {
                    player.setPseudo(newPlayer.getPseudo());
                    player.setWinNb(newPlayer.getWinNb());
                    return playerRepository.save(newPlayer);
                })
                .orElseGet(() -> {
                    newPlayer.setId(id);
                    return playerRepository.save(newPlayer);
                });
    }

    @DeleteMapping("/players/{id}")
    void deletePlayer(@PathVariable Long id){playerRepository.deleteById(id);}
}
