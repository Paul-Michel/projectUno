package uno.engine.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uno.engine.enums.Color;
import uno.engine.enums.Value;
import uno.engine.services.GameService;
import uno.engine.structs.Resulteffect;

import java.util.List;

@RestController
@Slf4j

public class GameController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/newgame", method = RequestMethod.POST)
    void newGame(@RequestBody List<Integer> players){
        gameService.newGame(players);
    }

}
