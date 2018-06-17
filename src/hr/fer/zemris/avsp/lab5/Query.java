package hr.fer.zemris.avsp.lab5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Query {
	//Row in matrix
	int I;
	//Column in matrix
	int J;
	//Item-item or user-user
	int T;
	//Cardinality
	int K;
	
	public Query(String input) {
		I = Integer.parseInt(input.split(" ")[0]);
		J = Integer.parseInt(input.split(" ")[1]);
		T = Integer.parseInt(input.split(" ")[2]);
		K = Integer.parseInt(input.split(" ")[3]);
	}
	
	public Query(int I, int J, int T, int K) {
		this.I = I;
		this.J = J;
		this.T = T;
		this.K = K;
	}

	/**
	 * @return the i
	 */
	public int getI() {
		return I;
	}

	/**
	 * @return the j
	 */
	public int getJ() {
		return J;
	}

	/**
	 * @return the t
	 */
	public int getT() {
		return T;
	}

	/**
	 * @return the k
	 */
	public int getK() {
		return K;
	}
	
	public void getOutput(UserItemMatrix matrix, UserItemMatrix matrixNorm) {
		if(T == 1) {
			int pom = I;
			I = J;
			J = pom;
		}
		
		ArrayList<Double> similarity = new ArrayList<>();
		
		
		for(int i = 0; i < matrixNorm.getStringMatrix().size();++i) {
			double sum1Square = 0.0;
			double sum2Square = 0.0;
			double sum= 0.0;
			for(int j = 0; j< matrixNorm.getStringMatrix().get(i).getRow().size(); j++) {
				sum+= Double.parseDouble(matrixNorm.getStringMatrix().get(i).getColumn(j)) * Double.parseDouble(matrixNorm.getStringMatrix().get(I-1).getColumn(j));
				sum1Square += Math.pow(Double.parseDouble(matrixNorm.getStringMatrix().get(i).getColumn(j)), 2);
				sum2Square += Math.pow(Double.parseDouble(matrixNorm.getStringMatrix().get(I-1).getColumn(j)), 2);
			}
			similarity.add((double)sum/(Math.sqrt(sum1Square)*Math.sqrt(sum2Square)));
		}
		
		
		ArrayList<Pair> pomSim = new ArrayList<>();
		for(int i = 0; i < similarity.size(); ++i) {
			if(similarity.get(i) > 0 && similarity.get(i)< 1 && i != I-1) {
				if(T==0) {
					if(!matrix.getColumn(i, J-1).equals("X")) {
						pomSim.add(new Pair(i,similarity.get(i)));
					}
				}else {
					if(!matrix.getColumn(J-1, i).equals("X")){
						pomSim.add(new Pair(i,similarity.get(i)));
					}
				}
				
			}
		}
		
		Collections.sort(pomSim);
		Collections.reverse(pomSim);
		if(pomSim.size() > K) {
			pomSim.subList(K, pomSim.size()).clear();;
		}
		Double simSum = 0.0;
		Double simGradeSum = 0.0;
		if(T==0) {
			for(int i = 0; i < pomSim.size(); ++i) {
				simSum += pomSim.get(i).getValue();
				simGradeSum += pomSim.get(i).getValue()*Double.parseDouble(matrix.getColumn(pomSim.get(i).getIndex(), J-1));
			}
		}else {
			for(int i = 0; i < pomSim.size(); ++i) {
				simSum += pomSim.get(i).getValue();
				simGradeSum += pomSim.get(i).getValue()*Double.parseDouble(matrix.getColumn(J-1,pomSim.get(i).getIndex()));
			}
		}
		
		DecimalFormat df = new DecimalFormat("#.000");
		BigDecimal bd = new BigDecimal(simGradeSum/simSum);
		BigDecimal res = bd.setScale(3, RoundingMode.HALF_UP);
		System.out.println(df.format(res));
	}
}
