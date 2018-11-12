package restAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restAPI.services.PlayedGameService;

@RestController
public class PlayedGameController {

    @Autowired
    PlayedGameService playedGameService;

    @RequestMapping("/playedgames/{id}")
    ResponseEntity getOne(@PathVariable Long id) {

        return playedGameService.getOnePlayedGame(id);
    }
}
