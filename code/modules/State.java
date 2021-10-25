package modules;

public class State implements Comparable<State>{
    private int x, y;
    private int health;
    private int carry;
    private Grid grid;

    @Override
    public int compareTo(State o) {
        return 0;
    }
}
