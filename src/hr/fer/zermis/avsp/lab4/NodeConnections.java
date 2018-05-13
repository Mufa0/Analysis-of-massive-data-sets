package hr.fer.zermis.avsp.lab4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Class representing all nodes that are connected to specific node
 * @author Mateo Stjepanovic
 *
 */
public class NodeConnections {
	
	private int node;
	private ArrayList<Integer> connections = new ArrayList<>();
	//Number of outgoing connections
	private int d;
	private double invertedD;
	/**
	 * Constructor cloning input connections to arraylist of connections and having number of specific node
	 * @param node - node of this specific node
	 * @param connections - array list of all nodes connected to this node
	 */
	public NodeConnections(int node, ArrayList<Integer> connections) {
		
		this.node = node;
		this.connections = (ArrayList<Integer>) connections.clone();
		this.d = connections.size();
		this.invertedD = 1/d;
	}
	
	/**
	 * Constructor cloning input connections to array list of connections and having number of specific node
	 * @param node - node of this specific node
	 * @param connections - string where delimiter between each node is \space
	 */
	public NodeConnections(int node, String connections) {
		this.node = node;
		addConnections(connections);
	}
	/**
	 * Getting node index
	 * @return
	 */
	public int getNode() {
		return node;
	}
	/**
	 * Getting number of outgoing connections
	 * @return
	 */
	public int getD() {
		return d;
	}
	public double getInvertedD() {
		return invertedD;
	}
	
	/**
	 * Get all connections of one node
	 * @return
	 */
	public ArrayList<Integer> getConnections() {
		return connections;
	}
	
	/**
	 * Get node that is connected to this one at specific index
	 * @param index
	 * @return
	 */
	public Integer getConnection(int index) {
		return connections.get(index);
	}
	
	/**
	 * Add one node to connections list
	 * @param node - node that we want to add
	 */
	public void addConnection(Integer node) {
		
		connections.add(node);
		this.d++;
	}
	
	/**
	 * Adding array of connections to list of node connections
	 * @param connections
	 */
	public void addConnections(ArrayList<Integer> connections) {
		this.connections.addAll(connections);
		this.d = this.connections.size();
		this.invertedD = 1/d;
	}
	
	/**
	 * Add string representing connections with \space as delimiter
	 * @param connections
	 */
	public void addConnections(String connections) {
		for (String connectingNode : connections.split(" ")) {
			addConnection(Integer.parseInt(connectingNode));
		}
		this.invertedD = 1/d;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connections == null) ? 0 : connections.hashCode());
		result = prime * result + node;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeConnections other = (NodeConnections) obj;
		if (connections == null) {
			if (other.connections != null)
				return false;
		} else if (!connections.equals(other.connections))
			return false;
		if (node != other.node)
			return false;
		return true;
	}
	
	
	
	
}