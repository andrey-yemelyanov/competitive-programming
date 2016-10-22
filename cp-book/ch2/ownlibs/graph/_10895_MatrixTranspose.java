package helvidios.cp.ch2.ownlibs.graph;

import java.util.*;

public class _10895_MatrixTranspose {
	public static class Tuple{
		public int element;
		public int column;
		public Tuple(int element, int column){
			this.element = element;
			this.column = column;
		}
		public String toString(){
			return String.format("(%1$d,%2$d)", element, column);
		}
		public boolean equals(Object obj){
			Tuple tuple = (Tuple)obj;
			return this.element == tuple.element && this.column == tuple.column;
		}
	}
	public static void main(String... args){
		String data = "4 3\r\n" + 
				"3 1 2 3\r\n" + 
				"1 3 2\r\n" + 
				"2 2 3\r\n" + 
				"4 -1\r\n" + 
				"0\r\n\n" + 
				"3 1 2 3\r\n" + 
				"5 -2 11";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int nRows = scanner.nextInt();
			int nCols = scanner.nextInt();
			List<List<Tuple>> matrix = buildMatrix(scanner, nRows, nCols);
			List<List<Tuple>> transposedMatrix = transpose(matrix, nCols);
			System.out.print(printMatrix(transposedMatrix, nCols, nRows));
		}
		scanner.close();
	}
	
	public static String printMatrix(List<List<Tuple>> matrix, int nRows, int nCols){
		StringBuilder builder = new StringBuilder();
		builder.append(nRows);
		builder.append(" ");
		builder.append(nCols);
		builder.append("\n");
		
		for(int row = 0; row < matrix.size(); row++){
			int nNonZeroElements = matrix.get(row).size();
			builder.append(nNonZeroElements);
			if(nNonZeroElements == 0){
				builder.append("\n\n");
			}
			else{
				builder.append(" ");
				for(int col = 0; col < nNonZeroElements; col++){
					builder.append(matrix.get(row).get(col).column);
					if(col < nNonZeroElements - 1) builder.append(" ");
				}
				builder.append("\n");
				for(int col = 0; col < nNonZeroElements; col++){
					builder.append(matrix.get(row).get(col).element);
					if(col < nNonZeroElements - 1) builder.append(" ");
				}
				builder.append("\n");
			}
		}
		
		return builder.toString();
	}
	
	private static List<List<Tuple>> buildMatrix(Scanner scanner, int nRows, int nCols){
		List<List<Tuple>> matrix = new ArrayList<List<Tuple>>();
		for(int row = 0; row < nRows; row++){
			matrix.add(new ArrayList<Tuple>());
			int r = scanner.nextInt();
			int[] nonZeroIndices = new int[r];
			for(int i = 0; i < r; i++){
				nonZeroIndices[i] = scanner.nextInt();
			}
			int[] nonZeroElements = new int[r];
			for(int i = 0; i < r; i++){
				nonZeroElements[i] = scanner.nextInt();
			}
			for(int i = 0; i < r; i++){			
				matrix.get(row).add(new Tuple(nonZeroElements[i], nonZeroIndices[i]));
			}
		}
		return matrix;
	}
	
	public static List<List<Tuple>> transpose(List<List<Tuple>> matrix, int nCols){
		List<List<Tuple>> transposedMatrix = new ArrayList<List<Tuple>>();
		// add rows to the transposed matrix
		// the number of rows in the transposed matrix is equal to the number of columns in the original matrix
		for(int i = 1; i <= nCols; i++){
			transposedMatrix.add(new ArrayList<Tuple>());
		}
		
		// process original matrix element by element
		for(int row = 0; row < matrix.size(); row++){
			for(int col = 0; col < matrix.get(row).size(); col++){
				Tuple pair = matrix.get(row).get(col);
				// place current pair into pair.column - 1 of the transposed matrix
				// update pair column value to the current row
				transposedMatrix.get(pair.column - 1).add(new Tuple(pair.element, row + 1));
			}
		}
		
		return transposedMatrix;
	}
}
