package hr.fer.zermis.avsp.lab4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	boolean visited[];
	int N;
	int numberOfSteps;
	HashMap<Node,Integer> reachable;
	
	public BFS( int N, int numberOfSteps){
		this.N = N;
		this.numberOfSteps = numberOfSteps;
	}
	
	public HashMap<Node,Integer> FindReachable(Node node) {
		reachable = new HashMap<>();
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(node);
		reachable.put(node, 0);
		
		for(int i = 0; i < numberOfSteps; ++i) {
			Node current = queue.remove();
			if(current.getEdgeNodes().isEmpty()) {
				return reachable;
			}else {
				queue.addAll(current.getEdgeNodes());
				
			}
		}
		return reachable;
	}
	
}
