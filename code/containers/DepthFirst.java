package containers;

import java.util.*;
import modules.Node;
import modules.State;

public class DepthFirst extends Container {
	private Stack<Node> s = new Stack<Node>();

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return s.empty();
	}

	@Override
	public void add(List<Node> nodes) {
		// TODO Auto-generated method stub
		nodes.forEach((node) -> {
            if(!(cost.containsValue(node.getState()))){
            	cost.put(node.getState(), node.getPathCost());
            	s.push(node);
            }
        });	
		
	}

	@Override
	public Node poll() {
		// TODO Auto-generated method stub
		return s.pop();
	}
}
