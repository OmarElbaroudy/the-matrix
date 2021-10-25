package containers;

import modules.Node;
import modules.State;

import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public abstract class Container {
    protected TreeMap<State, Integer> cost;
    protected Node root;

    public abstract boolean isEmpty();

    public abstract void add(List<Node> nodes);

    public abstract Node poll();

    public final void init(Node node) {
        //TODO add state comparable
        root = node;
        cost = new TreeMap<>();
        add(Collections.singletonList(node));
    }
}
