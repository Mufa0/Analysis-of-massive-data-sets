package hr.fer.zemris.avsp.lab2;

import java.util.ArrayList;
import java.util.Scanner;

public class PCY {

	public static void main(String[] args) {
		
		ArrayList<Basket> baskets = new ArrayList<>();
		
		Scanner br = new Scanner(System.in);

		int N = Integer.parseInt(br.nextLine());
		double s = Double.parseDouble(br.nextLine());
		int b = Integer.parseInt(br.nextLine());
		
		for (int i = 0; i < N; ++i) {
			baskets.add(new Basket(br.nextLine()));
		}
		
		br.close();
		
		PCYAlg pcy = new PCYAlg(baskets,N,s,b);
		pcy.PCYAlgorithmRun();
	}

}
