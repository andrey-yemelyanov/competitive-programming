package helvidios.cp.ch3.completesearch;

import java.util.Scanner;

public class _10102_ThePathInColoredField {
	public static void main(String... args){
		String data = "4\r\n" + 
				"1223\r\n" + 
				"2123\r\n" + 
				"2213\r\n" + 
				"3212\r\n" + 
				"2\r\n" + 
				"12\r\n" + 
				"33\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int m = scanner.nextInt();
			int[][] field = new int[m][m];
			for(int row = 0; row < m; row++){
				String numbers = scanner.next();
				for(int col = 0; col < m; col++){
					field[row][col] = Character.getNumericValue(numbers.charAt(col));
				}
			}
			System.out.println(findMinDistance(field));
		}
		scanner.close();
	}
	
	public static int findMinDistance(int[][] field){
		int maxMinDistance = Integer.MIN_VALUE;
		for(int row = 0; row < field.length; row++){
			for(int col = 0; col < field[0].length; col++){
				int minDistance = Integer.MAX_VALUE;
				if(field[row][col] == 1){
					for(int r = 0; r < field.length; r++){
						for(int c = 0; c < field[0].length; c++){
							if(field[r][c] == 3){
								int distance = Math.abs(row - r) + Math.abs(col - c);
								if(distance < minDistance) minDistance = distance;
							}
						}
					}
					if(minDistance > maxMinDistance) maxMinDistance = minDistance;
				}
			}
		}
		return maxMinDistance;
	}
}
