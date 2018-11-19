package uno.engine.controllers;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uno.engine.entities.Card;
import uno.engine.entities.CardPlayed;
import uno.engine.services.GameService;
import uno.engine.structs.Result;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j

public class GameController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/newgame", method = RequestMethod.POST)
    void newGame(Integer[] players) {
        List<Integer> playersList = Arrays.asList(players);
        gameService.newGame(playersList);
    }

    @RequestMapping(value = "/newturn", method = RequestMethod.POST)
    Result newTurn(Integer playerIdx) {

        return gameService.newTurn(playerIdx);
    }

    @RequestMapping(value = "/effect", method = RequestMethod.POST, consumes = {"application/json"})
    Result effect(@RequestBody CardPlayed cardplayed) {

        return gameService.effect(cardplayed);
    }
}
