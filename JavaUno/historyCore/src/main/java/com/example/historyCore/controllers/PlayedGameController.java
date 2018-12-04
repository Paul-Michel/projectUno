package com.example.historyCore.controllers;

import com.example.historyCore.entities.PlayedGame;
import com.example.historyCore.exceptions.PlayedGameNotFoundException;
import com.example.historyCore.repositories.PlayedGameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class PlayedGameController {

    @Autowired
    private PlayedGameRepository playedGameRepository;

    @RequestMapping(value = "/playedgames", method = RequestMethod.GET)
    List<PlayedGame> getAll(){return playedGameRepository.findAll();}

    @RequestMapping(value = "/playedgames", method = RequestMethod.POST, consumes = {"application/json"})
    PlayedGame createPlayedGame (@RequestBody List<String> winnersId){
        PlayedGame newPlayedGame = new PlayedGame(winnersId.get(0), winnersId.get(1), winnersId.get(2), winnersId.get(3));
        return playedGameRepository.save(newPlayedGame);
    }

    @RequestMapping(value = "/playedgames/{id}", method = RequestMethod.GET)
    PlayedGame getOne(@PathVariable Long id){
        return playedGameRepository.findById(id).orElseThrow(()-> new PlayedGameNotFoundException(id));
    }

    @RequestMapping(value = "/playedgames/winner/{id}", method = RequestMethod.GET)
    List<PlayedGame> getAllWonGames(@PathVariable String id){
        return playedGameRepository.findAllByFirstWinnerId(id);
    }

    @RequestMapping(value = "/playedgames/looser/{id}", method = RequestMethod.GET)
    List<PlayedGame> getAllSecondGames(@PathVariable String id){
        List<PlayedGame> playedGames = new ArrayList<>();
        playedGames.addAll(playedGameRepository.findAllBySecondWinnerId(id));
        playedGames.addAll(playedGameRepository.findAllByThirdWinnerId(id));
        playedGames.addAll(playedGameRepository.findAllByFourthWinnerId(id));
        return playedGames;
    }



    @PutMapping(value = "/playedgames/{id}")
    PlayedGame replacePlayedGame (@RequestBody PlayedGame newPlayedGame, @PathVariable Long id){
        return playedGameRepository.findById(id)
                .map(playedGame -> {
                    playedGame.setDatePlayed(newPlayedGame.getDatePlayed());
                    playedGame.setFirstWinnerId(newPlayedGame.getFirstWinnerId());
                    playedGame.setSecondWinnerId(newPlayedGame.getSecondWinnerId());
                    playedGame.setThirdWinnerId(newPlayedGame.getThirdWinnerId());
                    playedGame.setFourthWinnerId(newPlayedGame.getFourthWinnerId());
                    return playedGameRepository.save(newPlayedGame);
                })
                .orElseGet(()->{
                    newPlayedGame.setId(id);
                    return playedGameRepository.save(newPlayedGame);
                });
    }

    @DeleteMapping(value = "playedgames/{id}")
    void deletePlayedGame(@PathVariable Long id){
        playedGameRepository.deleteById(id);
    }
}
