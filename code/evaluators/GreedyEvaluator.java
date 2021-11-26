package evaluators;

import heuristics.HeuristicFunction;
import modules.Node;

public class GreedyEvaluator extends Evaluator {

    public GreedyEvaluator(HeuristicFunction function) {
        super(function);
    }


    @Override
    public int apply(Node node) {
        return function.apply(node);
    }
}
