package uno.engine.Entities;

//import com.sun.org.apache.bcel.internal.Const;

import uno.engine.enums.Color;
import uno.engine.enums.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private List<Player> Players = new ArrayList<>();
    private List<Card> Deck = new ArrayList<>();
    private List<Card> Stack = new ArrayList<>();



    public Game(List<Integer> idPlayers) {


        this.DeckCreate(); //Future recuperation des Cards dans la bdd
        Collections.shuffle(Deck);

        idPlayers.forEach(id -> {
            List<Card> hand = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                hand.add(Deck.get(Deck.size() - 1));
                Deck.remove(Deck.size() - 1);
            }
            this.Players.add(new Player(id, hand));
        });
    }

    public List<Card> newTurn(Integer playerNb) {
        Card currentCard = Stack.get(Stack.size() - 1);
        Players.get(playerNb).setAvailableCard(currentCard);

        return Players.get(playerNb).getHand();
    }


    public void effect(Integer playerNb,Card Card){
        Players.get(playerNb).getHand().remove(Card);






    }

    private void DeckCreate() {
        this.Deck.add(new Card(Value.ONE, Color.BLUE, 1));
        this.Deck.add(new Card(Value.TWO, Color.BLUE, 2));
        this.Deck.add(new Card(Value.THREE, Color.BLUE, 3));
        this.Deck.add(new Card(Value.FOUR, Color.BLUE, 4));
        this.Deck.add(new Card(Value.FIVE, Color.BLUE, 5));
        this.Deck.add(new Card(Value.SIX, Color.BLUE, 6));
        this.Deck.add(new Card(Value.SEVEN, Color.BLUE, 7));
        this.Deck.add(new Card(Value.EIGHT, Color.BLUE, 8));
        this.Deck.add(new Card(Value.NINE, Color.BLUE, 9));
        this.Deck.add(new Card(Value.ZERO, Color.BLUE, 10));
    }

}
