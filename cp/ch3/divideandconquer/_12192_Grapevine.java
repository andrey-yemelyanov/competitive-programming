package helvidios.cp.ch3.divideandconquer;

import java.util.*;

public class _12192_Grapevine{
	public static void main(String[] args){
		String data = "4 5\r\n13 21 25 33 34\r\n16 21 33 35 35\r\n16 33 33 45 50\r\n23 51 66 83 93\r\n3\r\n22 90\r\n33 35\r\n20 100\r\n4 4\r\n1 7 9 11\r\n5 8 10 12\r\n7 10 15 17\r\n11 19 30 41\r\n4\r\n6 20\r\n7 9\r\n10 10\r\n13 14\r\n0 0";
		String data2 = "3 3\r\n4 4 4\r\n4 4 4\r\n4 4 4\r\n1\r\n4 4\r\n0 0";
		String data3 = "8 9\r\n6 15 19 28 34 34 40 48 56\r\n9 16 24 29 35 42 44 50 63\r\n16 22 25 33 42 42 53 60 64\r\n25 28 28 42 43 45 58 60 64\r\n30 38 39 47 50 54 61 65 70\r\n32 46 53 62 69 74 74 77 81\r\n35 50 55 68 72 81 90 94 102\r\n36 50 55 71 79 89 94 97 105\r\n9\r\n832 928\r\n345 474\r\n19 427\r\n274 529\r\n12 59\r\n398 508\r\n10 77\r\n165 457\r\n504 928\r\n5 5\r\n4 11 15 19 20\r\n6 15 22 29 36\r\n14 20 28 33 43\r\n17 27 34 34 51\r\n17 27 38 45 55\r\n2\r\n382 413\r\n41 106\r\n10 1\r\n7\r\n10\r\n17\r\n17\r\n24\r\n27\r\n36\r\n42\r\n43\r\n50\r\n4\r\n701 739\r\n28 60\r\n397 421\r\n119 405\r\n2 8\r\n3 12 13 18 22 28 31 36\r\n10 17 21 23 25 34 36 44\r\n7\r\n86 268\r\n4 13\r\n605 780\r\n425 864\r\n37 108\r\n317 642\r\n19 117\r\n4 9\r\n3 11 15 16 19 28 36 44 48\r\n5 14 15 20 21 36 41 46 50\r\n14 16 17 23 23 45 54 56 64\r\n22 26 27 33 42 46 54 56 70\r\n2\r\n14 20\r\n362 545\r\n9 9\r\n9 10 16 20 23 23 26 33 34\r\n17 24 26 35 37 40 47 55 59\r\n22 31 39 44 53 57 64 65 66\r\n26 36 40 48 57 59 65 65 73\r\n29 41 46 55 63 67 76 83 91\r\n31 46 52 64 66 72 85 93 97\r\n36 53 58 71 74 75 93 101 108\r\n39 54 58 71 78 85 100 102 111\r\n43 57 59 75 85 93 101 108 111\r\n7\r\n142 307\r\n263 673\r\n308 387\r\n150 370\r\n180 481\r\n99 791\r\n546 553\r\n2 7\r\n8 16 21 23 27 28 33\r\n15 22 24 29 36 37 44\r\n6\r\n273 948\r\n12 60\r\n589 639\r\n376 621\r\n310 542\r\n33 793\r\n8 8\r\n6 9 15 22 23 31 32 34\r\n11 11 23 30 37 44 52 56\r\n16 25 29 35 37 50 58 62\r\n24 34 42 44 48 57 59 63\r\n27 41 42 48 55 58 65 67\r\n30 48 48 48 59 59 69 78\r\n31 49 54 56 66 67 77 85\r\n33 57 66 73 78 81 81 93\r\n1\r\n34 930\r\n9 4\r\n3 5 13 13\r\n5 13 17 22\r\n9 17 23 28\r\n10 17 27 30\r\n18 20 34 42\r\n21 27 40 48\r\n27 31 49 57\r\n28 40 52 61\r\n29 41 56 67\r\n2\r\n204 218\r\n360 405\r\n10 4\r\n5 5 12 12\r\n5 6 19 28\r\n12 15 24 31\r\n13 16 28 40\r\n15 19 32 48\r\n20 27 34 49\r\n28 31 38 52\r\n35 38 46 55\r\n38 46 51 61\r\n47 49 58 67\r\n6\r\n2 4\r\n30 476\r\n173 648\r\n196 490\r\n790 913\r\n432 589\r\n0 0";
		Scanner scanner = new Scanner(data3);
		StringBuilder out = new StringBuilder();
		while(scanner.hasNext()){
			int nRows = scanner.nextInt();
			int nCols = scanner.nextInt();
			int[][] props = new int[nRows][nCols];
			if(nRows == 0 && nCols == 0) break;
			for(int i = 0; i < nRows; i++){
				for(int j = 0; j < nCols; j++){
					props[i][j] = scanner.nextInt();
				}
			}
			int nQueries = scanner.nextInt();
			while(nQueries-- > 0){
				int from = scanner.nextInt();
				int to = scanner.nextInt();
				out.append(getMaxSize(props, from, to));
				out.append("\n");
			}
			out.append("-");
			out.append("\n");
		}
		System.out.print(out.toString());
	}
	
	static int getMaxSize(int[][] props, int from, int to){
		int maxSize = Integer.MIN_VALUE;
		for(int row = 0; row < props.length; row++){
			int col = binarySearchAlongRow(props[row], from);
			if(col >= 0){
				int[] upperBound = binarySearchAlongDiagonal(props, row, col, to);
				int size = Math.min(upperBound[0] - row + 1, upperBound[1] - col + 1);
				maxSize = Math.max(size, maxSize);
			}
		}
		return maxSize == Integer.MIN_VALUE ? 0 : maxSize;
	}
	
	static int binarySearchAlongRow(int[] row, int key){
		int i = Arrays.binarySearch(row, key);
		if(i >= 0){
			for(; i >= 0 && row[i] == key; i--);
			return i + 1;
		}else{
			int index = -i - 1;
			if(index >= row.length) return -1;
			return index;
		}
	}
	
	static int[] binarySearchAlongDiagonal(int[][] props, int row, int col, int key){
		int startRow = row; int startCol = col;
		int endRow = 0; int endCol = 0;
		if(props.length - 1 - startRow <= props[0].length - 1 - startCol){
			endRow = props.length - 1;
			endCol = startCol + (endRow - startRow);
		}else{
			endCol = props[0].length - 1;
			endRow = startRow + (endCol - startCol);
		}
		
		int midRow = 0; int midCol = 0;
		while(startRow <= endRow && startCol <= endCol){
			midRow = startRow + (endRow - startRow) / 2;
			midCol = startCol + (endCol - startCol) / 2;
			if(props[midRow][midCol] == key){
				int i = midRow + 1; int j = midCol + 1;
				for(; i < props.length && j < props[0].length && props[i][j] == key; i++, j++);
				return new int[]{i - 1, j - 1};
			}
			if(props[midRow][midCol] < key){
				startRow = midRow + 1;
				startCol = midCol + 1;
			}else{
				endRow = midRow - 1;
				endCol = midCol - 1;
			}
		}
		
		if(props[midRow][midCol] > key) return new int[]{midRow - 1, midCol - 1};
		return new int[]{midRow, midCol};
	}
}
