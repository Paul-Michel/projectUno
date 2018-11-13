package uno.engine.services;

import java.util.List;

public class GameService {

    public void newGame(List<Integer> players) {
        players.forEach(player -> {
            System.out.println(players);
        });
    }
}
