package modules;

import java.util.Objects;

public class Cell {
    private final Host host;
    private int damage;
    private int toX;
    private int toY;

    //exception hostage, pad
    public Cell(Host host) {
        this.host = host;
    }

    public Cell(int damage) {
        this.host = Host.HOSTAGE;
        this.damage = damage;
    }

    public Cell(int toX, int toY) {
        this.host = Host.PAD;
        this.toX = toX;
        this.toY = toY;
    }

    public Host getHost() {
        return host;
    }

    public int getDamage() {
        return damage;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }

    public void heal() {
        this.damage = Math.max(0, damage - 20);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return damage == cell.damage && toX == cell.toX && toY == cell.toY && host == cell.host;
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, damage, toX, toY);
    }


}
