package restAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restAPI.services.CardService;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @RequestMapping("/cards/{id}")
    ResponseEntity<Object> getOneCard(@PathVariable Long id) {
        return cardService.getOneCard(id);
    }

    @RequestMapping("/cards")
    ResponseEntity<Object> getAllCards() {
        return cardService.getAllCards();
    }

}
