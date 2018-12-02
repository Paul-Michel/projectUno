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

    public Card(Long id, Value value, Color color, Boolean playable) {
        this.id = id;
        this.value = value;
        this.color = color;
        this.playable = playable;
    }

    public Card(){

    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean getPlayable() {
        return playable;
    }

    public void setPlayable(Boolean playable) {
        this.playable = playable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
