package operators;

import modules.Node;

import java.util.List;

public abstract class Operator {
    protected Operation operation;
    protected int cost;

    public abstract List<Node> expand(Node node);

    protected Operator(Operation operation, int cost){
        this.operation = operation;
        this.cost = cost;
    }

    @Override
    public final String toString() {
        return operation.toString();
    }

    public Operation getOperation() {
        return operation;
    }

    public int getCost() {
        return cost;
    }
}
