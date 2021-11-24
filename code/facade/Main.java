package facade;

import modules.Node;
import operators.Operator;
import queues.*;
import services.GeneralSearch;
import services.GridHandler;

import java.util.List;
import java.util.function.BiFunction;

public class Main {

    public static String solve(String grid, String strategy, boolean visualize) {
        Problem matrix = new Matrix(GridHandler.toState(grid));
        BiFunction<Node, List<Operator>, List<Node>> qingFunc = matrix::expand;

        return GeneralSearch.search(
                matrix, getQueue(strategy), visualize, qingFunc);
    }

    public static String genGrid() {
        return GridHandler.genGrid();
    }

    public static void main(String[] args) {
        String grid = genGrid();
        String answer = solve(grid, "DF", true);
        System.out.println(answer);
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
