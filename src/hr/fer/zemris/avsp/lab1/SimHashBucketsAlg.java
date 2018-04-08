package hr.fer.zemris.avsp.lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class SimHashBucketsAlg {
	ArrayList<long[]> hashes;
	ArrayList< LinkedHashSet<Integer>> candidates = new ArrayList<>();
	int belt;
	int r;
	public SimHashBucketsAlg() {
		hashes = new ArrayList<>();
		candidates = new ArrayList<>();
		belt=8;
		r=16;
	}
	public SimHashBucketsAlg(ArrayList<long[]> hashes) {
		this.hashes = hashes;
		for(int i = 0; i < this.hashes.size();++i) {
			candidates.add(new LinkedHashSet<Integer>());
		}
//		candidates = new ArrayList<>();
		belt=8;
		r=16;
	}
	public ArrayList<long[]> getHashes() {
		return hashes;
	}
	public void setHashes(ArrayList<long[]> hashes) {
		this.hashes = hashes;
	}
	public ArrayList< LinkedHashSet<Integer>>  getCandidates() {
		return candidates;
	}

	public int getBelt() {
		return belt;
	}
	public void setBelt(int belt) {
		this.belt = belt;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	
	/**
	 * Creating candidates for simhash comparison. Creating bucket of belt that is given in assigment.
	 * @return
	 */
	ArrayList<LinkedHashSet<Integer>>  createCandidates(){
		
		for(int i = 0; i < belt;++i) {
			HashMap<Integer, ArrayList<Integer>> buckets = new HashMap<>();
			
			for(int j = 0; j < hashes.size(); ++j) {
				
				long[] hash = hashes.get(j);
				String bitString = "";
				for(int k = i*16; k < i*16+16;++k) {
					bitString+=hash[k];
				}
				int value = Integer.parseInt(bitString,2);
				
				ArrayList<Integer> bucket = new ArrayList<>();
				
				if(buckets.get(value) != null) {
					bucket.addAll(buckets.get(value));
					candidates.get(j).addAll(bucket);
					for (Integer textId : bucket) {
						candidates.get(textId).add(j);
					}
				}else {
					bucket = new ArrayList<>();
				}
				bucket.add(j);
				buckets.put(value, bucket);
			}
		}
		return candidates;
		
	}
}
