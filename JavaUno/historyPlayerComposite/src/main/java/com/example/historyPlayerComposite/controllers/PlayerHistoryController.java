package com.example.historyPlayerComposite.controllers;

import com.example.historyPlayerComposite.entities.PlayerHistory;
import com.example.historyPlayerComposite.services.PlayerHistoryService;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerHistoryController {

    @Autowired
    @Setter
    PlayerHistoryService playerHistoryService;

    @RequestMapping("/wonbyplayer/{id}")
    ResponseEntity<PlayerHistory> getOne (@PathVariable Long id){
        return new ResponseEntity<>(playerHistoryService.getOneById(id), HttpStatus.OK);
    }
}
