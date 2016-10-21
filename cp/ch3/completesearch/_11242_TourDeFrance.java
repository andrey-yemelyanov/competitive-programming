package helvidios.cp.ch3.completesearch;

import java.util.Arrays;
import java.util.Scanner;

public class _11242_TourDeFrance {
	public static void main(String... args){
		String data = "2 4\r\n" + 
				"40 50\r\n" + 
				"12 14 16 19\r\n" + 
				"0\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int f = scanner.nextInt();
			if(f == 0) break;
			int r = scanner.nextInt();
			int[] frontCluster = new int[f];
			int[] rearCluster = new int[r];
			for(int i = 0; i < frontCluster.length; i++){
				frontCluster[i] = scanner.nextInt();
			}
			for(int i = 0; i < rearCluster.length; i++){
				rearCluster[i] = scanner.nextInt();
			}
			System.out.printf("%1$.2f\n", maxSpread(frontCluster, rearCluster));
		}
		scanner.close();
	}
	
	public static double maxSpread(int[] frontCluster, int[] rearCluster){
		double[] driveRatios = new double[frontCluster.length * rearCluster.length];
		int i = 0;
		for(int m = 0; m < frontCluster.length; m++){
			for(int n = 0; n < rearCluster.length; n++){
				double driveRatio = (double)rearCluster[n] / frontCluster[m];
				driveRatios[i++] = driveRatio;
			}
		}
		
		Arrays.sort(driveRatios);
		
		double maxSpread = Double.MIN_VALUE;
		for(int d = 1; d < driveRatios.length; d++){
			double spread = driveRatios[d] / driveRatios[d - 1];
			if(spread > maxSpread){
				maxSpread = spread;
			}
		}
		
		return maxSpread;
	}
}
