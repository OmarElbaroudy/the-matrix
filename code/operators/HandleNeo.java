package operators;

import modules.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandleNeo extends Operator {

    public HandleNeo(Operation operation, Cost cost) {
        super(operation, cost);
    }

    @Override
    public List<Node> expand(Node node) {
        if (getNextState(node.getState()) == null) {
            return new ArrayList<>();
        }

        List<Node> ret = new ArrayList<>();

        ret.addAll(moveUp(node, getNextState(node.getState())));
        ret.addAll(moveDown(node, getNextState(node.getState())));
        ret.addAll(moveLeft(node, getNextState(node.getState())));
        ret.addAll(moveRight(node, getNextState(node.getState())));
        ret.addAll(takePill(node, getNextState(node.getState())));
        ret.addAll(fly(node, getNextState(node.getState())));

        return ret;
    }

    private List<Node> moveUp(Node parent, State cur) {
        if (parent.getOperator() != null &&
                parent.getOperator().getOperation() == Operation.Down) {
            return new ArrayList<>();
        }

        if (!isValid(cur, 0, 1)) {
            return new ArrayList<>();
        }

        cur.move(0, 1);
        Operator operator = new HandleNeo(Operation.Up, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private List<Node> moveDown(Node parent, State cur) {
        if (parent.getOperator() != null &&
                parent.getOperator().getOperation() == Operation.Up) {
            return new ArrayList<>();
        }

        if (!isValid(cur, 0, -1)) {
            return new ArrayList<>();
        }

        cur.move(0, -1);
        Operator operator = new HandleNeo(Operation.Down, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private List<Node> moveRight(Node parent, State cur) {
        if (parent.getOperator() != null &&
                parent.getOperator().getOperation() == Operation.Left) {
            return new ArrayList<>();
        }

        if (!isValid(cur, 1, 0)) {
            return new ArrayList<>();
        }

        cur.move(1, 0);
        Operator operator = new HandleNeo(Operation.Right, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }


    private List<Node> moveLeft(Node parent, State cur) {
        if (parent.getOperator() != null &&
                parent.getOperator().getOperation() == Operation.Right) {
            return new ArrayList<>();
        }

        if (!isValid(cur, -1, 0)) {
            return new ArrayList<>();
        }

        cur.move(-1, 0);
        Operator operator = new HandleNeo(Operation.Left, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private List<Node> fly(Node parent, State cur) {
        Host type = cur.getGrid().getHostAtPos(cur.getX(), cur.getY());

        if (type != Host.Pad) {
            return new ArrayList<>();
        }

        cur.transform(cur.getX(), cur.getY());
        Operator operator = new HandleNeo(Operation.Fly, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private List<Node> takePill(Node parent, State cur) {
        Host type = cur.getGrid().getHostAtPos(cur.getX(), cur.getY());

        if (type != Host.Pill) {
            return new ArrayList<>();
        }

        Grid grid = cur.getGrid();
        int n = grid.getN(), m = grid.getM();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid.heal(i, j);
            }
        }

        cur.healNeo();
        cur.clearPos(cur.getX(), cur.getY());
        Operator operator = new HandleNeo(Operation.TakePill, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private boolean isValid(State state, int dx, int dy) {
        int x = state.getX() + dx;
        int y = state.getY() + dy;
        int n = state.getGrid().getN();
        int m = state.getGrid().getM();

        if (x >= 0 && x < n && y >= 0 && y < m) {
            Host type = state.getGrid().getHostAtPos(x, y);
            return type != Host.Agent && type != Host.MutatedAgent;
        }

        return false;
    }
}
