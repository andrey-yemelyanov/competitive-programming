package helvidios.cp.ch3.completesearch;

import java.util.Scanner;

public class _10276_HanoiTower {
	public static void main(String... args){
		String data = "2\r\n" + 
				"4\r\n" + 
				"25";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int[] pegs = new int[scanner.nextInt()];
			System.out.println(placeBall(1, pegs));
		}
		scanner.close();
	}
	
	static int placeBall(int ball, int[] pegs){
		for(int peg = 0; peg < pegs.length; peg++){
			if(pegs[peg] != 0 && isSquare(pegs[peg] + ball)){
				pegs[peg] = ball;
				return placeBall(ball + 1, pegs);
			}
		}
		
		for(int peg = 0; peg < pegs.length; peg++){
			if(pegs[peg] == 0){
				pegs[peg] = ball;
				return placeBall(ball + 1, pegs);
			}
		}
		
		return ball - 1;
	}
	
	static boolean isSquare(int n){
		int squareRoot = (int) Math.sqrt(n);
		return squareRoot * squareRoot == n;
	}
}
