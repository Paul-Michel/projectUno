package uno.engine.entities;

import uno.engine.enums.Color;
import uno.engine.enums.Value;

public class Card {

    private Integer id;
    private Value value;
    private Color color;
    private Boolean playable;


    public Card(Value value, Color color, Integer id) {
        this.id = id;
        this.value = value;
        this.color = color;
        this.playable = false;
    }

    public Integer getId() {
        return id;
    }

    public Value getValue() {
        return value;
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
}
