package helvidios.cp.ch3.completesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _00296_Safebreaker {
	public static void main(String... args){
		String data = "4\r\n" + 
				"6\r\n" + 
				"9793 0/1\r\n" + 
				"2384 0/2\r\n" + 
				"6264 0/1\r\n" + 
				"3383 1/0\r\n" + 
				"2795 0/0\r\n" + 
				"0218 1/0\r\n" + 
				"1\r\n" + 
				"1234 4/0\r\n" + 
				"1\r\n" + 
				"1234 2/2\r\n" + 
				"2\r\n" + 
				"6428 3/0\r\n" + 
				"1357 3/0";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int n = scanner.nextInt();
			int[][] guesses = new int[n][3];
			for(int i = 0; i < n; i++){
				guesses[i][0] = scanner.nextInt();
				String[] l = scanner.next().split("/");
				guesses[i][1] = Integer.parseInt(l[0]);
				guesses[i][2] = Integer.parseInt(l[1]);
			}
			List<Integer> answer = guessCode(guesses);
			if(answer.size() == 0){
				System.out.println("impossible");
			}else if(answer.size() > 1){
				System.out.println("indeterminate");
			}else{
				System.out.println(String.format("%04d", answer.get(0)));
			}
		}
		scanner.close();
	}
	
	public static List<Integer> guessCode(int[][] guesses){
		List<Integer> answer = new ArrayList<Integer>();
		for(int code = 0; code <= 9999; code++){
			if(codePossible(code, guesses)){
				answer.add(code);
			}
		}
		return answer;
	}
	
	public static boolean codePossible(int code, int[][] guesses){
		for(int[] guess : guesses){
			if(!codeConsistentWithGuess(code, guess)) return false;
		}
		return true;
	}
	
	public static boolean codeConsistentWithGuess(int code, int[] guess){
		String guessedNumberStr = String.format("%04d", guess[0]);
		String codeStr = String.format("%04d", code);
		int nCorrectDigits = guess[1];
		int nCorrectDigitsInWrongPosition = guess[2];
		return countCorrectDigits(codeStr, guessedNumberStr) == nCorrectDigits 
				&& countCorrectDigitsInWrongPosition(codeStr, guessedNumberStr) == nCorrectDigitsInWrongPosition;
	}
	
	public static int countCorrectDigits(String codeStr, String guessedNumberStr){
		int count = 0;
		for(int i = 0; i < Math.min(guessedNumberStr.length(), codeStr.length()); i++){
			if(codeStr.charAt(codeStr.length() - 1 - i) == guessedNumberStr.charAt(guessedNumberStr.length() - 1 - i)) count++;
		}
		return count;
	}
	
	public static int countCorrectDigitsInWrongPosition(String codeStr, String guessedNumberStr){
		int[] freq1 = new int[10];
		int[] freq2 = new int[10];
		for(char c : guessedNumberStr.toCharArray()){
			freq1[Character.getNumericValue(c)]++;
		}
		for(char c : codeStr.toCharArray()){
			freq2[Character.getNumericValue(c)]++;
		}
		
		int nCommonDigits = 0;
		for(int i = 0; i < freq1.length; i++){
			if(freq1[i] != 0 && freq2[i] != 0) 
				nCommonDigits += Math.min(freq1[i], freq2[i]);
		}
		return nCommonDigits - countCorrectDigits(codeStr, guessedNumberStr);
	}
}
