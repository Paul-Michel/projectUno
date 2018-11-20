package uno.engine.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uno.engine.entities.Card;
import uno.engine.entities.CardPlayed;
import uno.engine.entities.Game;
import uno.engine.entities.Player;
import uno.engine.enums.Color;
import uno.engine.enums.Value;
import uno.engine.structs.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


@Service
@Slf4j
public class GameService {
    
    private Game myGame;
    

    public void newGame(List<Integer> idPlayers) {
        this.myGame = new Game();

        this.DeckCreate(); //Future recuperation des Cards dans la bdd
        Collections.shuffle(myGame.Deck);

        idPlayers.forEach(id -> {
            List<Card> hand = new ArrayList<>();
            hand.addAll(pickCard(7));
            myGame.Players.add(new Player(id, hand));
        });
        Integer i = 1;
        do {
            if (myGame.Deck.get(myGame.Deck.size() - i).getColor() == Color.BLACK ||
                    myGame.Deck.get(myGame.Deck.size() - i).getValue() == Value.FORBIDDEN ||
                    myGame.Deck.get(myGame.Deck.size() - i).getValue() == Value.DIRCHANGE ||
                    myGame.Deck.get(myGame.Deck.size() - i).getValue() == Value.TWOMORE) {
                i++;
            } else {
                myGame.Stack.add(myGame.Deck.get(myGame.Deck.size() - i));
                myGame.Deck.remove(myGame.Deck.size() - i);
                break;
            }

        } while (true);

    }

    public Result newTurn(Integer playerIdx) {

        Result Result = new Result();

        Card currentCard = myGame.Stack.get(myGame.Stack.size() - 1);
        Result.currentCard = currentCard;

        if (myGame.Deck.size() - myGame.cardMore <= 1) {

            myGame.Stack.remove(currentCard);
            myGame.Deck.addAll(myGame.Stack);
            myGame.Stack.clear();
            myGame.Stack.add(currentCard);
            Collections.shuffle(myGame.Deck);
        }

        if (!myGame.Players.get(playerIdx).setAvailableCard(currentCard)) {
            myGame.Players.get(playerIdx).pick(pickCard(1));
            if (!myGame.Players.get(playerIdx).setAvailableCard(currentCard)) {
                CardMore(playerIdx);
                Result.CanPlay = false;
                Result.nextPlayer = setNextPlayer(playerIdx);
            } else {
                Result.CanPlay = true;
            }
        } else {
            Result.CanPlay = true;
        }


        Result.hand = myGame.Players.get(playerIdx).getHand();
        return Result;
    }

