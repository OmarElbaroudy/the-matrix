package operators;

import modules.Node;

import java.util.List;

public class HandleHostages extends Operator{

    public HandleHostages(Operation operation, int cost){
        super(operation, cost);
    }

    @Override
    public List<Node> expand(Node node) {
        return null;
    }
}
