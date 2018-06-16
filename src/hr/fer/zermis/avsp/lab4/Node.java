package hr.fer.zermis.avsp.lab4;

import java.util.ArrayList;

public class Node {
	private int id;
	private boolean black;
	private ArrayList<Node> edgeNodes;
	/**
	 * Constructor for node saying if it is black or not
	 * @param id
	 * @param black
	 */
	public Node(int id, boolean black) {
		this.id = id;
		this.black = black;
		edgeNodes = new ArrayList<>();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the black
	 */
	public boolean isBlack() {
		return black;
	}
	/**
	 * @return the edgeNodes
	 */
	public ArrayList<Node> getEdgeNodes() {
		return edgeNodes;
	}
	/**
	 * @param edgeNodes the edgeNodes to set
	 */
	public void setEdgeNodes(ArrayList<Node> edgeNodes) {
		this.edgeNodes = edgeNodes;
	}
	
	public void addEdgeNode(Node node) {
		this.edgeNodes.add(node);
	}
	
}
