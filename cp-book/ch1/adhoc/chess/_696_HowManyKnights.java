package helvidios.cp.ch1.adhoc.chess;

import java.util.Scanner;

public class _696_HowManyKnights {
	public static void main(String... args){
		String data = "2 3\r\n" + 
				"5 5\r\n" + 
				"4 7\r\n" + 
				"500 500\r\n"+
				"0 0"; 
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int nRows = scanner.nextInt();
			int nCols = scanner.nextInt();
			if(nRows == 0 && nCols == 0) break;
			int nKnights = countKnights(nRows, nCols);
			System.out.printf("%1$d knights may be placed on a %2$d row %3$d column board.\n",
					nKnights, nRows, nCols);
		}
		scanner.close();
	}
	
	static int countKnights(int nRows, int nCols){
		if(nRows > nCols){
			int temp = nRows;
			nRows = nCols;
			nCols = temp;
		}
		
		if(nRows == 1) return nCols;
		if(nRows == 2) return nCols / 4 * 4 + (nCols % 4 == 1? 2 : (nCols % 4 >= 2? 4 : 0));
		return (int) Math.ceil((double)(nRows * nCols) / 2);
	}
}
