package helvidios.cp.ch2.lineards.bitmanip;

import java.util.Scanner;

public class _594_OneLittleTwoLittleThreeLittleEndians {
	public static void main(String... args){
		String data = "123456789\r\n" + 
				"-123456789\r\n" + 
				"1\r\n" + 
				"16777216\r\n" + 
				"20034556\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			byte[] bytes = toByteArray(n);
			reverse(bytes);
			System.out.printf("%1$d converts to %2$d\n", n, toInt(bytes));
		}
		scanner.close();
	}
	
	public static void reverse(byte[] bytes){
		int from = 0;
		int to = bytes.length - 1;
		while(from < to){
			byte temp = bytes[from];
			bytes[from] = bytes[to];
			bytes[to] = temp;
			from++; to--;
		}
	}
	
	public static byte[] toByteArray(int n){
		return new byte[]{
				(byte)(n >>> 24),
				(byte)(n >>> 16),
				(byte)(n >>> 8),
				(byte)n
		};
	}
	
	public static int toInt(byte[] bytes){
		return bytes[3] & 0xFF |
	           (bytes[2] & 0xFF) << 8 |
	           (bytes[1] & 0xFF) << 16 |
	           (bytes[0] & 0xFF) << 24;
	}
}
