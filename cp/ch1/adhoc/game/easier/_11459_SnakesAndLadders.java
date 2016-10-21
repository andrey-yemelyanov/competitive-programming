package helvidios.cp.ch1.adhoc.game.easier;

import java.util.Scanner;

public class _11459_SnakesAndLadders {
	public static void main(String... args){
		String data = "1\r\n" + 
				"3 1 3\r\n" + 
				"4 20\r\n" + 
				"3\r\n" + 
				"4\r\n" + 
				"5";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int nPlayers = scanner.nextInt();
			int nSnakesLadders = scanner.nextInt();
			int nDieRolls = scanner.nextInt();
			int[][] snakesLadders = new int[nSnakesLadders][2];
			for(int i = 0; i < nSnakesLadders; i++){
				snakesLadders[i][0] = scanner.nextInt();
				snakesLadders[i][1] = scanner.nextInt();
			}
			int[] dieRolls = new int[nDieRolls];
			for(int i = 0; i < nDieRolls; i++){
				dieRolls[i] = scanner.nextInt();
			}
			int[] positions = getPlayerPositions(snakesLadders, dieRolls, nPlayers);
			StringBuilder builder = new StringBuilder();
			for(int pos = 0; pos < positions.length; pos++){
				builder.append(String.format(
						"Position of player %1$d is %2$d.\n", pos + 1, positions[pos]));
			}
			System.out.print(builder.toString());
		}
		scanner.close();
	}
	
	static int[] getPlayerPositions(int[][] snakesLadders, int[] dieRolls, int nPlayers){
		int[] positions = new int[nPlayers];
		for(int i = 0; i < positions.length; i++){
			positions[i] = 1;
		}
		
		for(int roll = 0; roll < dieRolls.length; roll++){
			int player = roll % nPlayers;
			int newPosition = positions[player] + dieRolls[roll];
			if(hasSnakeOrLadder(snakesLadders, newPosition)){
				positions[player] = getSnakeOrLadder(snakesLadders, newPosition)[1];
			}else{
				positions[player] = newPosition;
			}
			
			if(positions[player] >= 100){ // game over
				positions[player] = 100;
				break;
			}
		}
		return positions;
	}
	
	static int[] getSnakeOrLadder(int[][] snakesLadders, int square){
		for(int i = 0; i < snakesLadders.length; i++){
			if(snakesLadders[i][0] == square){
				return snakesLadders[i];
			}
		}
		throw new RuntimeException("Cannot find snake or ladder for given square.");
	}
	
	static boolean hasSnakeOrLadder(int[][] snakesLadders, int square){
		for(int i = 0; i < snakesLadders.length; i++){
			if(snakesLadders[i][0] == square){
				return true;
			}
		}
		return false;
	}
}
