package uno.engine.entities;




import java.util.ArrayList;
import java.util.List;

public class Game {

    public List<Player> Players = new ArrayList<>();
    public List<Card> Deck = new ArrayList<>();
    public List<Card> Stack = new ArrayList<>();
    public Boolean TurnDir = true;  // true = 2->3->0->1-> | false = 2->1->0->3->
    public Integer cardMore = 0;


}