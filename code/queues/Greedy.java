package queues;

import evaluators.Evaluator;
import evaluators.GreedyEvaluator;
import evaluators.NodeComparator;
import heuristics.FirstFunction;
import heuristics.HeuristicFunction;
import heuristics.SecondFunction;
import modules.Node;

import java.util.PriorityQueue;

public class Greedy extends UniformCost {
    PriorityQueue<Node> pq;

    public Greedy(int i) {
        HeuristicFunction function =
                (i == 1) ? new FirstFunction() : new FirstFunction();
        Evaluator evaluator = new GreedyEvaluator(function);
        super.setQueue(pq = new PriorityQueue<>(new NodeComparator(evaluator)));
    }
}
