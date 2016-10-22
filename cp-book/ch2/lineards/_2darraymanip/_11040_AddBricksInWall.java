package helvidios.cp.ch2.lineards._2darraymanip;

import java.util.Scanner;

public class _11040_AddBricksInWall {
	public static void main(String... args){
		String data = "2\r\n" + 
				"255\r\n" + 
				"54 67\r\n" + 
				"10 18 13\r\n" + 
				"3 3 5 2\r\n" + 
				"2 1 2 1 1\r\n" + 
				"256\r\n" + 
				"64 64\r\n" + 
				"16 16 16\r\n" + 
				"4 4 4 4\r\n" + 
				"1 1 1 1 1\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int[][] wall = new int[5][];
			for(int i = 1; i <= 5; i++){
				int[] row = new int[i];
				for(int j = 1; j <= i; j++){
					row[j - 1] = scanner.nextInt();
				}
				wall[i - 1] = row; 
			}
			int[][] completeWall = buildCompleteWall(wall);
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < completeWall.length; i++){
				for(int j = 0; j < i + 1; j++){
					builder.append(completeWall[i][j]);
					if(j < i) builder.append(" ");
				}
				if(i < completeWall.length - 1) builder.append("\n");
			}
			System.out.println(builder.toString());
		}
		scanner.close();
	}
	
	public static int[] solveTriangle(int a, int b, int c){
		int x1 = (a - (b + c)) / 2;
		int x2 = x1 + b;
		int x3 = x1 + c;
		return new int[]{x1, x2, x3};
	}
	
	public static int[][] buildCompleteWall(int[][] wall){
		int[][] completeWall = new int[9][9];
		for(int row = 0; row < wall.length - 1; row++){
			for(int col = 0; col < wall[row].length; col++){
				int a = wall[row][col];
				int b = wall[row + 1][col];
				int c = wall[row + 1][col + 1];
				int[] solution = solveTriangle(a, b, c);
				completeWall[2 * row][2 * col] = wall[row][col];
				completeWall[2 * row + 1][2 * col] = solution[1];
				completeWall[2 * row + 1][2 * col + 1] = solution[2];
				completeWall[2 * row + 2][2 * col + 1] = solution[0];
			}
		}
		
		for(int col = 0; col < wall[wall.length - 1].length; col++){
			completeWall[completeWall.length - 1][2 * col] = wall[wall.length - 1][col];
		}
		
		return completeWall;
	}
}
