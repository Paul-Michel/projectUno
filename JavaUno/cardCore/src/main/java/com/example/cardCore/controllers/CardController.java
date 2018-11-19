package com.example.cardCore.controllers;

import com.example.cardCore.entities.Card;
import com.example.cardCore.exceptions.CardNotFoundException;
import com.example.cardCore.repositories.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CardController {


    @Autowired
    private CardRepository cardRepository;

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    List<Card> getAll(){return cardRepository.findAll();}

    @RequestMapping(value = "/cards", method = RequestMethod.POST)
    public @ResponseBody String add(@RequestParam String value,
                                    @RequestParam String color){
        Card card = new Card();
        card.setValue(value);
        card.setColor(color);
        cardRepository.save(card);
        return "Saved";
    }

    @RequestMapping(value = "/cards/{id}", method = RequestMethod.GET)
    Card getOneCard(@PathVariable Long id){
        return cardRepository.findById(id).orElseThrow(()-> new CardNotFoundException(id));
    }

    @PutMapping(value = "/cards/{id}")
    Card replaceCard (@RequestBody Card newCard, @PathVariable Long id){
        return cardRepository.findById(id)
                .map(card -> {
                    card.setValue(newCard.getValue());
                    return cardRepository.save(newCard);
                })
                .orElseGet(()->{
                    newCard.setId(id);
                    return cardRepository.save(newCard);
                });
    }

    @DeleteMapping(value = "cards/{id}")
    void deleteCard(@PathVariable Long id){
        cardRepository.deleteById(id);
    }
}
