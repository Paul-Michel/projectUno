package uno.restapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uno.restapi.enums.Color;
import uno.restapi.enums.Value;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    private Long id;
    private Value value;
    private Color color;
    private Boolean playable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
