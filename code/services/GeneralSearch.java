package services;

import containers.Container;
import facade.Problem;
import modules.Node;
import modules.State;
import operators.Operator;

import java.util.List;
import java.util.function.BiFunction;

public class GeneralSearch {

    public static void search(Problem problem,
                              Container container,
                              BiFunction<Node, List<Operator>, List<Node>> qingFunc) {

        int expandedNodesCnt = 0;
        State initialState = problem.getInitialState();
        List<Operator> operators = problem.getOperators();
        container.init(new Node(initialState, null, null));


        while (!container.isEmpty()) {
            expandedNodesCnt++;
            Node node = container.poll();

            if (problem.testGoal(node.getState())) {
                //print solution
                System.out.println(expandedNodesCnt);
                return;
            }

            container.add(qingFunc.apply(node, operators));
        }
        //failure
    }


}
