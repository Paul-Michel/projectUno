package restAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restAPI.services.HistoryPlayerService;

@RestController
public class HistoryPlayerController {

    @Autowired
    HistoryPlayerService historyPlayerService;

    @RequestMapping("/wonbyplayer/{id}")
    ResponseEntity<Object> getOneHistoryPlayer(@PathVariable Long id) { return historyPlayerService.getOneHistoryPlayer(id); }

}
