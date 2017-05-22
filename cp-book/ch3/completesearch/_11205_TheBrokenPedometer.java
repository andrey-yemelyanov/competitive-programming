import java.util.*;

public class _11205_TheBrokenPedometer {
	public static void main(String... args){
		Scanner scanner = new Scanner(System.in);
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
			System.out.println(getMinActiveLeds(p, codes));
		}
		scanner.close();
	}

	static int getMinActiveLeds(int p, int[] symbols){
		int minLeds = p + 1;
		for(int activeLeds = 0; activeLeds < (2 << p); activeLeds++){
			Set<Integer> s = new HashSet<>();
			for(int symbol : symbols){
				s.add(symbol & activeLeds);
			}
			if(s.size() == symbols.length) {
				minLeds = Math.min(minLeds, countSetBits(activeLeds));
			}
		}
		return minLeds;
	}

	static int countSetBits(int n){
		int count = 0;
		while(n != 0){
			if((n & 1) != 0) count++;
			n = n >> 1;
		}
		return count;
	}
}
