package uno.engine.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Player {

    private Integer id;
    private List<Card> hand;

    public Player(Integer id, List<Card> hand) {
        this.id = id;
        this.hand = hand;
    }
}
