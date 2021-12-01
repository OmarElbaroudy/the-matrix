package code.evaluators;

import code.modules.Node;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    private final Evaluator evaluator;

    public NodeComparator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public int compare(Node o1, Node o2) {
        return evaluator.apply(o1).compareTo(evaluator.apply(o2));
    }
}
