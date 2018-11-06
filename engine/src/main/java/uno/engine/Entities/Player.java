package uno.engine.Entities;

import uno.engine.enums.Color;

import java.util.List;

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


    public void setAvailableCard(Card currentCard) {
        this.hand.forEach(card -> {
            if ((card.getColor() == Color.BLACK || card.getColor() == currentCard.getColor()) || card.getValue() == currentCard.getValue()) {
                card.setPlayable(true);
            } else {
                card.setPlayable(false);
            }
        });
    }

    public void pick(List<Card> newCard) {
        this.hand.addAll(newCard);
    }




}
