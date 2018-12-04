package uno.engine.entities;


import jdk.internal.jline.internal.TestAccessible;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import uno.engine.enums.Color;
import uno.engine.enums.Value;
import uno.engine.services.GameService;
import uno.engine.structs.Result;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {

    @Test
    public void the_game_should_be_correctly_initialized() {
        List<String> players = new ArrayList<>();
        players.add("111");
        players.add("222");
        players.add("333");

        GameService myGame = new GameService();
        myGame.newGame(players);

        assertThat(myGame.getMyGame().getPlayers().size()).isEqualTo(3);
        assertThat(myGame.getMyGame().getPlayers().get(0).getHand().size()).isEqualTo(7);
        assertThat(myGame.getMyGame().getPlayers().get(1).getHand().size()).isEqualTo(7);
        assertThat(myGame.getMyGame().getPlayers().get(2).getHand().size()).isEqualTo(7);
        assertThat(myGame.getMyGame().getDeck().size()).isNotZero();
        assertThat(myGame.getMyGame().getStack().size()).isEqualTo(1);
    }

    @Test
    public void player_should_pick_a_card() {
        List<String> players = new ArrayList<>();
        players.add("111");
        players.add("222");
        players.add("333");

        GameService myGame = new GameService();
        myGame.newGame(players);

        assertThat(myGame.getMyGame().getPlayers().get(0).getHand().size()).isEqualTo(7);

        Color tempColor;
        if (myGame.getMyGame().getStack().get(myGame.getMyGame().getStack().size() - 1).getColor() == Color.RED) {
            tempColor = Color.BLUE;
        } else {
            tempColor = Color.RED;
        }


        Value tempValue;
        if (myGame.getMyGame().getStack().get(myGame.getMyGame().getStack().size() - 1).getValue() == Value.ONE) {
            tempValue = Value.TWO;
        } else {
            tempValue = Value.ONE;
        }


        List<Card> hand = new ArrayList<>();
        hand.add(new Card(tempValue, tempColor, 1254));
        myGame.getMyGame().getPlayers().get(0).setHand(hand);
        assertThat(myGame.getMyGame().getPlayers().get(0).getHand().size()).isEqualTo(1);
        myGame.newTurn(0);
        assertThat(myGame.getMyGame().getPlayers().get(0).getHand().size()).isEqualTo(2);
    }

    @Test
    public void the_current_card_Should_change() {
        List<String> players = new ArrayList<>();
        players.add("111");
        players.add("222");
        players.add("333");

        GameService myGame = new GameService();
        myGame.newGame(players);

        Card card = new Card(Value.TWOMORE,
                myGame.getMyGame().getStack().get(myGame.getMyGame().getStack().size() - 1).getColor(),
                1254);
        myGame.getMyGame().getPlayers().get(0).getHand().add(card);

        myGame.newTurn(0);

        assertThat(myGame.effect(new CardPlayed(0, card)).currentCard).isEqualTo(card);
    }

    @Test
    public void the_turn_direction_should_change() {
        List<String> players = new ArrayList<>();
        players.add("111");
        players.add("222");
        players.add("333");

        GameService myGame = new GameService();
        myGame.newGame(players);

        Card card = new Card(Value.DIRCHANGE,
                myGame.getMyGame().getStack().get(myGame.getMyGame().getStack().size() - 1).getColor(),
                1);
        myGame.getMyGame().getPlayers().get(0).getHand().add(card);

        myGame.newTurn(0);
        assertThat(myGame.effect(new CardPlayed(0, card)).nextPlayer).isEqualTo(2);
    }

    @Test
    public void the_next_player_should_pass_turn() {
        List<String> players = new ArrayList<>();
        players.add("111");
        players.add("222");
        players.add("333");

        GameService myGame = new GameService();
        myGame.newGame(players);

        Card card = new Card(Value.FORBIDDEN,
                myGame.getMyGame().getStack().get(myGame.getMyGame().getStack().size() - 1).getColor(),
                1);
        myGame.getMyGame().getPlayers().get(0).getHand().add(card);

        myGame.newTurn(0);
        assertThat(myGame.effect(new CardPlayed(0, card)).nextPlayer).isEqualTo(2);
    }

    @Test
    public void the_third_player_should_pick_six_card() {
        List<String> players = new ArrayList<>();
        players.add("111");
        players.add("222");
        players.add("333");

        GameService myGame = new GameService();
        myGame.newGame(players);

        Card card = new Card(Value.TWOMORE,
                myGame.getMyGame().getStack().get(myGame.getMyGame().getStack().size() - 1).getColor(),
                1);
        myGame.getMyGame().getPlayers().get(0).getHand().add(card);

        myGame.newTurn(0);
        Result resulteffect = myGame.effect(new CardPlayed(0, card)); //Joueur 0


        card = new Card(Value.FOURMORE,
                myGame.getMyGame().getStack().get(myGame.getMyGame().getStack().size() - 1).getColor(),
                1);
        myGame.getMyGame().getPlayers().get(resulteffect.nextPlayer).getHand().add(card);

        myGame.newTurn(resulteffect.nextPlayer);
        resulteffect = myGame.effect(new CardPlayed(resulteffect.nextPlayer, card)); //Joueur 1


        card = new Card(Value.ONE,
                myGame.getMyGame().getStack().get(myGame.getMyGame().getStack().size() - 1).getColor(),
                1);
        myGame.getMyGame().getPlayers().get(resulteffect.nextPlayer).getHand().add(card);

        myGame.newTurn(resulteffect.nextPlayer);
        resulteffect = myGame.effect(new CardPlayed(resulteffect.nextPlayer, card)); //joueur 2

        assertThat(resulteffect.hand.size()).isEqualTo(13);
    }

    @Test
    public void the_end_game_should_be_detected() {
        List<String> players = new ArrayList<>();
        players.add("111");
        players.add("222");


        GameService myGame = new GameService();
        myGame.newGame(players);


        Color tempColor = myGame.getMyGame().getStack().get(myGame.getMyGame().getStack().size() - 1).getColor();

        Value tempValue = myGame.getMyGame().getStack().get(myGame.getMyGame().getStack().size() - 1).getValue();

        List<Card> hand = new ArrayList<>();
        Card card = new Card(tempValue, tempColor, 1254);
        hand.add(card);
        myGame.getMyGame().getPlayers().get(0).setHand(hand);


        assertThat(myGame.getMyGame().getPlayers().get(0).getHand().size()).isEqualTo(1);
        assertThat(myGame.newTurn(0).CanPlay).isEqualTo(true);
        assertThat(myGame.effect(new CardPlayed(0, card)).gameEnd).isEqualTo(true);
    }

    @Test
    public void the_current_color_should_change() {
        List<String> players = new ArrayList<>();
        players.add("111");
        players.add("222");


        GameService myGame = new GameService();
        myGame.newGame(players);

        Color tempColor;
        if (myGame.getMyGame().getStack().get(myGame.getMyGame().getStack().size() - 1).getColor() == Color.RED) {
            tempColor = Color.BLUE;
        } else {
            tempColor = Color.RED;
        }
        myGame.getMyGame().getPlayers().get(0).getHand().add(new Card(Value.COLORCHANGE, tempColor, 1254));


        myGame.newTurn(0);
        assertThat(myGame.effect(new CardPlayed(0, new Card(Value.COLORCHANGE, tempColor, 1254)))
                .currentCard.getColor()).isEqualTo(tempColor);

        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Value.ONE, tempColor, 1255));
        myGame.getMyGame().getPlayers().get(1).setHand(hand);

        assertThat(myGame.newTurn(1).hand.get(0).getPlayable()).isEqualTo(true);
    }
}