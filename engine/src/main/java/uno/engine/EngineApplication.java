package uno.engine;

import uno.engine.entities.Card;
import uno.engine.entities.Game;
import uno.engine.entities.Player;
import uno.engine.structs.ResultNewTurn;
import uno.engine.structs.Resulteffect;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//@SpringBootApplication
public class EngineApplication {
    private static Game myGame;
    private static List<Integer> players = new ArrayList<>();

    public static void main(String[] args) {
        //SpringApplication.run(EngineApplication.class, args);
        players.add(111);
        players.add(222);
        players.add(333);
        myGame = new Game(players);

        int playerIdx = 0;

        while (true) {
            playerIdx = turn(playerIdx);
        }

    }

    public static int turn(Integer playerIdx) {

        ResultNewTurn resultNewTurn = myGame.newTurn(playerIdx);
        List<Card> hand = new ArrayList<>(resultNewTurn.hand);

        System.out.println("Current Card: "
                + resultNewTurn.currentCard.getValue() + " "
                + resultNewTurn.currentCard.getColor());

        System.out.println("Player: " + players.get(playerIdx));
        final Integer[] i = {0};
        hand.forEach(card -> {
            System.out.println(i[0] + " " + card.getValue() + " " + card.getColor() + " " + card.getPlayable());
            i[0]++;
        });

        if (resultNewTurn.CanPlay) {

            System.out.println("Pick a card : ");
            Scanner scanner = new Scanner(System.in);
            int inp;
            do {
                inp = scanner.nextInt();
                if (resultNewTurn.hand.get(inp).getPlayable()) {
                    break;
                } else {
                    System.out.println("Wrong card : ");
                }
            } while (true);


            Resulteffect resulteffect = myGame.effect(playerIdx, resultNewTurn.hand.get(inp));
            if(resulteffect.hand.size() == 0){
                players.remove(playerIdx);
            }
            return resulteffect.nextPlayer;
        } else {
            System.out.println("############################ Can't play -> next player");
            return resultNewTurn.nextplayer;
        }

    }
}