package containers;

import java.util.*;
import modules.Node;
import modules.State;

public class UniformCost extends Container {   
	PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>();



	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return nodePriorityQueue.isEmpty();
	}




	@Override
	public void add(List<Node> nodes) {
		// TODO Auto-generated method stub
		nodes.forEach((node) -> {
            if(!(cost.containsValue(node.getState()))){
            	cost.put(node.getState(), node.getPathCost());
            	nodePriorityQueue.add(node);
            }
        });
		
	}




	@Override
	public Node poll() {
		// TODO Auto-generated method stub
		return nodePriorityQueue.poll();
	}
	
//	public static void main(String[] args) {
//		UniformCost uc = new UniformCost();
//		Node node1 = new Node();
//		Node node2 = new Node();
//		Node node3 = new Node();
//		Node node4 = new Node();
//		node1.setPathCost(2);
//		node2.setPathCost(6);
//		node3.setPathCost(1);
//		node4.setPathCost(0);
//		uc.nodePriorityQueue.add(node1);
//		uc.nodePriorityQueue.add(node2);
//		uc.nodePriorityQueue.add(node3);
//		uc.nodePriorityQueue.add(node4);
//		while (!uc.nodePriorityQueue.isEmpty()) {
//            System.out.println(uc.nodePriorityQueue.remove().getPathCost());
//        }
//			 
//	 }

}
