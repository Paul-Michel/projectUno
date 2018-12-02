package uno.engine.entities;

import uno.engine.enums.Color;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Player {

    private String id;
    private List<Card> hand;

    public Player(String id, List<Card> hand) {
        this.id = id;
        this.hand = hand;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

}
