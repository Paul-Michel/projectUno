package uno.engine.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uno.engine.entities.Card;
import uno.engine.entities.CardPlayed;
import uno.engine.entities.Game;
import uno.engine.structs.Result;

import java.util.List;


@Service
@Slf4j
public class GameService {
    Game myGame;

    public void newGame(List<Integer> Players) {
        myGame = new Game(Players);

    }

    public Result newTurn(Integer playerIdx) {
        return myGame.newTurn(playerIdx);
    }

    public Result effect(CardPlayed cardPlayed) {
        Integer playerIdx = cardPlayed.getPlayerIdx();
        Card card = new Card(cardPlayed.getCard().getValue(),cardPlayed.getCard().getColor(),cardPlayed.getCard().getId());
        return myGame.effect(playerIdx, card);
    }
}
