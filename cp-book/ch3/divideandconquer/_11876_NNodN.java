import java.util.*;
import java.util.stream.*;

public class _11876_NNodN{
	public static void main(String... args){
		Scanner s = new Scanner(System.in);
		List<Integer> seq = generateSequence(1000000);
		int nTests = s.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int c = 1; c <= nTests; c++){
			int A = s.nextInt();
			int B = s.nextInt();
			int lower = upperBound(seq, A);
			int upper = lowerBound(seq, B);
			sb.append(String.format("Case %d: %d\n", c, upper - lower + 1));
		}
		System.out.print(sb.toString());
	}
	
	static int lowerBound(List<Integer> arr, int key){
		int from = 0;
		int to = arr.size() - 1;
		while(to >= from){
			int mid = from + (to - from) / 2;
			if(arr.get(mid) == key) return mid;
			if(arr.get(mid) < key){
				if(mid + 1 == arr.size() || arr.get(mid + 1) > key) return mid;
				from = mid + 1;
			}else to = mid - 1;
		}
		return to;
	}
	
	static int upperBound(List<Integer> arr, int key){
		int from = 0;
		int to = arr.size() - 1;
		while(to >= from){
			int mid = from + (to - from) / 2;
			if(arr.get(mid) == key) return mid;
			if(arr.get(mid) > key){
				if(mid - 1 < 0 || arr.get(mid - 1) < key) return mid;
				to = mid - 1;
			}else from = mid + 1;
		}
		return to;
	}
	
	static List<Integer> generateSequence(int limit){
		List<Integer> seq = new ArrayList<>();
		seq.add(1);
		int n = 1;
		while(n <= limit){
			n += countDivisors(n);
			seq.add(n);
		}
		return seq;
	}
	
	static int countDivisors(int n){
		Set<Integer> divisors = new HashSet<>();
		for(int i = 1; i <= (int)Math.ceil(Math.sqrt(n)); i++){
			if(n % i == 0){
				divisors.add(n / i);
				divisors.add(i);
			}
		}
		return divisors.size();
	}
}