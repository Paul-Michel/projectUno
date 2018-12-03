package uno.engine.services;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


@Service
@Slf4j
@Getter
@Setter
public class GameService {


    @Autowired
    private CardService cardService;
    private PlayerService playerService = new PlayerService();
    private PlayedGameService playedGameService = new PlayedGameService();
    private Game myGame;


    public void newGame(List<String> idPlayers) {
        this.myGame = new Game();

        //myGame.deck = cardService.getAllCards();

        this.deckCreate(); //Future recuperation des Cards dans la bdd
        Collections.shuffle(myGame.deck);

        idPlayers.forEach(id -> {
            List<Card> hand = new ArrayList<>(pickCard(1));
            myGame.players.add(new Player(id, hand));
        });
        int i = 1;
        do {
            if (myGame.deck.get(myGame.deck.size() - i).getColor() == Color.BLACK ||
                    myGame.deck.get(myGame.deck.size() - i).getValue() == Value.FORBIDDEN ||
                    myGame.deck.get(myGame.deck.size() - i).getValue() == Value.DIRCHANGE ||
                    myGame.deck.get(myGame.deck.size() - i).getValue() == Value.TWOMORE) {
                i++;
            } else {
                myGame.stack.add(myGame.deck.get(myGame.deck.size() - i));
                myGame.deck.remove(myGame.deck.size() - i);
                break;
            }

        } while (true);

    }

    public Result newTurn(Integer playerIdx) {

        Result Result = new Result();

        Card currentCard = myGame.stack.get(myGame.stack.size() - 1);
        Result.currentCard = currentCard;

        if (myGame.deck.size() - myGame.cardMore <= 1) {

            myGame.stack.remove(currentCard);
            myGame.deck.addAll(myGame.stack);
            myGame.stack.clear();
            myGame.stack.add(currentCard);
            Collections.shuffle(myGame.deck);
        }


        if (!playerService.setAvailableCard(currentCard, myGame.players.get(playerIdx))) {
            playerService.pick(pickCard(1), myGame.players.get(playerIdx));
            if (!playerService.setAvailableCard(currentCard, myGame.players.get(playerIdx))) {
                CardMore(playerIdx);
                Result.CanPlay = false;
                Result.nextPlayer = setNextPlayer(playerIdx);
            } else {
                Result.CanPlay = true;
            }
        } else {
            Result.CanPlay = true;
        }

        Result.hand = myGame.players.get(playerIdx).getHand();
        return Result;
    }

    public Result effect(CardPlayed cardplayed) {

        Integer playerIdx = cardplayed.getPlayerIdx();
        Card Card = cardplayed.getCard();

        Result Result = new Result();
        boolean forbiddenPlayer = false;

        if (Card.getValue() != Value.TWOMORE && Card.getValue() != Value.FOURMORE) {
            CardMore(playerIdx);
        }

        myGame.stack.add(Card);
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
                myGame.turnDir = !myGame.turnDir;
                break;
        }

        Card c = myGame.players.get(playerIdx).getHand()
                .stream()
                .filter(card -> card.getId().equals(Card.getId()))
                .findFirst()
                .get();

        myGame.players.get(playerIdx).getHand().remove(c);
        Result.CanRePlay = playerService.setOtherAvailableCard(Card, myGame.players.get(playerIdx));
        Result.hand = myGame.players.get(playerIdx).getHand();
        Result.currentCard = Card;

        if (myGame.players.get(playerIdx).getHand().size() <= 0) {
            Result.playerEnd = playerIdx;
            playedGameService.updateplayedGame(myGame.players.get(playerIdx).getId());
            myGame.players.remove(myGame.players.get(playerIdx));
            Result.gameEnd = false;
            if (myGame.players.size() == 1) {
                Result.gameEnd = true;
                playedGameService.updateplayedGame(myGame.players.get(0).getId());
                playedGameService.sendPlayedGame();
            }
        } else {
            Result.gameEnd = false;
        }

