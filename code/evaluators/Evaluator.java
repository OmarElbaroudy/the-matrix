package evaluators;

import heuristics.HeuristicFunction;
import modules.Cost;
import modules.Node;

public abstract class Evaluator {
    protected HeuristicFunction function;

    public Evaluator(HeuristicFunction function) {
        this.function = function;
    }

    public abstract Cost apply(Node node);
}
