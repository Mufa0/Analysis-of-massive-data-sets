/**
 * 
 */
package hr.fer.zemris.avsp.lab1;

/**
 * @author Mateo Stjepanovic
 *Class that represents array of bits, so we do not need to work just with standard array of values
 */
public class BitArray {

	/**
	 * 
	 */
	private int[] values;
	
	public BitArray(int size) {
		values = new int[size];
		// TODO Auto-generated constructor stub
	}

	public int[] getValues() {
		return values;
	}
	public int getValueAtIndex(int index) {
		if(values.length < index) {
			System.err.println("Index too big");
			return -1;
		}
		return values[index];
	}
	public void setValueAtIndex(int index,int value) {
		if(values.length < index) {
			System.err.println("Index too big");
			return;
		}
		if(value < 0 || value > 1) {
			System.err.println("Value must be 0 or 1");
			return;
		}
		values[index] = value;	
	}
	/**
	 * Calculating bits from hex array
	 * @param hexArray
	 */
	public void CalculateBits(String  hexArray) {
		
		Calculations.calculateBitFromHex(hexArray, values);
	}
	

}
