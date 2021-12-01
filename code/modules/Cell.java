package modules;

public class Cell {
    private Host host;
    private byte damage;
    private byte toX;
    private byte toY;

    //exception hostage, pad
    public Cell(Host host) {
        this.host = host;
    }

    public Cell(int damage) {
        this.host = Host.HOSTAGE;
        this.damage = (byte) damage;
    }

    public Cell(int toX, int toY) {
        this.host = Host.PAD;
        this.toX = (byte) toX;
        this.toY = (byte) toY;
    }

    public Host getHost() {
        return host;
    }

    public byte getDamage() {
        return damage;
    }

    public byte getToX() {
        return toX;
    }

    public byte getToY() {
        return toY;
    }

    public boolean heal(int parentDamage) {
        boolean flag = false;
        if (this.host == Host.MUTATED_AGENT &&
                (parentDamage == 98 || parentDamage == 99)) {
            this.host = Host.HOSTAGE;
            flag = true;
        }
        this.damage = (byte) Math.max(0, parentDamage - 20);
        return flag;
    }
}
