package queues;

import modules.Cost;
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
            Cost prevCost = mp.get(node.getState());

            if (prevCost == null ||
                    node.getPathCost().compareTo(prevCost) < 0) {

                mp.put(node.getState(), node.getPathCost());
                pq.add(node);
            }
        });
    }


    @Override
    public Node removeFront() {
        return pq.poll();
    }

    protected void setQueue(PriorityQueue<Node> pq) {
        this.pq = pq;
    }

}
