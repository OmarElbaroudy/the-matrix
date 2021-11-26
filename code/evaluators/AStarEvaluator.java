package evaluators;

import heuristics.HeuristicFunction;
import modules.Node;

public class AStarEvaluator extends Evaluator {

    public AStarEvaluator(HeuristicFunction function){
        super(function);
    }

    @Override
    public int apply(Node node) {
        return node.getPathCost().getDrops() + function.apply(node);
    }
}
