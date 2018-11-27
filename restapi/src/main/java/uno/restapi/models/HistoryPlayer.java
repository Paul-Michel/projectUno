package uno.restapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HistoryPlayer {

    private Long id;
    private String name;
    private Object playedGames;
}
