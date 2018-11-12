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
    private Boolean TurnDir;  // true = 2->3->0->1-> | false = 2->1->0->3->
    private Integer cardMore;

    public Game(List<Integer> idPlayers) {


        this.DeckCreate(); //Future recuperation des Cards dans la bdd
        Collections.shuffle(Deck);

        idPlayers.forEach(id -> {
            List<Card> hand = new ArrayList<>();
            hand.addAll(pickCard(2));
            this.Players.add(new Player(id, hand));
        });


        Stack.addAll(pickCard(1));

    }

    public List<Card> newTurn(Integer playerIdx) {
        Card currentCard = Stack.get(Stack.size() - 1);

        if (!Players.get(playerIdx).setAvailableCard(currentCard)) {
            Players.get(playerIdx).pick(pickCard(1));
            CardMore(playerIdx);
        }

        return Players.get(playerIdx).getHand();
    }


    public void effect(Integer playerIdx, Card Card) {

        if (Card.getValue() != Value.TOWMORE || Card.getValue() != Value.FOURMORE) {
            CardMore(playerIdx);
        }

        switch (Card.getValue()) {
            case FORBIDDEN:
                setNextPlayer(playerIdx);
                break;
            case TOWMORE:
                cardMore += 2;
                break;
            case FOURMORE:
                cardMore += 4;
                break;
            case DIRCHANGE:
                TurnDir = !TurnDir;
                break;
            case COLORCHANGE:
                break;
        }

        Players.get(playerIdx).getHand().remove(Card);
        Stack.add(Card);
        setNextPlayer(playerIdx);
    }

    private Integer setNextPlayer(Integer currentPlayerIdx) {
        Integer nextPlayer;

        if (TurnDir) {
            if (currentPlayerIdx + 1 <= Players.size() - 1) {
                nextPlayer = currentPlayerIdx + 1;
            } else {
                nextPlayer = 0;
            }

        } else {
            if (currentPlayerIdx - 1 >= 0) {
                nextPlayer = currentPlayerIdx - 1;
            } else {
                nextPlayer = Players.size() - 1;
            }
        }

        return nextPlayer;
    }

    private void CardMore(Integer playerIdx) {
        if (cardMore != 0) {
            Players.get(playerIdx).pick(pickCard(cardMore));
            cardMore = 0;
        }
    }

    private List<Card> pickCard(Integer numberCard) {

        List<Card> cards = new ArrayList<>();

        for (Integer i = 0; i < numberCard; i++) {
            cards.add(Deck.get(Deck.size() - 1));
            Deck.remove(Deck.size() - 1);
        }
        return cards;
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