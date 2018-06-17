package hr.fer.zemris.avsp.lab5;

public class Pair implements Comparable<Pair> {
	private int index;
	private double value;
	
	public Pair(int index, double value) {
		this.index = index;
		this.value = value;
	}
	
	
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}


	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}


	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}


	@Override
	public int compareTo(Pair arg0) {
		// TODO Auto-generated method stub
		return Double.compare(this.value, arg0.value);
	}

}
