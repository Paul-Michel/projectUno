package uno.engine.enums;

public enum Value {
    ONE("one"),
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    EIGHT("eight"),
    NINE("nine"),
    ZERO("zero"),
    FOURMORE("fourmore"),
    TWOMORE("twomore"),
    DIRCHANGE("dirchange"),
    COLORCHANGE("colorchange"),
    FORBIDDEN("forbidden");

    private String value;

    Value(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
