package uno.engine.entities;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {

    public List<Player> players = new ArrayList<>();
    public List<Card> deck = new ArrayList<>();
    public List<Card> stack = new ArrayList<>();
    public Boolean turnDir = true;  // true = 2->3->0->1-> | false = 2->1->0->3->
    public Integer cardMore = 0;

    public List<Player> getPlayers() {
        return players;
    }

    public List<Card> getStack() {
        return stack;
    }

}