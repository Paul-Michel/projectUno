package uno.engine.entities;


public class CardPlayed {

    Integer playerIdx;
    Card card;

    CardPlayed(Integer playerIdx, Card card) {
        this.playerIdx = playerIdx;
        this.card = card;
    }

    public CardPlayed() {

    }

    public Integer getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(Integer playerIdx) {
        this.playerIdx = playerIdx;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
