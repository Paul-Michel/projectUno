package uno.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EngineApplication {
    private static Game myGame;


    public static void main(String[] args) {
        SpringApplication.run(EngineApplication.class, args);
    }

    public static int turn(Integer playerIdx) {

        ResultNewTurn resultNewTurn = myGame.newTurn(playerIdx);
        List<Card> hand = new ArrayList<>(resultNewTurn.hand);

        System.out.println("Current Card: "
                + resultNewTurn.currentCard.getValue() + " "
                + resultNewTurn.currentCard.getColor());

        System.out.println("Player: " + (playerIdx + 1));
        hand.forEach(card -> {
            System.out.println(card.getValue() + " " + card.getColor() + " " + card.getPlayable());
        });


        System.out.println("Pick a card : ");


        Scanner scanner = new Scanner(System.in);
        int inp;
        do {
            inp = scanner.nextInt();
            System.out.println("coucou");
            if (resultNewTurn.hand.get(inp).getPlayable()) {
                break;
            } else {
                System.out.println("Wrong card : ");
            }
        } while (true);


        Resulteffect resulteffect = myGame.effect(playerIdx, resultNewTurn.hand.get(inp));
        return resulteffect.nextPlayer;
    }

}
