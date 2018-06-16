package hr.fer.zemris.avsp.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DGIM {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		
		/*
		 * Window size
		 */
		String N = input.nextLine();
		DGIM_Alg alg = new DGIM_Alg(Integer.parseInt(N));
		
		/**
		 * Reading all lines and if line is representing query, parse it end give estimation.
		 * Else put bits to window and maintain buckets
		 */
		int t=0;
		while(input.hasNextLine()) {
			String line = input.nextLine();
			if(line.split(" ").length == 2) {
				alg.parseQuerie(line,t);
			}else {
				t = alg.parseBits(line,t);
			}
		}
		input.close();
		
	}

}
