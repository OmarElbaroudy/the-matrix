package code.modules;

public enum Host {
    EMPTY("   "),
    AGENT("AG "),
    HOSTAGE("HO "),
    PAD("PA "),
    PILL("PI "),
    MUTATED_AGENT("MA "),
    TELEPHONE("TB ");

    private final String value;

    Host(final String value) {
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
