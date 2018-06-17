package hr.fer.zemris.avsp.lab5;

import java.util.ArrayList;
import java.util.Scanner;

public class CF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		//Number of items
		int N;
		//Number of users
		int M;
		
		//Number of queries
		int Q;
		
		String firstLine = input.nextLine();
		N = Integer.parseInt(firstLine.split(" ")[0]);
		M = Integer.parseInt(firstLine.split(" ")[1]);
		
		UserItemMatrix matrix = new UserItemMatrix();
		
		for(int i = 0; i < N; ++i) {
			matrix.addRow(input.nextLine());
		}
		
		Q = Integer.parseInt(input.nextLine());
		
		for(int i = 0; i < Q; ++i) {
			Query q = new Query(input.nextLine());
			q.getOutput(matrix);
		}
	}

}
