package helvidios.cp.ch3.completesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class _11205_TheBrokenPedometer {
	public static void main(String... args){
		String data = "2\r\n" + 
				"7\r\n" + 
				"10\r\n" + 
				"1 1 1 0 1 1 1\r\n" + 
				"0 0 1 0 0 1 0\r\n" + 
				"1 0 1 1 1 0 1\r\n" + 
				"1 0 1 1 0 1 1\r\n" + 
				"0 1 1 1 0 1 0\r\n" + 
				"1 1 0 1 0 1 1\r\n" + 
				"1 1 0 1 1 1 1\r\n" + 
				"1 0 1 0 0 1 0\r\n" + 
				"1 1 1 1 1 1 1\r\n" + 
				"1 1 1 1 0 1 1\r\n" + 
				"6\r\n" + 
				"10\r\n" + 
				"0 1 1 1 0 0\r\n" + 
				"1 0 0 0 0 0\r\n" + 
				"1 0 1 0 0 0\r\n" + 
				"1 1 0 0 0 0\r\n" + 
				"1 1 0 1 0 0\r\n" + 
				"1 0 0 1 0 0\r\n" + 
				"1 1 1 0 0 0\r\n" + 
				"1 1 1 1 0 0\r\n" + 
				"1 0 1 1 0 0\r\n" + 
				"0 1 1 0 0 0";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int p = scanner.nextInt();
			int n = scanner.nextInt();
			int[] codes = new int[n];
			for(int i = 0; i < n; i++){
				String line = "";
				for(int j = 0; j < p; j++){
					line += scanner.next();
				}
				codes[i] = Integer.parseInt(line, 2);
			}
			System.out.println(solve(p, codes));
		}
		scanner.close();
	}
	
	/**
	 * Generates bitmasks consisting of max p bits and returns a map that associates parity value from 1 to p
	 * with a list of corresponding masks.
	 */
	public static Map<Integer, List<Integer>> generateBitmasks(int p){
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for(int i = 1; i <= Math.pow(2, p) - 1; i++){
			int bitParity = bitParity(i);
			if(!map.containsKey(bitParity)){
				map.put(bitParity, new ArrayList<Integer>());
			}
			map.get(bitParity).add(i);
		}
		return map;
	}
	
	static int bitParity(int mask){
		int parity = 0;
		while(mask != 0){
			parity++;
			mask ^= (mask & ~(mask - 1));
		}
		return parity;
	}
	
	public static int solve(int p, int[] codes){
		Map<Integer, List<Integer>> masks = generateBitmasks(p);
		for(int i = 1; i <= p; i++){
			for(int mask : masks.get(i)){
				Set<Integer> s = new HashSet<Integer>();
				for(int j = 0; j < codes.length; j++){
					s.add(codes[j] & mask);
				}
				if(s.size() == codes.length) return i;
			}
		}
		return p;
	}
}
