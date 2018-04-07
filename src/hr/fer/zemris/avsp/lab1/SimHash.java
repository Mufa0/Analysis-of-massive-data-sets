/**
 * 
 */
package hr.fer.zemris.avsp.lab1;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

import org.apache.commons.codec.digest.DigestUtils;

import sun.security.provider.MD5;

/**
 * @author Mateo Stjepanovic
 * Class representing algorithm for creating SimHash on text
 */
public class SimHash {
	
	private String input;
	private Boolean isFilePath;
	/**
	 * Constructor that gets input that will represent text to minimize, of file path to this text
	 * @param input - input string that can represent text itself of string to that text
	 * @param isFilePath - flag that is telling if input is text of file path
	 */
	public SimHash(String input, Boolean isFilePath) {
		this.input = input;
		this.isFilePath = isFilePath;
	}
	/**
	 * Getter for input string
	 * @return input string
	 */
	public String getInput() {
		return input;
	}
	
	/**
	 * Setter for input string
	 * @param input - input string
	 */
	public void setInput(String input) {
		this.input = input;
	}
	
	/**
	 * Function that calculates SimHash on input, if input is filePath than file is first read and put into input variable.
	 * After creating units by splitting text by \space as delimiter hash for each unit is calculated and turned into array of 128 bits.
	 * Calculation is done but adding one if bit is "1" subtracting one if bit i "0". Then convert that summations into bits by defining that bit is "1" if value on i-th element is GE 0,
	 * else its 0. For the end that array of bits is converted into hex string.
	 * @return
	 */
	public String calculateSimHash() {
		String[] units;
		long[] sh = new long[128];
		
		if(this.isFilePath) {
			extractText(this.input);
		}
		
		Arrays.fill(sh,0);
		units = this.input.split(" ");
		
		for (String unit : units) {
			
			BitArray hash = new BitArray(sh.length);
			hash.CalculateBits(DigestUtils.md5Hex(unit));
			
			for(int i = 0; i < hash.getValues().length;++i) {
				if(hash.getValueAtIndex(i) == 1) {
					sh[i]++;
				}else {
					sh[i]--;
				}
			}
		}	
		for ( int i = 0; i < sh.length; ++i) {
			if(sh[i] >= 0) {
				sh[i]= 1;
			}else {
				sh[i]= 0;
			}
		}
		return Calculations.calculateHexFromBit(sh);
	}
	
	/**
	 * Method that will read text document and store it back into input variable
	 * @param filePath - file path to the text document
	 */
	private void extractText(String filePath) {
		try {
			this.input = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	
	
}
