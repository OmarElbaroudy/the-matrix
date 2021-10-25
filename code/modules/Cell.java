package modules;

public class Cell {
    private final Host host;
    private int damage;
    private int toX;
    private int toY;

    public Cell(Host host) {
        this.host = host;
    }

    public Cell(int damage) {
        this.host = Host.Hostage;
        this.damage = damage;
    }

    public Cell(int toX, int toY) {
        this.host = Host.Pad;
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

}
