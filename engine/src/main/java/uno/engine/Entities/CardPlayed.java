package uno.engine.entities;

public class CardPlayed {

    Integer playerIdx;
    Card card;

    CardPlayed(Integer playerIdx, Card card){
        this.playerIdx = playerIdx;
        this.card = card;
    }

    public Integer getPlayerIdx() {
        return playerIdx;
    }

    public Card getCard() {
        return card;
    }

}
