package modules;

public class Cost implements Comparable<Cost> {
    //private int pills;
    private final short depth;
    private short kills;
    private byte drops;

    public Cost() {
        kills = drops = 0;
        depth = 1;
    }

    public Cost(Cost pathCost, Cost cost) {
        this.depth = (short) (pathCost.depth + cost.depth);
        this.kills = (short) (pathCost.kills + cost.kills);
        this.drops = (byte) (pathCost.drops + cost.drops);
    }

    public Cost drop(int x) {
        drops += x;
        return this;
    }

    public Cost kill(int x) {
        kills += x;
        return this;
    }

    public int getDepth() {
        return depth;
    }

    public int getDrops() {
        return this.drops;
    }

    public int getKills() {
        return this.kills;
    }

    @Override
    public int compareTo(Cost o) {
        if (this.drops == o.drops
                && this.kills == o.kills) {
            return this.depth - o.depth;
        }

        if (drops == o.drops) {
            return this.kills - o.kills;
        }

        return o.drops - this.drops;
    }
}
