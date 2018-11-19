package uno.engine.structs;

import uno.engine.entities.Card;

import java.util.List;

public class Result {
    public Boolean CanPlay;
    public Boolean CanRePlay;
    public Integer nextPlayer;
    public Card currentCard;
    public List<Card> hand;
}
