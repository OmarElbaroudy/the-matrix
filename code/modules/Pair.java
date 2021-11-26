package modules;

public class Pair implements Comparable<Pair> {
    private final int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.x == o.x) {
            return this.y - o.y;
        }

        return this.x - o.x;
    }
}
