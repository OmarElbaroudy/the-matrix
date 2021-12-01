package facade;

import modules.Grid;
import modules.Host;
import modules.Node;
import modules.State;
import operators.HandleAgents;
import operators.HandleHostages;
import operators.HandleNeo;
import operators.Operator;
import queues.*;
import services.GeneralSearch;
import services.GridHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Matrix extends Problem {

    public Matrix(State initialState) {
        super(initialState);
        initOperators();
    }

    @Override
    public boolean testGoal(State state) {
        Grid grid = state.getGrid();
        int n = grid.getN(), m = grid.getM();

        if (!state.getCarriedDamages().isEmpty()) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Host host = grid.getHostAtPos(i, j);
                if (host == Host.MUTATED_AGENT
                        || host == Host.HOSTAGE) {
                    return false;
                }
            }
        }

        int x = state.getX(), y = state.getY();
        return state.getGrid().getHostAtPos(x,y) == Host.TELEPHONE;
    }

    private void initOperators() {
        this.operators = new ArrayList<>();
        operators.add(new HandleNeo(null, null));
        operators.add(new HandleAgents(null, null));
        operators.add(new HandleHostages(null, null));
    }

    public static String solve(String grid, String strategy, boolean visualize) {
        Problem matrix = new Matrix(GridHandler.toState(grid));
        BiFunction<Node, List<Operator>, List<Node>> qingFunc = matrix::expand;

        return GeneralSearch.search(
                matrix, getQueue(strategy), visualize, qingFunc);
    }

    public static String genGrid() {
        return GridHandler.genGrid();
    }

    private static Queue getQueue(String strategy) {
        switch (strategy) {
            case "BF":
                return new BreadthFirst();
            case "DF":
                return new DepthFirst();
            case "ID":
                return new IterativeDeepening();
            case "UC":
                return new UniformCost();
            default:
                int i = strategy.charAt(strategy.length() - 1) - '0';
                if (strategy.charAt(0) == 'G') return new Greedy(i);
                return new AStar(i);
        }
    }

}
