package hr.fer.zermis.avsp.lab4;

import java.util.ArrayList;
import java.util.Scanner;

public class ClosestBlackNode {

	public static void main(String[] args) {
Scanner input = new Scanner(System.in);
		
		/*
		 * First line from input that contains number of nodes and probability for surfer to follow nodes
		 */
		String firstLine = input.nextLine();
		int n = Integer.parseInt(firstLine.split(" ")[0]);
		double epsilon = Double.parseDouble(firstLine.split(" ")[1]);
		
		ArrayList<Node> nodes = new ArrayList<>();
		/**
		 * Array of nodes, saying if its black or white node
		 */
		for(int i = 0; i < n; ++i) {
			nodes.add(new Node(i,Integer.parseInt(input.nextLine())==1));
		}
		
		/*
		 * Number of queries that software needs to give answer to
		 */
		
		for(int i = 0; i < epsilon; ++i) {
			String line = input.nextLine();
			nodes.get(Integer.parseInt(line.split(" ")[0]))
			.addEdgeNode(nodes.get(Integer.parseInt(line.split(" ")[1])));
			nodes.get(Integer.parseInt(line.split(" ")[1]))
			.addEdgeNode(nodes.get(Integer.parseInt(line.split(" ")[0])));
		}

	}

}
