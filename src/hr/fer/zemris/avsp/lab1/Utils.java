package hr.fer.zemris.avsp.lab1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class with static functions that calculate all kind of stuff
 * @author Mateo Stjepanovic
 *
 */
public class Utils {
	
	/**
	 * Calculation bit array form hex by going to all char in hex array, changing it to decimal number, than to binaryString.
	 * If there is less than 4 bits just fill it up with zeros at beggining.
	 * @param hexArray
	 * @param values
	 * @return
	 */
	public static int[] calculateBitFromHex(String hexArray, int[] values) {
		int index = 0;
		
		for(int i = 0; i < hexArray.length(); ++i) {
			int decimal = Integer.parseInt(hexArray.charAt(i)+"",16);
			values = fillWithBit(index, 4 - Integer.toBinaryString(decimal).length(), Integer.toBinaryString(decimal),values);
			index+=4;
		}
		return values;
	}
	
	/**
	 * Calculation hex from bit array. Go through bit array and take for bit at one step.
	 * Append it to string and parse it to integer.
	 * After that parse it to hex character and append it to output
	 * 
	 * @param bitArray
	 * @return
	 */
	public static  String calculateHexFromBit(long[] bitArray) {
		String output="";
		for(int i = 0; i < bitArray.length;i+=4) {
			String binaryString = "";
			for(int j = i; j < i+4;++j) {
				binaryString+=Integer.toString((int)bitArray[j]);
			}
			int decimal = Integer.parseInt(binaryString,2);
			output+= Integer.toString(decimal,16);
		}
		return output;
	}
	
	/**
	 * Fill values with bits from bit string. If there is leading zeros first fill it with them, than add rest of bits.
	 * @param index
	 * @param leadingZeros
	 * @param bitString
	 * @param values
	 * @return
	 */
	private static  int[] fillWithBit(int index, int leadingZeros,String bitString,int[] values) {
		for(int i = 0; i < leadingZeros;++i) {
			values[index+i] = 0;
		}
		for(int i = 0; i < bitString.length();++i) {
			values[index+i+leadingZeros] =  Character.getNumericValue(bitString.charAt(i));			
		}
		return values;
		
	}
	/**
	 * Go through every bit and count different ones. Here we don't have any security checks because it is written in task
	 * that every hash will be 128 bit long, so we know that they are same length.
	 * @param first
	 * @param second
	 * @return
	 */
	public static int calculateHammingDistance(long[] first, long[] second) {
		int distance = 0;
		for(int i = 0; i < first.length;++i) {
			if(first[i] != second[i]) {
				distance++;
			}
		}
		return distance;
	}
	
	/**
	 * Open writer for output and and go through all queries and check them, returning values store to file.
	 * @param queries
	 * @param hashes
	 * @param output
	 * @throws IOException
	 */
	public static void callQueries(ArrayList<String> queries, ArrayList<long[]> hashes, String output) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		
		for (String querie : queries) {
			int I = Integer.parseInt(querie.split(" ")[0]);
			int K = Integer.parseInt(querie.split(" ")[1]);
			
			writer.write(String.valueOf(checkQuerie(hashes,I,K)+"\n"));
		}
		writer.close();
	}
	/**
	 * For I-th hash in hashes calculate Hamming distance between it and every other hash.
	 * If distance is LE of K, sum to counter.
	 * @param hashes
	 * @param I
	 * @param K
	 * @return
	 */
	private static int checkQuerie(ArrayList<long[]> hashes, int I, int K) {
		int num=0;
		long[] hash = hashes.get(I);
		for(int i = 0; i < hashes.size();++i) {
			if(i != I) {
				if(calculateHammingDistance(hash, hashes.get(i)) <= K) {
					num++;
				}
			}
		}
		
		return num;
	}
	
	
}