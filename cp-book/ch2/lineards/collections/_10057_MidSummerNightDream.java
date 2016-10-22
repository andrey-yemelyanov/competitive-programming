package helvidios.cp.ch2.lineards.collections;

import java.util.Arrays;
import java.util.Scanner;

public class _10057_MidSummerNightDream {
	public static void main(String... args){
		String data = "2\r\n" + 
				"10\r\n" + 
				"10\r\n" + 
				"4\n1\n2\n2\n4";
		String data2 = "1\n5";
		Scanner scanner = new Scanner(data2);
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			int[] numbers = new int[n];
			for(int i = 0; i < numbers.length; i++){
				numbers[i] = scanner.nextInt();
			}
			int[] result = findCombinationLockCode(numbers);
			System.out.println(result[0] + " " + result[1] + " " + result[2]);
		}
		scanner.close();
	}
	
	public static int[] findCombinationLockCode(int[] numbers){
		Arrays.sort(numbers);
		int medianIndex = numbers.length % 2 == 0 ? numbers.length / 2 - 1 : numbers.length / 2;
		int A = numbers[medianIndex];
		int allPossible = numbers.length % 2 == 0 ? numbers[medianIndex + 1] - A + 1 : 1;
		int to = numbers.length % 2 == 0 ? numbers[medianIndex + 1] : A;
		int inInput = findLastOccurrence(numbers, to) - findFirstOccurrence(numbers, A) + 1;
		return new int[]{A, inInput, allPossible};
	}
	
	public static int findFirstOccurrence(int[] numbers, int number){
		for(int i = 0; i < numbers.length; i++){
			if(numbers[i] == number) return i;
		}
		throw new RuntimeException("Unable to find " + number + " in numbers.");
	}
	
	public static int findLastOccurrence(int[] numbers, int number){
		for(int i = numbers.length - 1; i >= 0; i--){
			if(numbers[i] == number) return i;
		}
		throw new RuntimeException("Unable to find " + number + " in numbers.");
	}
}
