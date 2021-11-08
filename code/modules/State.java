package modules;

import services.VisualsHandler;

import java.util.List;

public class State implements Comparable<State> {
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
    public String toString() {
        return VisualsHandler.visualize(this);
    }

    @Override
    public int compareTo(State o) {

        if (this.x != o.x) return Integer.compare(this.x, o.x);
        if (this.y != o.y) return Integer.compare(this.y, o.y);
        if (this.damage != o.damage) return Integer.compare(this.damage, o.damage);

        int n = grid.getN(), m = grid.getM();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Host fst = this.grid.getHostAtPos(i, j);
                Host snd = o.grid.getHostAtPos(i, j);

                if (fst != snd) {
                    return fst.compareTo(snd);
                }

                if (fst == Host.HOSTAGE &&
                        this.grid.getDamageAtPos(i, j) !=
                                o.grid.getDamageAtPos(i, j)) {

                    return Integer.compare(this.grid.getDamageAtPos(i, j),
                            o.grid.getDamageAtPos(i, j));
                }
            }
        }

        if (this.carriedDamages.size() != o.carriedDamages.size()) {
            return Integer.compare(
                    this.carriedDamages.size(), o.carriedDamages.size());
        }

        for (int i = 0; i < this.carriedDamages.size(); i++) {
            int x = this.getCarriedDamages().get(i);
            int y = o.getCarriedDamages().get(i);
            if (x == y) continue;
            return Integer.compare(x, y);
        }
        return 0;
    }
}
