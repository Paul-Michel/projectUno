package uno.engine.entities;

//import com.sun.org.apache.bcel.internal.Const;

import uno.engine.enums.Color;
import uno.engine.enums.Value;
import uno.engine.structs.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
            hand.addAll(pickCard(7));
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

    public Result newTurn(Integer playerIdx) {

        Result Result = new Result();

        Card currentCard = Stack.get(Stack.size() - 1);
        Result.currentCard = currentCard;

        if (Deck.size() - cardMore <= 1) {

            Stack.remove(currentCard);
            Deck.addAll(Stack);
            Stack.clear();
            Stack.add(currentCard);
            Collections.shuffle(Deck);
        }

        if (!Players.get(playerIdx).setAvailableCard(currentCard)) {
            Players.get(playerIdx).pick(pickCard(1));
            if (!Players.get(playerIdx).setAvailableCard(currentCard)) {
                CardMore(playerIdx);
                Result.CanPlay = false;
                Result.nextPlayer = setNextPlayer(playerIdx);
            } else {
                Result.CanPlay = true;
            }
        } else {
            Result.CanPlay = true;
        }


        Result.hand = Players.get(playerIdx).getHand();
        return Result;
    }

    public Result effect(Integer playerIdx, Card Card) {

        Result Result = new Result();
        Boolean forbiddenPlayer = false;

        if (Card.getValue() != Value.TWOMORE && Card.getValue() != Value.FOURMORE) {
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


        Card c = Players.get(playerIdx).getHand()
                .stream()
                .filter(card -> card.getId().equals(Card.getId()))
                .findFirst()
                .get();
        Players.get(playerIdx).getHand().remove(c);
        Result.CanRePlay = Players.get(playerIdx).setOtherAvailableCard(Card);
        Result.hand = Players.get(playerIdx).getHand();
        Result.currentCard = Card;
        if (Players.get(playerIdx).getHand().size() <= 0) {
            Players.remove(Players.get(playerIdx));
            System.out.println("remove playeur");
        } else if (Players.size() == 1) {
            //Fin de partie !!
            System.out.println("Fin de partie !!!");
        }
        if (forbiddenPlayer) {
            playerIdx = setNextPlayer(playerIdx);
        }
        Result.nextPlayer = setNextPlayer(playerIdx);
        return Result;
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