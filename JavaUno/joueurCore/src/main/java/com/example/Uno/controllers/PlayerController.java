package com.example.Uno.controllers;

import com.example.Uno.services.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PlayerController {


    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    ResponseEntity<Object> getAll(){return playerService.getAll();}

    @RequestMapping(value = "/players/{username}", method = RequestMethod.GET)
    ResponseEntity<Object> getOneByUsername (@PathVariable String username){
        return playerService.getOneByUsername(username);
    }

    @RequestMapping(value = "/players/id/{id}", method = RequestMethod.GET)
    ResponseEntity<Object> getOneById (@PathVariable String id){
        return playerService.getOneById(id);
    }


}
