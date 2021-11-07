package modules;

import java.util.Objects;

public class State {
    private final int x, y;
    private final int damage;
    private final int remCarry;
    private final Grid grid;

    public State(StateBuilder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.damage = builder.damage;
        this.remCarry = builder.remCarry;
        this.grid = builder.grid;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDamage() {
        return damage;
    }

    public int getRemCarry() {
        return remCarry;
    }

    public Grid getGrid() {
        return grid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return x == state.x && y == state.y && damage == state.damage && remCarry == state.remCarry && grid.equals(state.grid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, damage, remCarry, grid);
    }

    public static class StateBuilder {
        private int x, y;
        private int damage;
        private int remCarry;
        private Grid grid;

        public StateBuilder xPos(int x) {
            this.x = x;
            return this;
        }

        public StateBuilder yPos(int y) {
            this.y = y;
            return this;
        }

        public StateBuilder damage(int damage) {
            this.damage = damage;
            return this;
        }

        public StateBuilder remCarry(int remCarry) {
            this.remCarry = remCarry;
            return this;
        }

        public StateBuilder Grid(Grid grid) {
            this.grid = grid;
            return this;
        }

        public State build() {
            return new State(this);
        }
    }
}
