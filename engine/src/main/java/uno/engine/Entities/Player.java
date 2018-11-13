package uno.engine.entities;

import uno.engine.enums.Color;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Player {

    private Integer id;
    private List<Card> hand;

    public Player(Integer id, List<Card> hand) {
        this.id = id;
        this.hand = hand;
    }

    public List<Card> getHand() {
        return hand;
    }


    public Boolean  setAvailableCard(Card currentCard) {
        AtomicReference<Boolean> minOneAvailable = new AtomicReference<>(false);
        this.hand.forEach(card -> {
            if ((card.getColor() == Color.BLACK || card.getColor() == currentCard.getColor()) || card.getValue() == currentCard.getValue()) {
                card.setPlayable(true);
                minOneAvailable.set(true);
            }else{
                card.setPlayable(false);
            }
        });
        return minOneAvailable.get();
    }

    public void pick(List<Card> newCard) {
        this.hand.addAll(newCard);
    }

}
