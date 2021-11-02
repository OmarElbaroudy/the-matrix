package modules;

public class State implements Comparable<State>{
    //for neo
    private int x, y;
    private int damage;
    private int remCarry;
    private Grid grid;

    @Override
    public int compareTo(State o) {
        return 0;
    }
}
