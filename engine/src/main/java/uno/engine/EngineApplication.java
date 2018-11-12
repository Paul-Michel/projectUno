package uno.engine;

import uno.engine.Entities.Card;
import uno.engine.Entities.Game;
import uno.engine.enums.Color;
import uno.engine.enums.Value;

import java.util.ArrayList;
import java.util.List;


//@SpringBootApplication
public class EngineApplication {

    public static void main(String[] args) {
        //SpringApplication.run(EngineApplication.class, args);
        List<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        Game myGame = new Game(test);
       List<Card> hand = myGame.newTurn(1);
        myGame.effect(1, new Card(Value.FIVE, Color.RED, 11));
    }

}
