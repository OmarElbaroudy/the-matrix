package modules;

import operators.Cost;
import operators.Operator;

public class Node implements Comparable<Node> {
    private final State state;
    private final Node parent;
    private final Operator operator;
    private Cost pathCost;

    public Node(State state, Node parent, Operator operator) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;

        if (parent != null) {
            this.pathCost = new Cost(parent.pathCost, operator.getCost());
        }
    }

    @Override
    public int compareTo(Node node) {
        return this.pathCost.compareTo(node.pathCost);
    }

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Operator getOperator() {
        return operator;
    }

    public Cost getPathCost() {
        return pathCost;
    }

}
