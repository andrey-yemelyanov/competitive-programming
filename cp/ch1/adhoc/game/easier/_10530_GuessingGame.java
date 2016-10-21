package helvidios.cp.ch1.adhoc.game.easier;

import java.util.Scanner;

public class _10530_GuessingGame {
	static final int TOO_HIGH = 1;
	static final int TOO_LOW = 2;
	public static void main(String... args){
//		String data = "10\r\n" + 
//				"too high\r\n" + 
//				"3\r\n" + 
//				"too low\r\n" + 
//				"4\r\n" + 
//				"too high\r\n" + 
//				"2\r\n" + 
//				"right on\r\n" + 
//				"5\r\n" + 
//				"too low\r\n" + 
//				"7\r\n" + 
//				"too high\r\n" + 
//				"6\r\n" + 
//				"right on\r\n" + 
//				"10\n"+
//				"too high\n"+
//				"9\n"+
//				"too high\n"+
//				"8\n"+
//				"right on\n"+
//				"10\n"+
//				"too high\n"+
//				"9\n"+
//				"too low\n"+
//				"8\n"+
//				"right on\n"+
//				"5\n"+
//				"too high\n"+
//				"2\n"+
//				"too low\n"+
//				"3\n"+
//				"right on\n"+
//				"8\n"+
//				"too high\n"+
//				"8\n"+
//				"too low\n"+
//				"9\n"+
//				"right on\n"+
//				"9\n"+
//				"too low\n"+
//				"8\n"+
//				"too low\n"+
//				"7\n"+
//				"too low\n"+
//				"10\n"+
//				"right on\n"+
//				"0\r\n" + 
//				"";
//		String data2 = "8\n"+
//				"too high\n"+
//				"8\n"+
//				"too low\n"+
//				"9\n"+
//				"right on\n"+
//				"0\n";
//		String data3 = "10\r\n" + 
//				"right on\r\n" + 
//				"5\r\n" + 
//				"too low\r\n" + 
//				"8\r\n" + 
//				"too high\r\n" + 
//				"7\r\n" + 
//				"too high\r\n" + 
//				"6\r\n" + 
//				"too high\r\n" + 
//				"5\r\n" + 
//				"too low\r\n" + 
//				"6\r\n" + 
//				"right on\r\n" + 
//				"10\r\n" + 
//				"right on\r\n" + 
//				"5\r\n" + 
//				"too low\r\n" + 
//				"8\r\n" + 
//				"too high\r\n" + 
//				"7\r\n" + 
//				"too high\r\n" + 
//				"6\r\n" + 
//				"too high\r\n" + 
//				"5\r\n" + 
//				"too low\r\n" + 
//				"6\r\n" + 
//				"right on\r\n" + 
//				"0\r\n" + 
//				"";
		String data4 = "8\r\n" +
			     "right on\r\n" +
			      "0";
		Scanner scanner = new Scanner(data4);
		int[] guesses = new int[11];
		boolean isHonest = true;
		while(scanner.hasNext()){
			int guess = scanner.nextInt();
			if(guess == 0) break;
			String response = scanner.nextLine();
			while (response.isEmpty()) {
				response = scanner.nextLine();
			}
			
			if(response.equals("too high")){
				if(guesses[guess] == TOO_LOW) {
					isHonest = false;
				}else guesses[guess] = TOO_HIGH;
			}else if(response.equals("too low")){
				if(guesses[guess] == TOO_HIGH) {
					isHonest = false;
				} else guesses[guess] = TOO_LOW;
			}
			else{
				if(guesses[guess] != 0) isHonest = false;
				System.out.println(isGameHonest(guesses, guess, !isHonest) 
						? "Stan may be honest" 
						: "Stan is dishonest");
				guesses = new int[11];
				isHonest = true;
			}
		}
		scanner.close();
	}
	
	static boolean isGameHonest(int[] guesses, int correctGuess, boolean conflictingResponses){
		if(conflictingResponses) return false;
		
		for(int guess = 1; guess < correctGuess; guess++){
			if(guesses[guess] != 0 && guesses[guess] != TOO_LOW){
				return false;
			}
		}
		
		for(int guess = correctGuess + 1; guess < guesses.length; guess++){
			if(guesses[guess] != 0 && guesses[guess] != TOO_HIGH){
				return false;
			}
		}
		
		return true;
	}
}
