package helvidios.cp.ch2.lineards.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _10107_WhatIsTheMedian {
	public static void main(String... args){
		String data = "1\n3\n4\r\n" + 
				"60\r\n" + 
				"70\r\n" + 
				"50\r\n" + 
				"2";
		Scanner scanner = new Scanner(data);
		List<Long> numbers = new ArrayList<Long>();
		while(scanner.hasNext()){
			long number = scanner.nextLong();
			numbers.add(getInsertionIndex(numbers, number), number);
			System.out.println(getMedian(numbers));
		}
		scanner.close();
	}
	
	public static int getInsertionIndex(List<Long> numbers, long number){
		int i = 0;
		for(;i < numbers.size() && numbers.get(i) <= number; i++);
		return i;
	}
	
	public static long getMedian(List<Long> numbers){
		int middle = numbers.size() / 2;
		long median = numbers.size() % 2 == 0 
				? (numbers.get(middle - 1) + numbers.get(middle)) / 2 
				: numbers.get(middle);
		return median;
	}
}
