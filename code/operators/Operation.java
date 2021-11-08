package operators;

public enum Operation {
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right"),
    FLY("fly"),
    TAKE_PILL("takePill"),
    KILL("kill"),
    CARRY("carry"),
    DROP("drop");

    private final String value;

    Operation(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

}
