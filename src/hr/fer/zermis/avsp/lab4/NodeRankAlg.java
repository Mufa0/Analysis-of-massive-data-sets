package hr.fer.zermis.avsp.lab4;



import java.util.ArrayList;

public class NodeRankAlg {
	
	
	ArrayList<Double> rank = new ArrayList<>();
	
	double beta;
	int N;
	
	ArrayList<ArrayList<Double>> rankSteps = new ArrayList<>();
	/**
	 * Constructor that creates r0 for calculating ranking of nodes
	 * @param beta
	 * @param N
	 */
	public NodeRankAlg(double beta , int N) {
		this.beta = beta;
		this.N=N;
		double invertedN = 1/N;
		for(int i = 0; i < N; ++i) {
			rank.add((1-beta)/(double)N);
		}
		rankSteps.add(rank);
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Double> CalculateRankInT(int T, ArrayList<NodeConnections> connections){	
		
		if(rankSteps.size() -1 >= T) {
				return rankSteps.get(T);
		}else {
			ArrayList<Double> rankT = (ArrayList<Double>) rankSteps.get(rankSteps.size()-1).clone();
			for(int i = rankSteps.size(); i <= T; ++i) {
				rankT = (ArrayList<Double>) CalculateRank(rankT,connections).clone();
				rankSteps.add(rankT);
			}
				
			return rankT;
		}
	
	}
	
	private ArrayList<Double> CalculateRank(ArrayList<Double> rankT, ArrayList<NodeConnections> connections){
		
		@SuppressWarnings("unchecked")
		ArrayList<Double> rankT1 = (ArrayList<Double>) rankSteps.get(0).clone();
		for (NodeConnections node : connections) {
			
			for(int j = 0; j < node.getD(); ++j) {
				rankT1.set(node.getConnection(j)
						, rankT1.get(node.getConnection(j))+beta*(Double)rankT.get(node.getNode())/(double)node.getD());
			}
		}
		return rankT1;
	}


	/**
	 * @return the rank
	 */
	public ArrayList<Double> getRank() {
		return rank;
	}
	
	public Double getRankAtIndex(int index) {
		return rank.get(index);
	}
}
