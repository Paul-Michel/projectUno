package uno.restapi.enums;

public enum Color {
    BLACK("black"),
    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    YELLOW("yellow");

    private String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
