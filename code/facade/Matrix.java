package facade;

import containers.Container;
import modules.Node;
import modules.State;
import operators.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Matrix extends Problem {
    public static void solve(String grid, String strategy, boolean visualize) {
        BiFunction<Node, List<Operator>, List<Node>> qingFunc = Matrix::expand;
        //GeneralSearch.search(new Matrix(), new BreadthFirst(), qingFunc);
    }

    private static List<Node> expand(Node node, List<Operator> operators) {
        List<Node> expanded = new ArrayList<>();
        for (Operator operator : operators) {
            expanded.addAll(operator.expand(node));
        }
        return expanded;
    }

    private static Container getQueue(String strategy) {
        
    }

    public String genGrid() {
        return null;
    }

    @Override
    public State getInitialState() {
        //calls genGrid and generates initial state from string
        //neo's initial position will also be added to the state
        return null;
    }

    @Override
    public boolean testGoal(State state) {
        return false;
    }

    @Override
    public List<Operator> getOperators() {
        return null;
    }
}
