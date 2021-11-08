package operators;

public class Cost implements Comparable<Cost> {
    private int drops;
    private int kills;
    private int depth;

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

    public Cost kill() {
        kills++;
        return this;
    }

    public Cost add(Cost o) {
        this.depth += o.depth;
        this.kills += o.kills;
        this.drops += o.drops;

        return this;
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
