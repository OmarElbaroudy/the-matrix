package heuristics;

import modules.Cost;
import modules.Node;

public class FirstFunction extends HeuristicFunction {

    @Override
    public Cost apply(Node node) {
        return new Cost();
    }
}
