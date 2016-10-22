package helvidios.cp.ch1.adhoc.game.harder;

import java.util.Scanner;

public class _227_Puzzle {
	static final char EMPTY = ' ';
	
	public static void main(String... args){
		String data = "TRGSJ\r\n" + 
				"XDOKI\r\n" + 
				"M VLN\r\n" + 
				"WPABE\r\n" + 
				"UQHCF\r\n" + 
				"ARRBBL0\r\n" + 
				"ABCDE\r\n" + 
				"FGHIJ\r\n" + 
				"KLMNO\r\n" + 
				"PQRS \r\n" + 
				"TUVWX\r\n" + 
				"AAA\r\n" + 
				"LLLL0\r\n" + 
				"ABCDE\r\n" + 
				"FGHIJ\r\n" + 
				"KLMNO\r\n" + 
				"PQRS \r\n" + 
				"TUVWX\r\n" + 
				"AAAAABBRRRLL0\r\n" + 
				"Z\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		final int PUZZLE_SIZE = 5;
		int puzzleCount = 0;
		game:while(scanner.hasNext()){
			char[][] puzzleConfig = new char[PUZZLE_SIZE][PUZZLE_SIZE];
			// read in puzzle config
			for(int i = 0; i < PUZZLE_SIZE; i++){
				String line = scanner.nextLine();
				if(line.charAt(0) == 'Z') break game;
				for(int c = 0; c < line.length(); c++){
					char cell = line.charAt(c);
					puzzleConfig[i][c] = cell;
				}
			}
			StringBuilder moves = new StringBuilder();
			// read in moves
			while(true){
				String line = scanner.nextLine();
				if(line.charAt(line.length() - 1) == '0'){
					moves.append(line.substring(0, line.length() - 1));
					break;
				}
				moves.append(line);
			}
			char[][] finalConfig = getFinalConfig(puzzleConfig, moves.toString().toCharArray());
			if(puzzleCount != 0) System.out.println();
			System.out.printf("Puzzle #%1$d:\n", ++puzzleCount);
			if(finalConfig != null){
				System.out.println(printConfig(finalConfig));
			}else{
				System.out.println("This puzzle has no final configuration.");
			}
		}
		scanner.close();
	}
	
	static String printConfig(char[][] config){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < config.length; i++){
			for(int j = 0; j < config[0].length; j++){
				builder.append(config[i][j]);
				if(j < config[0].length - 1) builder.append(" ");
			}
			if(i < config.length - 1) builder.append("\n");
		}
		return builder.toString();
	}
	
	static boolean moveAbove(char[][] config){
		int[] emptyCellLocation = getEmptyCellLocation(config);
		if(emptyCellLocation[0] < 1) return false;
		swap(config, emptyCellLocation, new int[]{emptyCellLocation[0] - 1, emptyCellLocation[1]});
		return true;
	}
	
	static boolean moveBelow(char[][] config){
		int[] emptyCellLocation = getEmptyCellLocation(config);
		if(emptyCellLocation[0] == config.length - 1) return false;
		swap(config, emptyCellLocation, new int[]{emptyCellLocation[0] + 1, emptyCellLocation[1]});
		return true;
	}
	
	static boolean moveRight(char[][] config){
		int[] emptyCellLocation = getEmptyCellLocation(config);
		if(emptyCellLocation[1] == config[0].length - 1) return false;
		swap(config, emptyCellLocation, new int[]{emptyCellLocation[0], emptyCellLocation[1] + 1});
		return true;
	}
	
	static boolean moveLeft(char[][] config){
		int[] emptyCellLocation = getEmptyCellLocation(config);
		if(emptyCellLocation[1] < 1) return false;
		swap(config, emptyCellLocation, new int[]{emptyCellLocation[0], emptyCellLocation[1] - 1});
		return true;
	}
	
	static int[] getEmptyCellLocation(char[][] config){
		for(int i = 0; i < config.length; i++){
			for(int j = 0; j < config[0].length; j++){
				if(config[i][j] == EMPTY){
					return new int[]{i,j};
				}
			}
		}
		throw new RuntimeException("Unable to find empty cell in the specified puzzle config.");
	}
	
	public static char[][] getFinalConfig(char[][] puzzleConfig, char[] moves){
		char[][] finalConfig = copyConfig(puzzleConfig);
		boolean invalidConfig = false;
		for(char move : moves){
			switch (move) {
				case 'A':
					invalidConfig = !moveAbove(finalConfig);
					break;
				case 'B':
					invalidConfig = !moveBelow(finalConfig);
					break;
				case 'R':
					invalidConfig = !moveRight(finalConfig);
					break;
				default:
					invalidConfig = !moveLeft(finalConfig);
			}
			if(invalidConfig) return null;
		}
		return finalConfig;
	}
	
	static char[][] copyConfig(char[][] config){
		char[][] newConfig = new char[config.length][config[0].length];
		for(int i = 0; i < config.length; i++){
			for(int j = 0; j < config[0].length; j++){
				newConfig[i][j] = config[i][j];
			}
		}
		return newConfig;
	}
	
	static void swap(char[][] config, int[] cell1, int[] cell2){
		char temp = config[cell1[0]][cell1[1]];
		config[cell1[0]][cell1[1]] = config[cell2[0]][cell2[1]];
		config[cell2[0]][cell2[1]] = temp;
	}
}
