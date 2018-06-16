package hr.fer.zemris.avsp.lab3;

public class Bucket implements Comparable<Bucket> {
	private int timestamp;
	private int onesCount;
	
	public Bucket(int timestamp, int onesCount) {
		this.timestamp = timestamp;
		this.onesCount = onesCount;
	}
	
	public Bucket() {
		this.timestamp = 0;
		this.onesCount = 0;
	}
	
	/**
	 * @return the timestamp
	 */
	public int getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the onesCount
	 */
	public int getOnesCount() {
		return onesCount;
	}

	/**
	 * @param onesCount the onesCount to set
	 */
	public void setOnesCount(int onesCount) {
		this.onesCount = onesCount;
	}

	@Override
	public int compareTo(Bucket o) {
		// TODO Auto-generated method stub
		if(this.onesCount != o.getOnesCount()) {
			return Integer.compare(this.onesCount, o.getOnesCount());
		}else {
			return Integer.compare(0-this.timestamp, 0-o.timestamp);
		}
		
	}
	
	
}
