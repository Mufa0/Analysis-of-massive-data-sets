package hr.fer.zemris.avsp.lab2;

import java.util.ArrayList;

public class Basket {
	
	ArrayList<Integer> objects;
	ArrayList<Tuple<Integer,Integer>> objectTuples = new ArrayList<>();
	String[] stringObjects;
	
	public Basket(String input) {
		objects = new ArrayList<>();
		stringObjects = input.split(" ");
		fillArrayObjects();
		//fillObjectTuples();
	}
	
	private void fillArrayObjects() {
		
		for (String object : stringObjects) {		
			objects.add(Integer.parseInt(object));
		}
	}
	
	private void fillObjectTuples() {
		for(int i = 0; i < objects.size();++i) {
			for(int j = i+1; j < objects.size(); ++j) {
				objectTuples.add(new Tuple<>(objects.get(i),objects.get(j)));
			}
		}
	}

	public ArrayList<Integer> getObjects() {
		return objects;
	}

	public String[] getStringObjects() {
		return stringObjects;
	}
	
	public ArrayList<Tuple<Integer,Integer>> getObjectTuples(){
		return objectTuples;
	}
	
	
}
