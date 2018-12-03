package uno.engine.entities;

import java.util.List;


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

    public String getId() {
        return id;
    }

}
