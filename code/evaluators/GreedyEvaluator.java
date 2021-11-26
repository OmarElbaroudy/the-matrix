package evaluators;

import heuristics.HeuristicFunction;
import modules.Cost;
import modules.Node;

public class GreedyEvaluator extends Evaluator {

    public GreedyEvaluator(HeuristicFunction function) {
        super(function);
    }


    @Override
    public Cost apply(Node node) {
        return function.apply(node);
    }
}
