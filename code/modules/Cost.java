package modules;

public class Cost implements Comparable<Cost> {
    //private int pills;
    private final int depth;
    private int drops;
    private int kills;

    public Cost() {
        drops = kills = 0;
        depth = 1;
    }

    public Cost(Cost pathCost, Cost cost) {
        this.depth = pathCost.depth + cost.depth;
        this.drops = pathCost.drops + cost.drops;
        this.kills = pathCost.kills + cost.kills;
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
