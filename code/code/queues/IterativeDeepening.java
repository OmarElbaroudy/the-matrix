package code.queues;

import code.modules.Node;

import java.util.List;
import java.util.Stack;

public class IterativeDeepening extends Queue {
    private final int MAX_DEPTH = (int) (1e8);
    private final Stack<Node> st = new Stack<>();
    private int curDepth = 0;

    @Override
    public boolean isEmpty() {
        if (!st.isEmpty()) {
            return false;
        }

        if (curDepth >= MAX_DEPTH) {
            return true;
        }

        curDepth += 1;
        makeQ(root);
        return false;
    }

    @Override
    public void add(List<Node> nodes) {
        nodes.forEach((node) -> {
            int nodeDepth = node.getPathCost().getDepth();

            if (nodeDepth <= curDepth &&
                    !mp.containsKey(node.getState())) {

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
