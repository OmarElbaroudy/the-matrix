package modules;

import services.VisualsHandler;

import java.util.List;
import java.util.Objects;

public class State {
    private final List<Integer> carriedDamages;
    private final Grid grid;
    private int damage;
    private int remCarry;
    private int x, y;

    public State(StateBuilder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.damage = builder.damage;
        this.remCarry = builder.remCarry;
        this.grid = builder.grid;
        this.carriedDamages = builder.carriedDamages;
    }

    public void move(int dx, int dy) {
        this.x += dy;
        this.y += dx;
    }

    public void transform(int x, int y) {
        this.x = getGrid().getPadXAtPos(x, y);
        this.y = getGrid().getPadYAtPos(x, y);
    }

    public void carry(int x, int y) {
        this.remCarry -= 1;
        this.carriedDamages.add(getGrid().getDamageAtPos(x, y));
        grid.clearPos(x, y);
    }

    public int drop() {
        int cnt = this.carriedDamages.size();
        this.carriedDamages.clear();
        remCarry += cnt;
        return cnt;
    }

    public void clearPos(int x, int y) {
        grid.clearPos(x, y);
    }

    public void healNeo() {
        this.damage = Math.max(0, damage - 20);
    }

    public boolean kill() {
        this.damage += 20;
        return damage < 100;
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

    public List<Integer> getCarriedDamages() {
        return carriedDamages;
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


    @Override
    public String toString() {
        return VisualsHandler.visualize(this);
    }
}
