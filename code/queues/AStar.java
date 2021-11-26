package queues;

import modules.Node;

import java.util.List;
import java.util.PriorityQueue;

public class AStar extends Queue {
    protected PriorityQueue<Node> pq;

    public AStar(int i) {
        pq = new PriorityQueue<>();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void add(List<Node> nodes) {

    }

    @Override
    public Node removeFront() {
        return null;
    }
}
