package operators;

import modules.Node;

import java.util.List;

public abstract class Operator {
    public Operator operation;

    public abstract List<Node> expand(Node node);

    @Override
    public final String toString() {
        return operation.toString();
    }
}
