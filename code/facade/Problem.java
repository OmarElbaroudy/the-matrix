package facade;

import modules.State;
import operators.Operator;

import java.util.List;


public abstract class Problem {

    protected List<Operator> operators;

    public abstract State getInitialState();

    public abstract boolean testGoal(State state);

    public abstract List<Operator> getOperators();


}
