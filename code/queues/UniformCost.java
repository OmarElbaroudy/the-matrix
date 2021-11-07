package queues;

import modules.Node;

import java.util.List;
import java.util.PriorityQueue;

public class UniformCost extends Queue {
    PriorityQueue<Node> pq = new PriorityQueue<>();


    @Override
    public boolean isEmpty() {
        return pq.isEmpty();
    }


    @Override
    public void add(List<Node> nodes) {
        nodes.forEach((node) -> {
            if (!(cost.containsKey(node.getState()))) {
                cost.put(node.getState(), node.getPathCost());
                pq.add(node);
            }
        });
    }


    @Override
    public Node removeFront() {
        return pq.poll();
    }
}
