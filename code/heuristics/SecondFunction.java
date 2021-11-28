package heuristics;

import modules.Cost;
import modules.Node;
import services.GraphHandler;

public class SecondFunction extends HeuristicFunction {

    @Override
    public Cost apply(Node node) {
        GraphHandler graphHandler = new GraphHandler(node.getState());
        int potentialDrops = graphHandler.getNumOfPotentialDrops();
        //if (potentialDrops != 0) {
            return new Cost(0, 0, potentialDrops, node.getState());
        //}

        //return new Cost(graphHandler.getDepth(), 0, 0, node.getState());
    }
}
