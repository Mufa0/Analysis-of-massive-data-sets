package hr.fer.zermis.avsp.lab4;
/**
 * Class representing query to software
 * @author Mateo Stjepanovic
 *
 */
public class Query {
	private int index;
	private int t;
	/**
	 * Constructor splitting line containing index of node for query and number of iteration
	 * @param line
	 */
	public Query(String line) {
		this.index = Integer.parseInt(line.split(" ")[0]);
		this.t = Integer.parseInt(line.split(" ")[1]);
	}
	
	/**
	 * Constructor putting index and t to it's variables
	 * @param index
	 * @param t
	 */
	public Query(int index, int t) {
		this.index = index;
		this.t = t;
	}
	
	/**
	 * Get index of node to query it
	 * @return
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Get number of iteration on which to have rank
	 * @return
	 */
	public int getT() {
		return t;
	}

}
