package queues;

import evaluators.AStarEvaluator;
import evaluators.Evaluator;
import evaluators.NodeComparator;
import heuristics.FirstFunction;
import heuristics.HeuristicFunction;
import heuristics.SecondFunction;

import java.util.PriorityQueue;

public class AStar extends UniformCost {

    public AStar(int i) {
        HeuristicFunction function =
                (i == 1) ? new FirstFunction() : new SecondFunction();
        Evaluator evaluator = new AStarEvaluator(function);
        super.setQueue(new PriorityQueue<>(new NodeComparator(evaluator)));
    }
}
