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
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{-1, 1, 0, 0};

        State nxtState = getNextState(node.getState());

        int killed = 0;
        for (int i = 0; i < 4; i++) {
            killed += kill(nxtState, node.getState(), dx[i], dy[i]) ? 1 : 0;
        }

        if (killed == 0 || !nxtState.kill()) {
            return new ArrayList<>();
        }

        Cost cost = new Cost().kill(killed);
//        cost.getDeaths(nxtState);
        Operator operator = new HandleAgents(Operation.KILL, cost);
        Node nxtNode = new Node(nxtState, node, operator);
        return super.filterInvalidNodes(Collections.singletonList(nxtNode));
    }

    public boolean kill(State cur, State parent, int dx, int dy) {
        if (!isValid(cur, parent, dx, dy)) {
            return false;
        }

        cur.clearPos(cur.getX() + dx, cur.getY() + dy);
        return true;
    }

    private boolean isValid(State state, State parent, int dx, int dy) {
        int x = state.getX() + dx;
        int y = state.getY() + dy;
        int n = state.getGrid().getN();
        int m = state.getGrid().getM();

        if (x >= 0 && x < n && y >= 0 && y < m) {
            Host type = state.getGrid().getHostAtPos(x, y);
            Host parentType = parent.getGrid().getHostAtPos(x, y);
            return type == Host.AGENT ||
                    (type == Host.MUTATED_AGENT &&
                            parentType != Host.HOSTAGE);
        }

        return false;
    }
}
