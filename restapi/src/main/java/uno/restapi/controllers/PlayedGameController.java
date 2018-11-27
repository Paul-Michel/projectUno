package uno.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uno.restapi.services.PlayedGameService;

@RestController
public class PlayedGameController {

    @Autowired
    PlayedGameService playedGameService;

    @RequestMapping("/playedgames/{id}")
    ResponseEntity<Object> getOnePlayedGame(@PathVariable Long id) {
        return playedGameService.getOnePlayedGame(id);
    }

    @RequestMapping("/playedgames")
    ResponseEntity<Object> getAllPlayedGames() {
        return playedGameService.getAllPlayedGame();
    }

}
