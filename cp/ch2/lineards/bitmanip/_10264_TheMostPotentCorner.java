package helvidios.cp.ch2.lineards.bitmanip;

import java.util.Scanner;

public class _10264_TheMostPotentCorner {
	public static void main(String... args){
		String data = "3\r\n" + 
				"82\r\n" + 
				"73\r\n" + 
				"8\r\n" + 
				"49\r\n" + 
				"120\r\n" + 
				"44\r\n" + 
				"242\r\n" + 
				"58\r\n" + 
				"2\n1\n1\n1\n1";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int nDimensions = scanner.nextInt();
			int nCorners = (int) Math.pow(2, nDimensions);
			int[] weights = new int[nCorners];
			for(int i = 0; i < nCorners; i++){
				weights[i] = scanner.nextInt();
			}
			System.out.println(maxPotencySum(weights, nDimensions));
		}
		scanner.close();
	}
	
	public static int maxPotencySum(int[] weights, int nDimensions){
		int maxPotencySum = Integer.MIN_VALUE;
		for(int i = 0; i < weights.length; i++){
			int potency1 = potency(i, weights, nDimensions);
			for(int neighbor : getNeighboringCorners(nDimensions, i)){
				int potency2 = potency(neighbor, weights, nDimensions);
				int sum = potency1 + potency2;
				if(sum > maxPotencySum) maxPotencySum = sum;
			}
		}
		return maxPotencySum;
	}
	
	public static int potency(int corner, int[] weights, int nDimensions){
		int potency = 0;
		for(int neighbor : getNeighboringCorners(nDimensions, corner)){
			potency += weights[neighbor];
		}
		return potency;
	}
	
	public static int[] getNeighboringCorners(int nDimensions, int sourceCorner){
		int[] neighbors = new int[nDimensions];
		for(int i = 0; i < nDimensions; i++){
			int neighbor = sourceCorner ^ (1 << i);
			neighbors[i] = neighbor;
		}
		return neighbors;
	}
}
