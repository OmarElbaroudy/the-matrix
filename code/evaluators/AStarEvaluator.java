package evaluators;

import heuristics.HeuristicFunction;
import modules.Cost;
import modules.Node;

public class AStarEvaluator extends Evaluator {

    public AStarEvaluator(HeuristicFunction function) {
        super(function);
    }

    @Override
    public Cost apply(Node node) {
        return new Cost(node.getPathCost(), function.apply(node), node.getState());
    }
}
