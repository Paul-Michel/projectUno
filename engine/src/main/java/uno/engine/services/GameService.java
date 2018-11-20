package uno.engine.services;

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
public class GameService {


    @Autowired
    private CardService cardService;
    private Game myGame;
    private PlayerService playerService = new PlayerService();

    public void newGame(List<Integer> idPlayers) {
        this.myGame = new Game();


        myGame.deck = cardService.getAllCards();
        Collections.shuffle(myGame.deck);

        idPlayers.forEach(id -> {
            List<Card> hand = new ArrayList<>(pickCard(7));
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
            case COLORCHANGE:
                Card.setColor(Color.BLACK);
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
            myGame.players.remove(myGame.players.get(playerIdx));
            System.out.println("remove playeur");
        } else if (myGame.players.size() == 1) {
            Result.gameEnd = true;
            System.out.println("Fin de partie !!!");
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
}
