package heuristics;

import modules.Cost;
import modules.Node;
import services.GraphHandler;

public class FirstFunction extends HeuristicFunction {

    @Override
    public Cost apply(Node node) {
        GraphHandler graphHandler = new GraphHandler(node.getState());
        int kills = graphHandler.getHostageKills();
        //kills += graphHandler.canReachTB() ? 0 : 1;
        return new Cost(graphHandler.getDepth(), kills, 0);
    }
}
