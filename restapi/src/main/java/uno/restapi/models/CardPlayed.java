package uno.restapi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CardPlayed {

    Integer playerIdx;
    Card card;

    CardPlayed(Integer playerIdx, Card card){
        this.playerIdx = playerIdx;
        this.card = card;
    }

    CardPlayed(){
    }

    public void setPlayerIdx(Integer playerIdx) {
        this.playerIdx = playerIdx;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getPlayerIdx() {
        return playerIdx;
    }

    public Card getCard() {
        return card;
    }

}
