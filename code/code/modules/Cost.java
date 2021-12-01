package code.modules;

import code.services.InfoExtractor;

public class Cost implements Comparable<Cost> {
    private final short depth;
    private short kills;
    private byte drops;
    private byte deaths;

    public Cost() {
        kills = drops = deaths = 0;
        depth = 1;
    }

    public Cost(Cost pathCost, Cost cost, State s) {
        this.depth = (short) (pathCost.depth + cost.depth);
        this.kills = (short) (pathCost.kills + cost.kills);
        this.drops = (byte) (pathCost.drops + cost.drops);
        this.drops = (byte) Math.min(drops, InfoExtractor.numberOfHostages);
        this.updateDeaths(s);
    }

    public Cost(int depth, int kills, int drops) {
        this.depth = (short) (depth);
        this.kills = (short) (kills);
        this.drops = (byte) (drops);
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

    private int getAliveHostages(State state) {
        return (int) state.getCarriedDamages().
                stream().filter(x -> x < 100).count()
                + state.aliveHostages();
    }

    private int getAliveHostagesInGrid(Grid grid) {
        int ret = 0;
        for (int i = 0; i < grid.getN(); i++) {
            for (int j = 0; j < grid.getM(); j++) {
                Host type = grid.getHostAtPos(i, j);
                ret += type == Host.HOSTAGE ? 1 : 0;
            }
        }
        return ret;
    }

    private void updateDeaths(State state) {
        this.deaths = (byte) (InfoExtractor.numberOfHostages -
                (this.drops + getAliveHostages(state)));
        this.deaths = (byte) Math.max(deaths, 0);
    }

    @Override
    public int compareTo(Cost o) {
        if (this.deaths == o.deaths
                && this.kills == o.kills) {
            return this.depth - o.depth;
        }

        if (deaths == o.deaths) {
            return this.kills - o.kills;
        }

        return this.deaths - o.deaths;
    }

}
