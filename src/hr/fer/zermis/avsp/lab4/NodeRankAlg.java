package hr.fer.zermis.avsp.lab4;



import java.util.ArrayList;

public class NodeRankAlg {
	
	
	ArrayList<Double> rank = new ArrayList<>();
	ArrayList<Double> startRank = new ArrayList<>();
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
		for(int i = 0; i < N; ++i) {
			rank.add((1.0)/N);
			startRank.add((1.0-beta)/N);
		}
		rankSteps.add(rank);
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Double> CalculateRankInT(int T, ArrayList<NodeConnections> connections){	
		
		if(rankSteps.size() -1 >= T) {
				return rankSteps.get(T);
		}else {
			ArrayList<Double> rankT = new ArrayList<Double>();
			rankT.addAll(rankSteps.get(rankSteps.size()-1));
			
			for(int i = rankSteps.size(); i <= T; ++i) {
				ArrayList<Double> pom = new ArrayList<>();			
				pom.addAll(CalculateRank(rankT,connections));
				rankT.clear();
				rankT.addAll(pom);
				rankSteps.add(rankT);
			}		
			return rankT;
		}
	
	}
	
	private ArrayList<Double> CalculateRank(ArrayList<Double> rankT, ArrayList<NodeConnections> connections){
		
		@SuppressWarnings("unchecked")
		ArrayList<Double> rankT1 = new ArrayList<>();
		rankT1.addAll(startRank);
		for (NodeConnections node : connections) {
			
			for(int j = 0; j < node.getD(); ++j) {
				rankT1.set(node.getConnection(j) , rankT1.get(node.getConnection(j))+(beta*rankT.get(node.getNode())/(double)node.getD()));
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
