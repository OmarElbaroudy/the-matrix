package heuristics;

import modules.Cost;
import modules.Node;

public abstract class HeuristicFunction {

    public abstract Cost apply(Node node);
}
