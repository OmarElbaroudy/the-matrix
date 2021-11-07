package operators;

import modules.Node;

import java.util.List;

public abstract class Operator {
    protected Operation operation;
    protected Cost cost;

    protected Operator(Operation operation, Cost cost) {
        this.operation = operation;
        this.cost = cost;
    }

    public abstract List<Node> expand(Node node);

    @Override
    public final String toString() {
        return operation.toString();
    }

    public Operation getOperation() {
        return operation;
    }

    public Cost getCost() {
        return cost;
    }
}
