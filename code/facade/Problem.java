package facade;

import modules.Node;
import modules.State;
import operators.Operator;

import java.util.ArrayList;
import java.util.List;


public abstract class Problem {

    protected State initialState;
    protected List<Operator> operators;

    public Problem(State initialState) {
        this.initialState = initialState;
    }

    public State getInitialState() {
        return initialState;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public abstract boolean testGoal(State state);

    public List<Node> expand(Node node, List<Operator> operators) {
        List<Node> expanded = new ArrayList<>();

        for (Operator operator : operators) {
            expanded.addAll(operator.expand(node));
        }

        return expanded;
    }
}
