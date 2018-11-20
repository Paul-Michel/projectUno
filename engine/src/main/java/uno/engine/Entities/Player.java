package uno.engine.entities;

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
}
