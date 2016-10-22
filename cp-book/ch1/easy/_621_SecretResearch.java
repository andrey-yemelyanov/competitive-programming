package helvidios.cp.ch1.easy;

import java.util.Scanner;

public class _621_SecretResearch {
	public static void main(String... args){
		String data = "4\r\n" + 
				"78\r\n" + 
				"7835\r\n" + 
				"19078\r\n" + 
				"944\r\n"; 
		Scanner scanner = new Scanner(data);
		int resultCount = scanner.nextInt();
		while(resultCount-- > 0){
			System.out.println(decrypt(scanner.next()));
		}
		scanner.close();
	}
	
	private static String decrypt(String encryptedResult){
		final String positive = "+";
		final String negative = "-";
		final String failed = "*";
		final String notCompleted = "?";
		
		if(isPositiveResult(encryptedResult)) return positive;
		if(isNegativeResult(encryptedResult)) return negative;
		if(isExperimentFailed(encryptedResult)) return failed;
		if(isExperimentNotCompleted(encryptedResult)) return notCompleted;
		
		if(isPartialNegativeResult(encryptedResult)) return negative;
		if(isPartialExperimentFailed(encryptedResult)) return failed;
		return notCompleted;
	}
	
	private static boolean isPartialExperimentNotCompleted(String result){
		return result.startsWith("190");
	}
	
	private static boolean isExperimentNotCompleted(String result){
		return isPartialExperimentNotCompleted(result) && isPositiveResult(result.substring(3));
	}
	
	private static boolean isPartialExperimentFailed(String result){
		return result.charAt(0) == '9' && result.charAt(result.length() - 1) == '4'; 
	}
	
	private static boolean isExperimentFailed(String result){
		return isPartialExperimentFailed(result) && isPositiveResult(result.substring(1, result.length() - 1));
	}
	
	private static boolean isPartialNegativeResult(String result){
		return result.endsWith("35"); 
	}
	
	private static boolean isNegativeResult(String result){
		return isPartialNegativeResult(result) && isPositiveResult(result.substring(0, result.length() - 2));
	}
	
	private static boolean isPositiveResult(String result){
		switch (result) {
			case "1":
			case "4":
			case "78":
				return true;
			default:
				return false;
		}
	}
}
