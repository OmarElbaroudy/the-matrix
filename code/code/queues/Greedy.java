package code.queues;

import code.evaluators.Evaluator;
import code.evaluators.GreedyEvaluator;
import code.evaluators.NodeComparator;
import code.heuristics.FirstFunction;
import code.heuristics.HeuristicFunction;
import code.heuristics.SecondFunction;
import code.modules.Node;

import java.util.PriorityQueue;

public class Greedy extends UniformCost {
    PriorityQueue<Node> pq;

    public Greedy(int i) {
        HeuristicFunction function =
                (i == 1) ? new FirstFunction() : new SecondFunction();
        Evaluator evaluator = new GreedyEvaluator(function);
        super.setQueue(pq = new PriorityQueue<>(new NodeComparator(evaluator)));
    }
}
