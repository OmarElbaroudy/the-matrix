package operators;

import modules.Node;

import java.util.List;

public class HandleHostages extends Operator{

    public HandleHostages(Operation operation, int cost){
        super(operation, cost);
    }

    @Override
    public List<Node> expand(Node node) {
<<<<<<< Updated upstream
        return null;
=======
        List<Node> ret = new ArrayList<>();

        ret.addAll(carry(node, getNextState(node.getState())));
        ret.addAll(drop(node, getNextState(node.getState())));

        return super.filterInvalidNodes(ret);
    }

    private List<Node> carry(Node parent, State cur) {
        if (!canCarry(cur)) {
            return new ArrayList<>();
        }

        cur.carry(cur.getX(), cur.getY());
        Operator operator = new HandleHostages(Operation.CARRY, new Cost());
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private List<Node> drop(Node parent, State cur) {
        if (!canDrop(cur)) {
            return new ArrayList<>();
        }

        Cost c = new Cost().drop(cur.drop());
        c.getDeaths(cur);
        Operator operator = new HandleHostages(Operation.DROP, c);
        Node node = new Node(cur, parent, operator);
        return Collections.singletonList(node);
    }

    private boolean canCarry(State state) {
        Host type = state.getGrid().getHostAtPos(state.getX(), state.getY());
        return type == Host.HOSTAGE && state.getRemCarry() > 0;
    }

    private boolean canDrop(State state) {
        Host type = state.getGrid().getHostAtPos(state.getX(), state.getY());
        return type == Host.TELEPHONE && !state.getCarriedDamages().isEmpty();
>>>>>>> Stashed changes
    }
}
