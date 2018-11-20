package uno.engine.services;

import uno.engine.entities.Card;
import uno.engine.entities.Player;
import uno.engine.enums.Color;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

class PlayerService {


    Boolean setAvailableCard(Card currentCard, Player player) {
        AtomicReference<Boolean> minOneAvailable = new AtomicReference<>(false);
        player.getHand().forEach(card -> {
            if ((card.getColor() == Color.BLACK || card.getColor() == currentCard.getColor()) || card.getValue() == currentCard.getValue()) {
                card.setPlayable(true);
                minOneAvailable.set(true);
            } else {
                card.setPlayable(false);
            }
        });
        return minOneAvailable.get();
    }

    Boolean setOtherAvailableCard(Card currentCard, Player player) {
        AtomicReference<Boolean> minOneAvailable = new AtomicReference<>(false);
        player.getHand().forEach(card -> {
            if (currentCard.getValue() == card.getValue()) {
                card.setPlayable(true);
                minOneAvailable.set(true);
            } else {
                card.setPlayable(false);
            }
        });
        return minOneAvailable.get();
    }


    void pick(List<Card> newCard, Player player) {
        player.getHand().addAll(newCard);
    }
}