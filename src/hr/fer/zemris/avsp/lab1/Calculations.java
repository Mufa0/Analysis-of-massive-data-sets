package hr.fer.zemris.avsp.lab1;
/**
 * Class with static functions that calculate all kind of stuff
 * @author Mateo Stjepanovic
 *
 */
public class Calculations {
	
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
}
