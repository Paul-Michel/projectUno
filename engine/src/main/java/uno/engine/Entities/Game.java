package uno.engine.entities;

//import com.sun.org.apache.bcel.internal.Const;

import uno.engine.enums.Color;
import uno.engine.enums.Value;
import uno.engine.structs.ResultNewTurn;
import uno.engine.structs.Resulteffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private List<Player> Players = new ArrayList<>();
    private List<Card> Deck = new ArrayList<>();
    private List<Card> Stack = new ArrayList<>();
    private Boolean TurnDir = true;  // true = 2->3->0->1-> | false = 2->1->0->3->
    private Integer cardMore = 0;

    public Game(List<Integer> idPlayers) {


        this.DeckCreate(); //Future recuperation des Cards dans la bdd
        Collections.shuffle(Deck);

        idPlayers.forEach(id -> {
            List<Card> hand = new ArrayList<>();
            hand.addAll(pickCard(10));
            this.Players.add(new Player(id, hand));
        });
        Integer i = 1;
        do {
            if (Deck.get(Deck.size() - i).getColor() == Color.BLACK ||
                    Deck.get(Deck.size() - i).getValue() == Value.FORBIDDEN ||
                    Deck.get(Deck.size() - i).getValue() == Value.DIRCHANGE ||
                    Deck.get(Deck.size() - i).getValue() == Value.TWOMORE) {
                i++;
            } else {
                Stack.add(Deck.get(Deck.size() - i));
                Deck.remove(Deck.size() - i);
                break;
            }

        } while (true);

    }

    public ResultNewTurn newTurn(Integer playerIdx) {

        ResultNewTurn resultNewTurn = new ResultNewTurn();

        Card currentCard = Stack.get(Stack.size() - 1);
        resultNewTurn.currentCard = currentCard;

        if(Deck.size() < 1){
            Stack.forEach(card -> {
                Deck.add(card);
                Stack.remove(card);
            });
            Collections.shuffle(Deck);
            Stack.add(currentCard);
        }

        if (!Players.get(playerIdx).setAvailableCard(currentCard)) {
            Players.get(playerIdx).pick(pickCard(1));
            if(!Players.get(playerIdx).setAvailableCard(currentCard)){
                CardMore(playerIdx);
                resultNewTurn.CanPlay = false;
                resultNewTurn.nextplayer = setNextPlayer(playerIdx);
            }else{
                resultNewTurn.CanPlay = true;
            }
        }else{
            resultNewTurn.CanPlay = true;
        }


        resultNewTurn.hand = Players.get(playerIdx).getHand();

        return resultNewTurn;
    }


    public Resulteffect effect(Integer playerIdx, Card Card) {

        Resulteffect resulteffect = new Resulteffect();
        Boolean forbiddenPlayer = false;

        if (Card.getValue() != Value.TWOMORE && Card.getValue() != Value.FOURMORE) {
            System.out.println(cardMore);
            CardMore(playerIdx);
        }

        Stack.add(Card);
        switch (Card.getValue()) {
            case FORBIDDEN:
                forbiddenPlayer = true;
                break;
            case TWOMORE:
                cardMore += 2;
                break;
            case FOURMORE:
                cardMore += 4;
                break;
            case DIRCHANGE:
                TurnDir = !TurnDir;
                break;
            case COLORCHANGE:
                Card.setColor(Color.BLACK);
                break;
        }

        Players.get(playerIdx).getHand().remove(Card);
        if (forbiddenPlayer) {
            playerIdx = setNextPlayer(playerIdx);
        }
        resulteffect.nextPlayer = setNextPlayer(playerIdx);
        return resulteffect;
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

        this.Deck.add(new Card(Value.ONE, Color.RED, 11));
        this.Deck.add(new Card(Value.TWO, Color.RED, 12));
        this.Deck.add(new Card(Value.THREE, Color.RED, 13));
        this.Deck.add(new Card(Value.FOUR, Color.RED, 14));
        this.Deck.add(new Card(Value.FIVE, Color.RED, 15));
        this.Deck.add(new Card(Value.SIX, Color.RED, 16));
        this.Deck.add(new Card(Value.SEVEN, Color.RED, 17));
        this.Deck.add(new Card(Value.EIGHT, Color.RED, 18));
        this.Deck.add(new Card(Value.NINE, Color.RED, 19));
        this.Deck.add(new Card(Value.ZERO, Color.RED, 20));

        this.Deck.add(new Card(Value.DIRCHANGE, Color.BLUE, 21));
        this.Deck.add(new Card(Value.DIRCHANGE, Color.BLUE, 22));
        this.Deck.add(new Card(Value.DIRCHANGE, Color.RED, 23));
        this.Deck.add(new Card(Value.DIRCHANGE, Color.RED, 24));

        this.Deck.add(new Card(Value.FORBIDDEN, Color.BLUE, 25));
        this.Deck.add(new Card(Value.FORBIDDEN, Color.BLUE, 26));
        this.Deck.add(new Card(Value.FORBIDDEN, Color.RED, 27));
        this.Deck.add(new Card(Value.FORBIDDEN, Color.RED, 28));

        this.Deck.add(new Card(Value.TWOMORE, Color.BLUE, 25));
        this.Deck.add(new Card(Value.TWOMORE, Color.BLUE, 26));
        this.Deck.add(new Card(Value.TWOMORE, Color.RED, 27));
        this.Deck.add(new Card(Value.TWOMORE, Color.RED, 28));


    }
}