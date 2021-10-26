package containers;

import java.util.*;
import modules.Node;
import modules.State;

public class BreadthFirst extends Container {
	private Queue<Node> q = new LinkedList<>();

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return q.isEmpty();
	}

	@Override
	public void add(List<Node> nodes) {
		// TODO Auto-generated method stub
		nodes.forEach((node) -> {
            if(!(cost.containsValue(node.getState()))){
            	cost.put(node.getState(), node.getPathCost());
            	q.add(node);
            }
        });	
	}

	@Override
	public Node poll() {
		// TODO Auto-generated method stub
		return q.poll();
	}
}
