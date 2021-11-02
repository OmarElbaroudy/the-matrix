package modules;

import operators.Operator;

public class Node {
    private final State state;
    private final Node parent;
    private final Operator operator;
    private int depth, pathCost;

    public Node(State state, Node parent, Operator operator) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;

        if (parent != null) { //TODO check access modifier problem
            this.depth = parent.depth + 1;
            this.pathCost = parent.pathCost + operator.getCost();
        }
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

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }
}
