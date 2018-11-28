package uno.restapi.controllers;

import okhttp3.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uno.restapi.models.CardPlayed;
import uno.restapi.services.EngineService;
import uno.restapi.structs.Result;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@RestController
public class EngineController {


    @Autowired
    EngineService engineService;


    @RequestMapping(value = "/newgame", method = RequestMethod.POST)
    void newGame(Integer[] players) {
        System.out.println("Controller: " + players.length);
        engineService.newGame(players);
    }

    @RequestMapping(value = "/newturn/{playerIdx}", method = RequestMethod.GET)
    Result newTurn(@PathVariable Integer playerIdx) {
        return engineService.newTurn(playerIdx);
    }

    @RequestMapping(value = "/effect", method = RequestMethod.POST, consumes = {"application/json"})
    Result effect(@RequestBody CardPlayed cardplayed) {



        return engineService.newEffect(cardplayed);
    }
}