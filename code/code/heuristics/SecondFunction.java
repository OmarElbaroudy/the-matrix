package code.heuristics;

import code.modules.Cost;
import code.modules.Node;
import code.services.GraphHandler;

public class SecondFunction extends HeuristicFunction {

    @Override
    public Cost apply(Node node) {
        GraphHandler graphHandler = new GraphHandler(node.getState());
        int kills = graphHandler.getHostageKills2();
        kills += graphHandler.canReachTB() ? 0 : 1;
        int depth = graphHandler.getDepth() + graphHandler.minDistFromMAsToTB();
        return new Cost(depth, kills, 0);
    }
}
