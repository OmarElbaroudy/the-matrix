package queues;

import modules.Node;

import java.util.LinkedList;
import java.util.List;

public class BreadthFirst extends Queue {
    private final java.util.Queue<Node> q = new LinkedList<>();

    @Override
    public boolean isEmpty() {
        return q.isEmpty();
    }

    @Override
    public void add(List<Node> nodes) {
        nodes.forEach((node) -> {
            if (!cost.containsKey(node.getState())) {
                cost.put(node.getState(), node.getPathCost());
                q.add(node);
            }
        });
    }

    @Override
    public Node removeFront() {
        return q.poll();
    }
}
