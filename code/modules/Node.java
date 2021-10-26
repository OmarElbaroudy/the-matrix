package modules;

import operators.Operator;

public class Node implements Comparable<Node> {
    private State state;
	private Node parent;
    private Operator operator;
    private int depth, pathCost;
    
    public int getPathCost() {
		return pathCost;
	}
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	@Override
	public int compareTo(Node node) {
		// TODO Auto-generated method stub
		return this.getPathCost() - node.getPathCost();
	}

}
