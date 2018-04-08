package hr.fer.zemris.avsp.lab1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SimHashBuckets {

	public static void main(String[] args) {
		// String inputFile = System.in.toString();

		SimHashAlg simhash = new SimHashAlg();

		ArrayList<long[]> hashes = new ArrayList<>();
		ArrayList<String> queries = new ArrayList<>();
		Scanner br = new Scanner(System.in);

		int N = Integer.parseInt(br.nextLine());

		for (int i = 0; i < N; ++i) {
			simhash.setInput(br.nextLine());
			hashes.add(simhash.calculateSimHash());
		}
		int Q = Integer.parseInt(br.nextLine());

		for (int i = 0; i < Q; ++i) {
			queries.add(br.nextLine());
		}

		SimHashBucketsAlg simhashBuckets = new SimHashBucketsAlg(hashes);
		try {
			Utils.callQueries(queries, hashes, simhashBuckets.createCandidates());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
