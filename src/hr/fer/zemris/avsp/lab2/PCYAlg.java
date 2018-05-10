package hr.fer.zemris.avsp.lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PCYAlg {
	ArrayList<Basket> baskets;
	HashMap<Integer,Integer> objectCounter = new HashMap<>();
	HashMap<Tuple<Integer,Integer>,Integer> pairs;
	int[] partitionCounter;
	int N;
	double s;
	int b;
	int threshold;
	
	public PCYAlg(ArrayList<Basket> baskets,int N, double s, int b) {
		this.baskets = baskets;
		this.N = N;
		this.s = s;
		this.b = b;
		this.threshold = (int)Math.floor(s * N);
		this.partitionCounter = new int[b];
		Arrays.fill(this.partitionCounter,0);
	}

	public ArrayList<Basket> getBaskets() {
		return baskets;
	}

	public void setBaskets(ArrayList<Basket> baskets) {
		this.baskets = baskets;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public double getS() {
		return s;
	}

	public void setS(double s) {
		this.s = s;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public HashMap<Integer, Integer> getObjectCounter() {
		return objectCounter;
	}

	public int[] getPartitionCounter() {
		return partitionCounter;
	}

	public double getThreshold() {
		return threshold;
	}
	
	public void PCYAlgorithmRun() {
		
		for (Basket basket : baskets) {
			for (Integer object : basket.getObjects()) {
				if(objectCounter.containsKey(object)) {
					objectCounter.put(object, objectCounter.get(object)+1);
				}else {
					objectCounter.put(object, 1);
				}
			}
		}
		
		for (Basket basket : baskets) {
			for(int i = 0; i < basket.getObjects().size();++i) {
				for(int j = i+1; j < basket.getObjects().size(); ++j) {
					
					int first = basket.getObjects().get(i);
					int second = basket.getObjects().get(j);
						
					if(objectCounter.get(first) >= threshold && objectCounter.get(second) >= threshold) {
							
						if(first > second) {
							int tmp = first;
							first = second;
							second = tmp;
						}
						
						int k = ((first * objectCounter.size()) + second)%b;
						partitionCounter[k]++;
						
					}
				}
//			for(Tuple<Integer,Integer> pair : basket.getObjectTuples()) {
//				if(objectCounter.get(pair.getX()) >= threshold && objectCounter.get(pair.getY()) >= threshold) {
//					int k = ((pair.getX() * objectCounter.size()) + pair.getY())%b;
//					partitionCounter[k]++;
//				}
			}
		}
		
		pairs = new HashMap<>();
		
		for ( Basket basket : baskets) {
			for(int i = 0; i < basket.getObjects().size();++i) {
				for(int j = i+1	; j < basket.getObjects().size(); ++j) {
					
					int first = basket.getObjects().get(i);
					int second = basket.getObjects().get(j);
					
					if(objectCounter.get(first) >= threshold && objectCounter.get(second) >= threshold) {
						
						if(first > second) {
							int tmp = first;
							first = second;
							second = tmp;
						}
						
						Tuple<Integer,Integer> pair = new Tuple<Integer,Integer>(first,second);
						
						
						int k = ((first * objectCounter.size()) +second)%b;
						
						if(partitionCounter[k] >=threshold) {
							if(pairs.containsKey(pair)) {
								pairs.put(pair, pairs.get(pair)+1);
							}else {
								pairs.put(pair, 1);
							}
						}
					}
				}
			}
			/*for(Tuple<Integer,Integer> pair : basket.getObjectTuples()) {
				
			}*/
		}
		
		printResult();
	}
	
	private void printResult() {
		int m = objectCounter.values().stream().filter(val -> val >= threshold).toArray().length;
		System.out.println((int)(m*(m-1)/2));
		System.out.println(pairs.size());
		ArrayList<Integer> sortedValues = (ArrayList<Integer>) pairs.values().stream().filter(val -> val >= threshold).collect(Collectors.toList());
		Collections.sort(sortedValues);
		for(int i = sortedValues.size()-1; i >= 0;--i) {
			System.out.println(sortedValues.get(i));
		}
		
	}
}
