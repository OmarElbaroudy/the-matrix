package modules;

import java.util.List;

public class StateBuilder {
    int x, y;
    int damage;
    int remCarry;
    Grid grid;
    List<Integer> carriedDamages;

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

    public StateBuilder carriedDamages(List<Integer> carriedDamages) {
        this.carriedDamages = carriedDamages;
        return this;
    }

    public State build() {
        return new State(this);
    }
}
