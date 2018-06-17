package hr.fer.zemris.avsp.lab5;

import java.util.ArrayList;
/**
 * Matrix representing user item matrix
 * @author Mateo Stjepanovic
 *
 */
public class UserItemMatrix {
	private ArrayList<Row> stringMatrix;
	private String errorMessage = "Row index must be lower then N";
	public UserItemMatrix() {
		stringMatrix = new ArrayList<>();
	}
	public UserItemMatrix(UserItemMatrix matrix) {
		stringMatrix = new ArrayList<>();
		for(int i = 0; i < matrix.getStringMatrix().size(); ++i) {
			stringMatrix.add(new Row(matrix.getRow(i).getRow()));
		}
		
	}
	public UserItemMatrix(ArrayList<Row> stringMatrix) {
		this.stringMatrix = (ArrayList<Row>) stringMatrix.clone();
		
	}

	/**
	 * @return the stringMatrix
	 */
	public ArrayList<Row> getStringMatrix() {
		return stringMatrix;
	}
	
	public Row getRow(int index) {
		errorPrint(index, stringMatrix.size(),errorMessage);
		return stringMatrix.get(index);
	}
	public void addRow(String row) {
		stringMatrix.add(new Row(row));
	}
	public void updateRow(int index, Row elements) {
		errorPrint(index, stringMatrix.size(),errorMessage);
		
		stringMatrix.set(index, elements);
	}
	public String getColumn(int rowIndex, int columnIndex) {
		
		return getRow(rowIndex).getColumn(columnIndex);
	}
	
	public void updateColumn(int rowIndex, int columnIndex, String element) {
		getRow(rowIndex).updateColumn(columnIndex, element);
	}
	
	private void errorPrint(int value, int upperBound, String message) {
		if(value >= upperBound) {
			System.err.println(message);
			System.exit(1);
		}
	}
	
	public void normalizeMatrix() {
		for(int i = 0; i < stringMatrix.size();++i) {
			stringMatrix.get(i).normalizeRow();
		}
	}
	public void transponeMatrix() {
		UserItemMatrix pom = new UserItemMatrix(this.stringMatrix);
		stringMatrix.clear();
		for(int i = 0; i < pom.getRow(0).getRow().size();++i) {
			Row r = new Row();
			for(int j = 0; j < pom.getStringMatrix().size();++j) {
				r.addColumnt(pom.getColumn(j, i));
	
			}
			stringMatrix.add(r);
		}
	}
}
