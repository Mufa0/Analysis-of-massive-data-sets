package hr.fer.zemris.avsp.lab5;

import java.util.ArrayList;
/**
 * Class representing row of itemUser matrix
 * @author Mateo Stjepanovic
 *
 */
public class Row {
	ArrayList<String> row;
	int gradeCount=0;
	private String errorMessage = "Column index must be lower then M";
	public Row() {
		row = new ArrayList<>();
	}
	public Row(ArrayList<String> row) {
		this.row = (ArrayList<String>) row.clone();
	}
	public Row(String row) {
		this.row = new ArrayList<>();
		for (String value : row.split(" ")) {
			this.row.add(value);
		}
	}
	public int getGradeCount() {
		return gradeCount;
	}
	/**
	 * @return the row
	 */
	public ArrayList<String> getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(ArrayList<String> row) {
		this.row = row;
	}
	
	public String getColumn(int index) {
		errorPrint(index, row.size(), errorMessage+index);
		return row.get(index);	
	}
	
	public void updateColumn(int index, String element) {
		errorPrint(index, row.size(), errorMessage+index);
		row.set(index, element);
	}
	
	private void errorPrint(int value, int upperBound, String message) {
		if(value >= upperBound) {
			System.err.println(message);
			System.exit(1);
		}
	}
	public void normalizeRow() {
		float mean = rowMean();
		for(int i = 0; i< row.size();++i) {
			if(row.get(i).equals("X")) {
				updateColumn(i, "0");
			}else {
				updateColumn(i,String.valueOf(Double.parseDouble(row.get(i)) - mean));
			}
		}
	}
	public float rowMean() {
		int sum = 0;
		for (String column : row) {
			if(!column.equals("X")) {
				gradeCount++;
				sum+= Double.parseDouble(column);
			}
		}
		return (float)sum/gradeCount;
	}
	
}
