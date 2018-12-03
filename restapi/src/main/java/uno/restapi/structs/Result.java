package uno.restapi.structs;



import uno.restapi.models.Card;

import java.util.List;

public class Result {
    public Boolean gameEnd;
    public Boolean CanPlay;
    public Boolean CanRePlay;
    public Integer nextPlayer;
    public Integer playerEnd;
    public Card currentCard;
    public List<Card> hand;
}
