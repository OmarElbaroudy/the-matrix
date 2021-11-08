package queues;

import modules.Cost;
import modules.Node;
import modules.State;

import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public abstract class Queue {
    protected TreeMap<State, Cost> mp;
    protected Node root;

    public abstract boolean isEmpty();

    public abstract void add(List<Node> nodes);

    public abstract Node removeFront();

    public final void makeQ(Node node) {
        root = node;
        mp = new TreeMap<>();
        add(Collections.singletonList(node));
    }
}
