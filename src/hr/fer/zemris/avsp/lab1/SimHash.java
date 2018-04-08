package hr.fer.zemris.avsp.lab1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SimHash {

	public static void main(String[] args) {
		if(args.length != 2 ) {
			System.err.println("Number of input parameters incorrect");
			return;
		}
		
		String inputFile = args[0];
//		String inputFile = System.in.toString();
		String outputFile = args[1];
		
		SimHashAlg simhash = new SimHashAlg();
		
		ArrayList<long[]> hashes = new ArrayList<>();
		ArrayList<String> queries = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
		    int N = Integer.parseInt(br.readLine());
		    
		    for(int i = 0; i < N; ++i) {
		    	simhash.setInput(br.readLine());
		    	hashes.add(simhash.calculateSimHash());
		    }
		    int Q = Integer.parseInt(br.readLine());
		    
		    for(int i = 0; i < Q;++i) {
		    	queries.add(br.readLine());
		    }
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Utils.callQueries(queries, hashes, outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
