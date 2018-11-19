package uno.engine.entities;


import org.junit.Test;
import uno.engine.enums.Value;
import uno.engine.structs.Result;
import uno.engine.structs.Result;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {

    @Test
    public void effectDirChange() {
        List<Integer> players = new ArrayList<>();
        players.add(111);
        players.add(222);
        players.add(333);

        Game myGame = new Game(players);

        Result Result = myGame.newTurn(0);

        assertThat(myGame.effect(0, new Card(Value.DIRCHANGE, Result.currentCard.getColor(), 1)).nextPlayer).isEqualTo(2);
    }

    @Test
    public void effectForbidden() {
        List<Integer> players = new ArrayList<>();
        players.add(111);
        players.add(222);
        players.add(333);

        Game myGame = new Game(players);

        Result Result = myGame.newTurn(0);

        assertThat(myGame.effect(0, new Card(Value.FORBIDDEN, Result.currentCard.getColor(), 1)).nextPlayer).isEqualTo(2);
    }

    @Test
    public void effectMoreCard() {
        List<Integer> players = new ArrayList<>();
        players.add(111);
        players.add(222);
        players.add(333);

        Game myGame = new Game(players);

        Result resultnewturn = myGame.newTurn(0);
        Result resulteffect = myGame.effect(0, new Card(Value.TWOMORE, resultnewturn.currentCard.getColor(), 1)); //Joueur 0

        resultnewturn = myGame.newTurn(resulteffect.nextPlayer);
        resulteffect = myGame.effect(resulteffect.nextPlayer, new Card(Value.FOURMORE, resultnewturn.currentCard.getColor(), 1)); //Joueur 1

        resultnewturn = myGame.newTurn(resulteffect.nextPlayer);
        resulteffect = myGame.effect(resulteffect.nextPlayer, new Card(Value.ONE, resultnewturn.currentCard.getColor(), 1)); //joueur 2

        assertThat(resulteffect.hand.size()).isGreaterThanOrEqualTo(12);
    }
}