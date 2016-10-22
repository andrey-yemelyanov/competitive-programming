package helvidios.cp.ch3.greedy;

import java.util.*;

public class BitMask{
	public static void main(String[] args){
		String data = "100 50 60\r\n100 50 50\r\n100 0 100\r\n1 0 100\r\n15 1 15\r\n";
		String data2 = "20421198 11724734 31928748\r\n1541522 10226990 20764468\r\n22651935 2478699 31095674\r\n1995360670 908222743 1868651617\r\n305542918 594331857 1426036714\r\n1265639777 1580376576 1885248297\r\n1442823820 658800174 1919310996\r\n604563406 1050668699 2128532112";
		/*
			13133233
			19429997
			10902496
			957429345
			1305069817
			1880088222
			704659827
			1542920241
		*/
		String data3 = "1995360670 908222743 1868651617";
		Scanner scanner = new Scanner(data2);
		while(scanner.hasNext()){
			long n = scanner.nextLong();
			long low = scanner.nextLong();
			long high = scanner.nextLong();
			System.out.println(getMask(n, low, high));
		}
	}
	
	static long mask(int i){
		long n = 0;
		while(i >= 0){
			n = (n << 1) | 1;
			i--;
		}
		return n;
	}
	
	static boolean isBitSet(long n, int i){
		return ((1L << i) & n) > 0;
	}
	
	static long turnOnBit(long n, int i){
		return (1L << i) | n;
	}
	
	static final long getMask(long n, long low, long high){
		final int bitLength = 32;
		long mask = 0;
		for(int i = bitLength - 1; i >= 0; i--){
			if(isBitSet(n, i)){
				if((mask | mask(i - 1)) < low){
					mask = turnOnBit(mask, i);
				}
			}else{
				if(turnOnBit(mask, i) <= high){
					mask = turnOnBit(mask, i);
				}
			}
		}
		return mask;
	}
}