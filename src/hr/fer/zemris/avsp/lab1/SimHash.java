/**
 * 
 */
package hr.fer.zemris.avsp.lab1;
import org.apache.commons.codec.digest.DigestUtils;
/**
 * @author Mateo Stjepanovic
 *
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
	
	
	
}
