package helvidios.cp.ch1.adhoc.game.easier;

import java.util.Scanner;

public class _12239_Bingo {
	public static void main(String... args){
		String data = "6 7\r\n" + 
				"2 1 3 4 0 6 5\r\n" + 
				"5 4\r\n" + 
				"5 3 0 1\r\n" + 
				"5 3\r\n" + 
				"1 5 0\r\n" + 
				"0 0";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int N = scanner.nextInt();
			int B = scanner.nextInt();
			if(N == 0 && B == 0) break;
			int[] remainingBalls = new int[B];
			for(int i = 0; i < remainingBalls.length; i++){
				remainingBalls[i] = scanner.nextInt();
			}
			if(callOutPossible(remainingBalls, N))
				System.out.println("Y");
			else System.out.println("N");;
		}
		scanner.close();
	}
	
	static boolean callOutPossible(int[] balls, int N){
		boolean[] callouts = new boolean[N + 1];
		for(int i = 0; i < balls.length; i++){
			for(int j = 0; j < balls.length; j++){
				if(i != j){
					callouts[Math.abs(balls[i] - balls[j])] = true;
				}
			}
		}
		
		for(int i = 1; i < callouts.length; i++){
			if(!callouts[i])
				return false;
		}
		
		return true;
	}
}