    public Result effect(CardPlayed cardplayed) {

        Integer playerIdx = cardplayed.getPlayerIdx();
        Card Card = cardplayed.getCard();

        Result Result = new Result();
        Boolean forbiddenPlayer = false;

        if (Card.getValue() != Value.TWOMORE && Card.getValue() != Value.FOURMORE) {
            CardMore(playerIdx);
        }

        myGame.Stack.add(Card);
        switch (Card.getValue()) {
            case FORBIDDEN:
                forbiddenPlayer = true;
                break;
            case TWOMORE:
                myGame.cardMore += 2;
                break;
            case FOURMORE:
                myGame.cardMore += 4;
                break;
            case DIRCHANGE:
                myGame.TurnDir = !myGame.TurnDir;
                break;
            case COLORCHANGE:
                Card.setColor(Color.BLACK);
                break;
        }


        Card c = myGame.Players.get(playerIdx).getHand()
                .stream()
                .filter(card -> card.getId().equals(Card.getId()))
                .findFirst()
                .get();
        myGame.Players.get(playerIdx).getHand().remove(c);
        Result.CanRePlay = myGame.Players.get(playerIdx).setOtherAvailableCard(Card);
        Result.hand = myGame.Players.get(playerIdx).getHand();
        Result.currentCard = Card;
        if (myGame.Players.get(playerIdx).getHand().size() <= 0) {
            myGame.Players.remove(myGame.Players.get(playerIdx));
            System.out.println("remove playeur");
        } else if (myGame.Players.size() == 1) {
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

        if (myGame.TurnDir) {
            if (currentPlayerIdx + 1 <= myGame.Players.size() - 1) {
                nextPlayer = currentPlayerIdx + 1;
            } else {
                nextPlayer = 0;
            }

        } else {
            if (currentPlayerIdx - 1 >= 0) {
                nextPlayer = currentPlayerIdx - 1;
            } else {
                nextPlayer = myGame.Players.size() - 1;
            }
        }

        return nextPlayer;
    }

    private void CardMore(Integer playerIdx) {
        if (myGame.cardMore != 0) {
            myGame.Players.get(playerIdx).pick(pickCard(myGame.cardMore));
            myGame.cardMore = 0;
        }
    }

    private List<Card> pickCard(Integer numberCard) {

        List<Card> cards = new ArrayList<>();

        for (Integer i = 0; i < numberCard; i++) {
            cards.add(myGame.Deck.get(myGame.Deck.size() - 1));
            myGame.Deck.remove(myGame.Deck.size() - 1);
        }
        return cards;
    }

    private void DeckCreate() {
        myGame.Deck.add(new Card(Value.ONE, Color.BLUE, 1));
        myGame.Deck.add(new Card(Value.TWO, Color.BLUE, 2));
        myGame.Deck.add(new Card(Value.THREE, Color.BLUE, 3));
        myGame.Deck.add(new Card(Value.FOUR, Color.BLUE, 4));
        myGame.Deck.add(new Card(Value.FIVE, Color.BLUE, 5));
        myGame.Deck.add(new Card(Value.SIX, Color.BLUE, 6));
        myGame.Deck.add(new Card(Value.SEVEN, Color.BLUE, 7));
        myGame.Deck.add(new Card(Value.EIGHT, Color.BLUE, 8));
        myGame.Deck.add(new Card(Value.NINE, Color.BLUE, 9));
        myGame.Deck.add(new Card(Value.ZERO, Color.BLUE, 10));

        myGame.Deck.add(new Card(Value.ONE, Color.RED, 11));
        myGame.Deck.add(new Card(Value.TWO, Color.RED, 12));
        myGame.Deck.add(new Card(Value.THREE, Color.RED, 13));
        myGame.Deck.add(new Card(Value.FOUR, Color.RED, 14));
        myGame.Deck.add(new Card(Value.FIVE, Color.RED, 15));
        myGame.Deck.add(new Card(Value.SIX, Color.RED, 16));
        myGame.Deck.add(new Card(Value.SEVEN, Color.RED, 17));
        myGame.Deck.add(new Card(Value.EIGHT, Color.RED, 18));
        myGame.Deck.add(new Card(Value.NINE, Color.RED, 19));
        myGame.Deck.add(new Card(Value.ZERO, Color.RED, 20));

        myGame.Deck.add(new Card(Value.DIRCHANGE, Color.BLUE, 21));
        myGame.Deck.add(new Card(Value.DIRCHANGE, Color.BLUE, 22));
        myGame.Deck.add(new Card(Value.DIRCHANGE, Color.RED, 23));
        myGame.Deck.add(new Card(Value.DIRCHANGE, Color.RED, 24));

        myGame.Deck.add(new Card(Value.FORBIDDEN, Color.BLUE, 25));
        myGame.Deck.add(new Card(Value.FORBIDDEN, Color.BLUE, 26));
        myGame.Deck.add(new Card(Value.FORBIDDEN, Color.RED, 27));
        myGame.Deck.add(new Card(Value.FORBIDDEN, Color.RED, 28));

        myGame.Deck.add(new Card(Value.TWOMORE, Color.BLUE, 25));
        myGame.Deck.add(new Card(Value.TWOMORE, Color.BLUE, 26));
        myGame.Deck.add(new Card(Value.TWOMORE, Color.RED, 27));
        myGame.Deck.add(new Card(Value.TWOMORE, Color.RED, 28));


    }
}
