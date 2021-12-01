package code.heuristics;

import code.modules.Cost;
import code.modules.Node;

public abstract class HeuristicFunction {

    public abstract Cost apply(Node node);
}
