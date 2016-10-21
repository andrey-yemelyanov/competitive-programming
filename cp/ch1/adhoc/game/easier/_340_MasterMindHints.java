package helvidios.cp.ch1.adhoc.game.easier;

import java.util.Scanner;

public class _340_MasterMindHints {
	public static void main(String... args){
		String data = "4\r\n" + 
				"1 3 5 5\r\n" + 
				"1 1 2 3\r\n" + 
				"4 3 3 5\r\n" + 
				"6 5 5 1\r\n" + 
				"6 1 3 5\r\n" + 
				"1 3 5 5\r\n" + 
				"0 0 0 0\r\n" + 
				"10\r\n" + 
				"1 2 2 2 4 5 6 6 6 9\r\n" + 
				"1 2 3 4 5 6 7 8 9 1\r\n" + 
				"1 1 2 2 3 3 4 4 5 5\r\n" + 
				"1 2 1 3 1 5 1 6 1 9\r\n" + 
				"1 2 2 5 5 5 6 6 6 7\r\n" + 
				"0 0 0 0 0 0 0 0 0 0\r\n" + 
				"0\r\n"; 
		Scanner scanner = new Scanner(data);
		int gameCount = 0;
		while(scanner.hasNextInt()){
			int codeLength = scanner.nextInt();
			if(codeLength == 0) break;
			int[] secretCode = new int[codeLength];
			for(int i = 0; i < secretCode.length; i++){
				secretCode[i] = scanner.nextInt();
			}
		
			System.out.printf("Game %1$d:\n", ++gameCount);
			while(scanner.hasNextInt()){
				int[] guess = new int[codeLength];
				for(int i = 0; i < guess.length; i++){
					guess[i] = scanner.nextInt();
				}
				
				if(endOfGame(guess)) break;
				int[] hint = getHint(secretCode, guess);
				System.out.printf("    (%1$d,%2$d)\n", hint[0], hint[1]);
			}
		}
		scanner.close();
	}
	
	static int[] getHint(int[] secretCode, int[] guess){
		return new int[] {getStrongMatches(secretCode, guess), getWeakMatches(secretCode, guess)};
	}
	
	static int getStrongMatches(int[] secretCode, int[] guess){
		int count = 0;
		for(int code = 0; code < secretCode.length; code++){
			if(secretCode[code] == guess[code]){
				count++;
			}
		}
		return count;
	}
	
	static int getWeakMatches(int[] secretCode, int[] guess){
		int count = 0;
		boolean[] codeMatches = new boolean[secretCode.length];
		boolean[] guessMatches = new boolean[guess.length];
		for(int code = 0; code < secretCode.length; code++){
			for(int i = 0; i < guess.length; i++){
				if(code != i && secretCode[code] == guess[i]){
					if(!codeMatches[code] && !guessMatches[i]){
						codeMatches[code] = true;
						guessMatches[i] = true;
						count++;
					}
				}
			}
		}
		
		int strongMatches = getStrongMatches(secretCode, guess);
		if(strongMatches > count) return 0;
		return count - strongMatches;
	}
	
	static boolean endOfGame(int[] guess){
		for(int g : guess){
			if(g != 0) return false;
		}
		return true;
	}
}
