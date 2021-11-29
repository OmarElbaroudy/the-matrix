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
        List<Node> ret = new ArrayList<>();

        ret.addAll(takePill(node, getNextState(node.getState())));
        ret.addAll(moveUp(node, getNextState(node.getState())));
        ret.addAll(moveDown(node, getNextState(node.getState())));
        ret.addAll(moveLeft(node, getNextState(node.getState())));
        ret.addAll(moveRight(node, getNextState(node.getState())));
        ret.addAll(fly(node, getNextState(node.getState())));

        return super.filterInvalidNodes(ret);
    }

    private List<Node> moveUp(Node parent, State cur) {
        if (parent.getOperator() != null &&
                parent.getOperator().getOperation() == Operation.DOWN) {
            return new ArrayList<>();
        }

        if (!isValid(cur, 0, -1)) {
            return new ArrayList<>();
        }

        cur.move(0, -1);
        Operator operator = new HandleNeo(Operation.UP, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private List<Node> moveDown(Node parent, State cur) {
        if (parent.getOperator() != null &&
                parent.getOperator().getOperation() == Operation.UP) {
            return new ArrayList<>();
        }

        if (!isValid(cur, 0, 1)) {
            return new ArrayList<>();
        }

        cur.move(0, 1);
        Operator operator = new HandleNeo(Operation.DOWN, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private List<Node> moveRight(Node parent, State cur) {
        if (parent.getOperator() != null &&
                parent.getOperator().getOperation() == Operation.LEFT) {
            return new ArrayList<>();
        }

        if (!isValid(cur, 1, 0)) {
            return new ArrayList<>();
        }

        cur.move(1, 0);
        Operator operator = new HandleNeo(Operation.RIGHT, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }


    private List<Node> moveLeft(Node parent, State cur) {
        if (parent.getOperator() != null &&
                parent.getOperator().getOperation() == Operation.RIGHT) {
            return new ArrayList<>();
        }

        if (!isValid(cur, -1, 0)) {
            return new ArrayList<>();
        }

        cur.move(-1, 0);
        Operator operator = new HandleNeo(Operation.LEFT, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private List<Node> fly(Node parent, State cur) {
        Host type = cur.getGrid().getHostAtPos(cur.getX(), cur.getY());

        if (parent.getOperator() != null &&
                parent.getOperator().getOperation() == Operation.FLY) {
            return new ArrayList<>();
        }

        if (type != Host.PAD) {
            return new ArrayList<>();
        }

        cur.transform(cur.getX(), cur.getY());
        Operator operator = new HandleNeo(Operation.FLY, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private List<Node> takePill(Node parent, State cur) {
        Host type = cur.getGrid().getHostAtPos(cur.getX(), cur.getY());

        if (type != Host.PILL) {
            return new ArrayList<>();
        }

        Grid grid = cur.getGrid();
        int n = grid.getN(), m = grid.getM();
        Grid parentGrid = parent.getState().getGrid();

        byte cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cnt += grid.heal(i, j, parentGrid.getDamageAtPos(i,j)) ? 1 : 0;
            }
        }

        cur.healNeo();
        cnt += cur.healCarriedHostages(parent.getState().getCarriedDamages());

        cur.clearPos(cur.getX(), cur.getY());
        cur.updateAliveHostages(cnt);
        Operator operator = new HandleNeo(Operation.TAKE_PILL, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private boolean isValid(State state, int dx, int dy) {
        int x = state.getX() + dy;
        int y = state.getY() + dx;
        int n = state.getGrid().getN();
        int m = state.getGrid().getM();

        if (x >= 0 && x < n && y >= 0 && y < m) {
            Host type = state.getGrid().getHostAtPos(x, y);
            return type != Host.AGENT && type != Host.MUTATED_AGENT;
        }

        return false;
    }
}
