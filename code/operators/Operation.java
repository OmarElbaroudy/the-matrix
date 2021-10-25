package operators;

public enum Operation {
    Up("up"),
    Down("down"),
    Left("left"),
    Right("right"),
    Fly("fly"),
    TakePill("takePill"),
    Kill("kill"),
    Carry("carry"),
    Drop("drop");

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
