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
	
	public static int[] calculateBitFromHex(String hexArray, int[] values) {
		int index = 0;
		
		for(int i = 0; i < hexArray.length(); ++i) {
			int decimal = Integer.parseInt(hexArray.charAt(i)+"",16);
			values = fillWithBit(index, 4 - Integer.toBinaryString(decimal).length(), Integer.toBinaryString(decimal),values);
			index+=4;
		}
		return values;
	}
	
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
	
	private static  int[] fillWithBit(int index, int leadingZeros,String bitString,int[] values) {
		for(int i = 0; i < leadingZeros;++i) {
			values[index+i] = 0;
		}
		for(int i = 0; i < bitString.length();++i) {
			values[index+i+leadingZeros] =  Character.getNumericValue(bitString.charAt(i));			
		}
		return values;
		
	}
	public static int calculateHammingDistance(long[] first, long[] second) {
		int distance = 0;
		for(int i = 0; i < first.length;++i) {
			if(first[i] != second[i]) {
				distance++;
			}
		}
		return distance;
	}
	
	public static void callQueries(ArrayList<String> queries, ArrayList<long[]> hashes, String output) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		
		for (String querie : queries) {
			int I = Integer.parseInt(querie.split(" ")[0]);
			int K = Integer.parseInt(querie.split(" ")[1]);
			
			writer.write(String.valueOf(checkQuerie(hashes,I,K)+"\n"));
		}
		writer.close();
	}
	
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