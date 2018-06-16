package hr.fer.zermis.avsp.lab4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;
 /**
  * Input point for NodeRank algorithm, in assignment is described to do everything
  * in same package and to give input point as this one.
  * @author Mateo Stjepanovic
  *
  */
public class NodeRank {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		/*
		 * First line from input that contains number of nodes and probability for surfer to follow nodes
		 */
		String firstLine = input.nextLine();
		int n = Integer.parseInt(firstLine.split(" ")[0]);
		double beta = Double.parseDouble(firstLine.split(" ")[1]);
		
		ArrayList<NodeConnections> connections = new ArrayList<>();
		ArrayList<Query> queries = new ArrayList<>();
		/**
		 * Can do this because it is said in assigment that we will always have n lines representing connections
		 */
		for(int i = 0; i < n; ++i) {
			connections.add(new NodeConnections(i,input.nextLine()));
		}
		
		/*
		 * Number of queries that software needs to give answer to
		 */
		int Q = Integer.parseInt(input.nextLine());
		
		for(int i = 0; i < Q; ++i) {
			queries.add(new Query(input.nextLine()));
		}
		
		
		NodeRankAlg nra = new NodeRankAlg(beta, n);
		for (Query query : queries) {
			//System.out.println(BigDecimal.valueOf(rank.get(query.getIndex())).setScale(10,RoundingMode.HALF_UP));
			System.out.format("%.10f\n", nra.CalculateRankInT(query.getT(), connections).get(query.getIndex()));
			
		}
	}

}
