package code.evaluators;

import code.heuristics.HeuristicFunction;
import code.modules.Cost;
import code.modules.Node;

public abstract class Evaluator {
    protected HeuristicFunction function;

    public Evaluator(HeuristicFunction function) {
        this.function = function;
    }

    public abstract Cost apply(Node node);
}
