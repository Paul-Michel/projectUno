package uno.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uno.restapi.services.PlayerService;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @RequestMapping("/players/{id}")
    ResponseEntity<Object> getOnePlayer(@PathVariable Long id) { return playerService.getOnePlayer(id); }

    @RequestMapping("/players")
    ResponseEntity<Object> getAllPlayers() {
        return playerService.getAllPlayer();
    }

}
