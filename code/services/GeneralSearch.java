package services;

import facade.Problem;
import modules.Node;
import modules.State;
import operators.Operation;
import operators.Operator;
import queues.Queue;

import java.util.List;
import java.util.function.BiFunction;

public class GeneralSearch {

    private static int kills, deaths, pathDepth;
    private static StringBuilder sol;

    public static String search(Problem problem,
                                Queue queue,
                                boolean visualize,
                                BiFunction<Node, List<Operator>, List<Node>> qingFunc) {

        State initialState = problem.getInitialState();
        List<Operator> operators = problem.getOperators();
        queue.makeQ(new Node(initialState, null, null));

        int expandedNodes = 0;
        while (!queue.isEmpty()) {
            expandedNodes++;
            Node node = queue.removeFront();
            if (problem.testGoal(node.getState())) {

                if (visualize) {
                    printVisual(node);
                }

                return getSolution(node, expandedNodes);
            }

            queue.add(qingFunc.apply(node, operators));
        }

        return "failed to reach test goal!";
    }

    private static String getSolution(Node node, int expandedNodes) {
        kills = pathDepth = deaths = 0;
        sol = new StringBuilder();

        getPlan(node);
        sol.append(deaths).append(", ").append(kills)
                .append(", ").append(expandedNodes).append(", ").append(pathDepth);

        return sol.toString();
    }

    private static void getPlan(Node node) {
        if (node.getParent() != null) {
            pathDepth++;
            getPlan(node.getParent());

            Operation op = node.getOperator().getOperation();
            kills += (op == Operation.KILL) ? 1 : 0;

            deaths += VisualsHandler.getDeaths(node);
            sol.append(op).append(", ");
        }
    }

    private static void printVisual(Node node) {
        if (node.getParent() != null) {
            printVisual(node.getParent());
            System.out.println(node.getOperator().getOperation());
        }

        System.out.println(node.getState());
    }

}
