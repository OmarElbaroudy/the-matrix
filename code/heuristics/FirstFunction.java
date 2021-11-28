package heuristics;

import modules.Cost;
import modules.Node;
import services.GraphHandler;

public class FirstFunction extends HeuristicFunction {

    @Override
    public Cost apply(Node node) {
        GraphHandler graphHandler = new GraphHandler(node.getState());
        int kills = graphHandler.getHostageKills();
        kills += graphHandler.canReachTB() ? 0 : 1;
        //if(kills != 0){
            return new Cost(0, kills, 0, node.getState());
        //}

        //return new Cost(graphHandler.getDepth(), 0, 0, node.getState());
    }
}
