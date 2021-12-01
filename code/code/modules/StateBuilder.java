package code.modules;

import java.util.List;

public class StateBuilder {

    Grid grid;
    byte x, y;
    byte damage;
    byte remCarry;
    byte numOfAliveHostages;
    List<Byte> carriedDamages;

    public StateBuilder xPos(int x) {
        this.x = (byte) x;
        return this;
    }

    public StateBuilder yPos(int y) {
        this.y = (byte) y;
        return this;
    }

    public StateBuilder damage(int damage) {
        this.damage = (byte) damage;
        return this;
    }

    public StateBuilder remCarry(int remCarry) {
        this.remCarry = (byte) remCarry;
        return this;
    }

    public StateBuilder numOfAliveHostages(int numOfAliveHostages) {
        this.numOfAliveHostages = (byte) numOfAliveHostages;
        return this;
    }

    public StateBuilder Grid(Grid grid) {
        this.grid = grid;
        return this;
    }

    public StateBuilder carriedDamages(List<Byte> carriedDamages) {
        this.carriedDamages = carriedDamages;
        return this;
    }

    public State build() {
        return new State(this);
    }
}
