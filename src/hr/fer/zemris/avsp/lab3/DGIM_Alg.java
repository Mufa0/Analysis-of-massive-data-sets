package hr.fer.zemris.avsp.lab3;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class DGIM_Alg {
	private int N;
	private String window;
	private ArrayList<Bucket> buckets;
	public DGIM_Alg(int N) {
		this.N = N;
		this.buckets = new ArrayList<>();
		this.window = "";
	}
	/**
	 * Estimating number of ones in stream with DGIM
	 * @param input
	 */
	public void parseQuerie(String input,int t) {
		int k = Integer.parseInt(input.split(" ")[1]);
		int sum = 0;
		int minBucket = t-k;
		for(int i = 0; i < buckets.size();++i) {
			if(buckets.get(i).getTimestamp() >= minBucket) {
				if(i < buckets.size()-1) {
					sum+=buckets.get(i).getOnesCount();
				}else {
					sum+=buckets.get(i).getOnesCount()/2;
				}
				
			}else{
				try {
					sum-=buckets.get(i-1).getOnesCount();
					sum+=buckets.get(i-1).getOnesCount()/2;
				}catch (Exception e) {
					System.out.println(sum);
					return;
				}
				
				break;
			}
		}
		System.out.println(sum);
	}
	public int parseBits(String input, int t) {
		for(String s : input.split("")) {
			window = s + window;
			
			if(window.length() > N) {
				window = window.substring(0, N);
			}
			Collections.sort(buckets);
			if(buckets.size() > 0 && t-buckets.get(buckets.size()-1).getTimestamp() >= N) {
				buckets.remove(buckets.size()-1);
			}
			if(s.equals("1")) {
				updateBuckets(t);
				
			}
			t++;
		}
		Collections.sort(buckets);
		return t;
		
		
	}
	private void updateBuckets(int t) {
		
		Bucket latest = new Bucket(t,1);
		buckets.add(latest);
		Collections.sort(buckets);
		int minSize = 1;
		while(true) {
			if(getSameBucketsNum(minSize) > 2) {
				for(int i = 0; i < buckets.size(); ++i) {
					if(buckets.get(i).getOnesCount() == minSize) {
						Bucket first = buckets.get(i+1);
						Bucket second = buckets.get(i+2);
						
						buckets.remove(i+1);
						buckets.remove(i+1);
						
						buckets.add(new Bucket(first.getTimestamp()>second.getTimestamp()?first.getTimestamp():second.getTimestamp(), first.getOnesCount()+second.getOnesCount()));
						Collections.sort(buckets);
						minSize*=2;
						break;
					}
				}
			}else {
				break;
			}
		}
		
	}
	private int getSameBucketsNum(int minSize) {
		// TODO Auto-generated method stub
		return buckets.stream().filter(x->x.getOnesCount()==minSize).toArray().length;
	}
}
