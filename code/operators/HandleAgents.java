package operators;

import modules.Cost;
import modules.Host;
import modules.Node;
import modules.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandleAgents extends Operator {

    public HandleAgents(Operation operation, Cost cost) {
        super(operation, cost);
    }

    @Override
    public List<Node> expand(Node node) {
        if (getNextState(node.getState()) == null) {
            return new ArrayList<>();
        }

        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{-1, 1, 0, 0};

        List<Node> ret = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ret.addAll(kill(node, getNextState(node.getState()), dx[i], dy[i]));
        }
        return ret;
    }

    public List<Node> kill(Node parent, State cur, int dx, int dy) {
        if (!isValid(cur, dx, dy)) {
            return new ArrayList<>();
        }

        if(!cur.kill()){
            return new ArrayList<>();
        }

        cur.clearPos(cur.getX() + dx, cur.getY() + dy);
        Operator operator = new HandleAgents(Operation.KILL, new Cost().kill());
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
            return type == Host.AGENT || type == Host.MUTATED_AGENT;
        }

        return false;
    }
}
