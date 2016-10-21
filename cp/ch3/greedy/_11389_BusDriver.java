package helvidios.cp.ch3.greedy;

import java.util.*;

public class _11389_BusDriver{
	public static void main(String[] args){
		String data = "2 20 5\r\n10 15\r\n10 15\r\n2 20 5\r\n10 10\r\n10 10\r\n0 0 0";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			int d = scanner.nextInt();
			int r = scanner.nextInt();
			if(n == 0 && d == 0 && r == 0) break;
			int[] morningRoutes = new int[n];
			int[] eveningRoutes = new int[n];
			for(int i = 0; i < n; i++){
				morningRoutes[i] = scanner.nextInt();
			}
			for(int i = 0; i < n; i++){
				eveningRoutes[i] = scanner.nextInt();
			}
			System.out.println(getMinimumCost(morningRoutes, eveningRoutes, d, r));
		}
		scanner.close();
	}
	
	static int getMinimumCost(int[] morningRoutes, int[] eveningRoutes, int d, int r){
		Arrays.sort(morningRoutes);
		Arrays.sort(eveningRoutes);
		int totalCost = 0;
		for(int i = 0; i < morningRoutes.length; i++){
			int morning = morningRoutes[i];
			int evening = eveningRoutes[eveningRoutes.length - 1 - i];
			totalCost += (morning + evening) > d ? r * (morning + evening - d) : 0;
		}
		return totalCost;
	}
}
