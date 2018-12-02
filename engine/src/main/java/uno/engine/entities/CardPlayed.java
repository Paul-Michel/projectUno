package uno.engine.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CardPlayed {

    Integer playerIdx;
    Card card;

    public CardPlayed(Integer playerIdx, Card card){
        this.playerIdx = playerIdx;
        this.card = card;
    }
    public CardPlayed(){

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
