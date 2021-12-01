package code.queues;

import code.modules.Node;

import java.util.List;
import java.util.Stack;

public class DepthFirst extends Queue {
    private final Stack<Node> st = new Stack<>();

    @Override
    public boolean isEmpty() {
        return st.isEmpty();
    }

    @Override
    public void add(List<Node> nodes) {
        nodes.forEach((node) -> {
            if (!mp.containsKey(node.getState())) {
                mp.put(node.getState(), node.getPathCost());
                st.push(node);
            }
        });
    }

    @Override
    public Node removeFront() {
        return st.pop();
    }
}
