package code.evaluators;

import code.heuristics.HeuristicFunction;
import code.modules.Cost;
import code.modules.Node;

public class GreedyEvaluator extends Evaluator {

    public GreedyEvaluator(HeuristicFunction function) {
        super(function);
    }


    @Override
    public Cost apply(Node node) {
        return function.apply(node);
    }
}
