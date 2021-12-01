package code.services;

import code.Problem;
import code.modules.Node;
import code.modules.State;
import code.operators.Operation;
import code.operators.Operator;
import code.queues.Queue;

import java.util.List;
import java.util.function.BiFunction;

public class GeneralSearch {

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

                return getSolution(node, expandedNodes, initialState);
            }

            queue.add(qingFunc.apply(node, operators));
        }

        return "No Solution";
    }

    private static String getSolution(Node node,
                                      int expandedNodes,
                                      State initialState) {
        sol = new StringBuilder();
        getPlan(node);

        int deaths = VisualsHandler.getDeaths(node, initialState);
        int kills = VisualsHandler.getKills(node);

        sol.replace(sol.length() - 2, sol.length(), ";");
        sol.append(deaths).append("; ").append(kills)
                .append("; ").append(expandedNodes);
//                .append(", ").append(pathDepth);

        return sol.toString();
    }

    private static void getPlan(Node node) {
        if (node.getParent() != null) {
            getPlan(node.getParent());
            Operation op = node.getOperator().getOperation();

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
