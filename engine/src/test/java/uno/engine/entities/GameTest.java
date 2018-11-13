package uno.engine.entities;


import org.junit.Test;
import uno.engine.enums.Value;
import uno.engine.structs.ResultNewTurn;
import uno.engine.structs.Resulteffect;


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

        ResultNewTurn resultNewTurn = myGame.newTurn(0);

        assertThat(myGame.effect(0, new Card(Value.DIRCHANGE, resultNewTurn.currentCard.getColor(), 1)).nextPlayer).isEqualTo(2);
    }

    @Test
    public void effectForbidden() {
        List<Integer> players = new ArrayList<>();
        players.add(111);
        players.add(222);
        players.add(333);

        Game myGame = new Game(players);

        ResultNewTurn resultNewTurn = myGame.newTurn(0);

        assertThat(myGame.effect(0, new Card(Value.FORBIDDEN, resultNewTurn.currentCard.getColor(), 1)).nextPlayer).isEqualTo(2);
    }

    @Test
    public void effectMoreCard() {
        List<Integer> players = new ArrayList<>();
        players.add(111);
        players.add(222);
        players.add(333);

        Game myGame = new Game(players);

        ResultNewTurn resultNewTurn = myGame.newTurn(0);
        Resulteffect resulteffect = myGame.effect(0, new Card(Value.TWOMORE, resultNewTurn.currentCard.getColor(), 1)); //Joueur 0

        resultNewTurn = myGame.newTurn(resulteffect.nextPlayer);
        resulteffect = myGame.effect(resulteffect.nextPlayer, new Card(Value.FOURMORE, resultNewTurn.currentCard.getColor(), 1)); //Joueur 1

        resultNewTurn = myGame.newTurn(resulteffect.nextPlayer);
        resulteffect = myGame.effect(resulteffect.nextPlayer, new Card(Value.ONE, resultNewTurn.currentCard.getColor(), 1)); //joueur 2

        assertThat(resulteffect.hand.size()).isEqualTo(13);
    }
}