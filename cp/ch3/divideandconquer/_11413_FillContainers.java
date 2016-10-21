package helvidios.cp.ch3.divideandconquer;

import java.util.*;

public class _11413_FillContainers{
	public static void main(String[] args){
		String data = "5 3\r\n1 2 3 4 5\r\n3 2\r\n4 78 9";
		String data2 = "5 3\r\n1 2 3 4 8\r\n3 2\r\n4 78 9";
		String data3 = "3 100\n4 78 9";
		String data4 = "10 11\r\n1 1 1 1 1 1 1 1 1 1\r\n10 10\r\n1 1 1 1 1 1 1 1 1 1\r\n5 3\r\n1 2 3 4 5\r\n3 2\r\n4 78 9\r\n1 2\r\n1\r\n1 1\r\n1\r\n5 1\r\n1 2 3 4 5\r\n5 2\r\n1 2 3 4 5\r\n10 3\r\n9 5 1 4 2 8 7 3 6 5\r\n21 6\r\n1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1";
		String data5 = "5 9\r\n1 9 2 3 5\r\n10 9\r\n1 2 3 4 9 12 34 45 34 2\r\n5 2\r\n12 12 45 5 5";
		Scanner scanner = new Scanner(data5);
		while(scanner.hasNext()){
			int n = scanner.nextInt(); int m = scanner.nextInt();
			int[] vessels = new int[n];
			for(int i = 0; i < vessels.length; i++){
				vessels[i] = scanner.nextInt();
			}
			System.out.println(maxContainerCapacity(vessels, m));
		}
		scanner.close();
	}
	
	static int maxContainerCapacity(int[] vessels, int m){
		double lowerBound = max(vessels);
		double upperBound = sum(vessels);
		
		if(capacitySatisfiesConstraints((int)lowerBound, vessels, m)){
			return (int)lowerBound;
		}
		
		double mid = 0.0; int ans = 0;
		final double epsilon = 1e-2;
		while(Math.abs(lowerBound - upperBound) > epsilon){
			mid = (lowerBound + upperBound) / 2.0;
			int candidateCapacity = (int)Math.ceil(mid);
			if(capacitySatisfiesConstraints(candidateCapacity, vessels, m)){
				upperBound = mid;
				ans = candidateCapacity;
			}else{
				lowerBound = mid;
			}
		}
		return ans;
	}
	
	static int sum(int[] vessels){
		int sum = 0;
		for(int i = 0; i < vessels.length; i++){
			sum += vessels[i];
		}
		return sum;
	}
	
	static int max(int[] vessels){
		int max = 0;
		for(int i = 0; i < vessels.length; i++){
			max = Math.max(max, vessels[i]);
		}
		return max;
	}
	
	static boolean capacitySatisfiesConstraints(int capacity, int[] vessels, int m){
		int nextVessel = 0; int filled = 0;
		while(m > 0){
			if(filled + vessels[nextVessel] > capacity){
				m--; filled = 0;
			}else{
				filled += vessels[nextVessel];
				nextVessel++;
			}
			if(nextVessel == vessels.length) return true;
		}
		return false;
	}
}
