package code.queues;

import code.evaluators.AStarEvaluator;
import code.evaluators.Evaluator;
import code.evaluators.NodeComparator;
import code.heuristics.FirstFunction;
import code.heuristics.HeuristicFunction;
import code.heuristics.SecondFunction;

import java.util.PriorityQueue;

public class AStar extends UniformCost {

    public AStar(int i) {
        HeuristicFunction function =
                (i == 1) ? new FirstFunction() : new SecondFunction();
        Evaluator evaluator = new AStarEvaluator(function);
        super.setQueue(new PriorityQueue<>(new NodeComparator(evaluator)));
    }
}