        if (forbiddenPlayer) {
            playerIdx = setNextPlayer(playerIdx);
        }
        Result.nextPlayer = setNextPlayer(playerIdx);
        return Result;
    }

    private Integer setNextPlayer(Integer currentPlayerIdx) {
        int nextPlayer;

        if (myGame.turnDir) {
            if (currentPlayerIdx + 1 <= myGame.players.size() - 1) {
                nextPlayer = currentPlayerIdx + 1;
            } else {
                nextPlayer = 0;
            }

        } else {
            if (currentPlayerIdx - 1 >= 0) {
                nextPlayer = currentPlayerIdx - 1;
            } else {
                nextPlayer = myGame.players.size() - 1;
            }
        }

        return nextPlayer;
    }

    private void CardMore(Integer playerIdx) {
        if (myGame.cardMore != 0) {
            playerService.pick(pickCard(myGame.cardMore), myGame.players.get(playerIdx));
            myGame.cardMore = 0;
        }
    }

    private List<Card> pickCard(Integer numberCard) {

        List<Card> cards = new ArrayList<>();

        for (Integer i = 0; i < numberCard; i++) {
            cards.add(myGame.deck.get(myGame.deck.size() - 1));
            myGame.deck.remove(myGame.deck.size() - 1);
        }
        return cards;
    }

    private void deckCreate() {
        myGame.deck.add(new Card(Value.ONE, Color.BLUE, 1));
        myGame.deck.add(new Card(Value.TWO, Color.BLUE, 2));
        myGame.deck.add(new Card(Value.THREE, Color.BLUE, 3));
        myGame.deck.add(new Card(Value.FOUR, Color.BLUE, 4));
        myGame.deck.add(new Card(Value.FIVE, Color.BLUE, 5));
        myGame.deck.add(new Card(Value.SIX, Color.BLUE, 6));
        myGame.deck.add(new Card(Value.SEVEN, Color.BLUE, 7));
        myGame.deck.add(new Card(Value.EIGHT, Color.BLUE, 8));
        myGame.deck.add(new Card(Value.NINE, Color.BLUE, 9));
        myGame.deck.add(new Card(Value.ZERO, Color.BLUE, 10));

        /*myGame.deck.add(new Card(Value.COLORCHANGE, Color.BLACK, 33));
        myGame.deck.add(new Card(Value.COLORCHANGE, Color.BLACK, 34));
        myGame.deck.add(new Card(Value.COLORCHANGE, Color.BLACK, 35));
        myGame.deck.add(new Card(Value.COLORCHANGE, Color.BLACK, 36));
        myGame.deck.add(new Card(Value.FOURMORE, Color.BLACK, 37));
        myGame.deck.add(new Card(Value.FOURMORE, Color.BLACK, 38));
        myGame.deck.add(new Card(Value.FOURMORE, Color.BLACK, 39));
        myGame.deck.add(new Card(Value.FOURMORE, Color.BLACK, 40));*/

        myGame.deck.add(new Card(Value.ONE, Color.RED, 11));
        myGame.deck.add(new Card(Value.TWO, Color.RED, 12));
        myGame.deck.add(new Card(Value.THREE, Color.RED, 13));
        myGame.deck.add(new Card(Value.FOUR, Color.RED, 14));
        myGame.deck.add(new Card(Value.FIVE, Color.RED, 15));
        myGame.deck.add(new Card(Value.SIX, Color.RED, 16));
        myGame.deck.add(new Card(Value.SEVEN, Color.RED, 17));
        myGame.deck.add(new Card(Value.EIGHT, Color.RED, 18));
        myGame.deck.add(new Card(Value.NINE, Color.RED, 19));
        myGame.deck.add(new Card(Value.ZERO, Color.RED, 20));

        myGame.deck.add(new Card(Value.DIRCHANGE, Color.BLUE, 21));
        myGame.deck.add(new Card(Value.DIRCHANGE, Color.BLUE, 22));
        myGame.deck.add(new Card(Value.DIRCHANGE, Color.RED, 23));
        myGame.deck.add(new Card(Value.DIRCHANGE, Color.RED, 24));

        myGame.deck.add(new Card(Value.FORBIDDEN, Color.BLUE, 25));
        myGame.deck.add(new Card(Value.FORBIDDEN, Color.BLUE, 26));
        myGame.deck.add(new Card(Value.FORBIDDEN, Color.RED, 27));
        myGame.deck.add(new Card(Value.FORBIDDEN, Color.RED, 28));

        myGame.deck.add(new Card(Value.TWOMORE, Color.BLUE, 29));
        myGame.deck.add(new Card(Value.TWOMORE, Color.BLUE, 30));
        myGame.deck.add(new Card(Value.TWOMORE, Color.RED, 31));
        myGame.deck.add(new Card(Value.TWOMORE, Color.RED, 32));

    }
}
