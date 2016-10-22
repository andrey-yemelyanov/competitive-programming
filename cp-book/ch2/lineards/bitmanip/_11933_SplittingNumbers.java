package helvidios.cp.ch2.lineards.bitmanip;

import java.util.Scanner;

public class _11933_SplittingNumbers {
	public static void main(String... args){
		String data = "6\n7\r\n" + 
				"13\r\n" + 
				"0";
		Scanner scanner = new Scanner(data);
		while(true){
			int number = scanner.nextInt();
			if(number == 0) break;
			int[] onBits = onBits(number);
			System.out.printf("%1$d %2$d\n", a(onBits), b(onBits));
		}
		scanner.close();
	}
	
	public static int a(int[] onBits){
		int a = 0;
		for(int i = 0; i < onBits.length; i += 2){
			a = a | (1 << onBits[i]);
		}
		return a;
	}
	
	public static int b(int[] onBits){
		int b = 0;
		for(int i = 1; i < onBits.length; i += 2){
			b = b | (1 << onBits[i]);
		}
		return b;
	}
	
	public static int[] onBits(int number){
		int[] onBits = new int[countOnBits(number)];
		int current = 0;
		for(int i = 0; i < 32; i++){
			if(bitSet(number, i)) onBits[current++] = i;
		}
		return onBits;
	}
	
	public static int countOnBits(int number){
		int count = 0;
		for(int i = 0; i < 32; i++){
			if(bitSet(number, i)) count++;
		}
		return count;
	}
	
	public static boolean bitSet(int number, int bitIndex){
		return (number & (1 << bitIndex)) != 0;
	}
}
