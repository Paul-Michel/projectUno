package uno.engine.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uno.engine.entities.CardPlayed;
import uno.engine.services.GameService;
import uno.engine.structs.Result;


import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j

public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/newgame", method = RequestMethod.POST)
    void newGame(@RequestBody String players) {
        List<String> playersList = new ArrayList<>();
        for (String i : players.split(",")) {
            playersList.add(i);
        }
        gameService.newGame(playersList);
    }

    @RequestMapping(value = "/newturn/{playerIdx}", method = RequestMethod.GET)
    Result newTurn(@PathVariable Integer playerIdx) {
        return gameService.newTurn(playerIdx);
    }

    @RequestMapping(value = "/effect", method = RequestMethod.POST, consumes = {"application/json"})
    Result effect(@RequestBody CardPlayed cardplayed) {
        return gameService.effect(cardplayed);
    }
}