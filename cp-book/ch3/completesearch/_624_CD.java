package helvidios.cp.ch3.completesearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _624_CD {
	public static void main(String... args){
		String data = "5 3 1 3 4\r\n" + 
				"10 4 9 8 4 2\r\n" + 
				"20 4 10 5 7 4\r\n" + 
				"90 8 10 23 1 2 3 4 5 7\r\n" + 
				"45 8 4 10 44 43 12 9 8 2";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int availableLength = scanner.nextInt();
			int nRecords = scanner.nextInt();
			int[] tracks = new int[nRecords];
			for(int i = 0; i < nRecords; i++){
				tracks[i] = scanner.nextInt();
			}
			Queue<Integer> bestSolution = optimize(tracks, availableLength);
			System.out.println(toString(bestSolution));
		}
		scanner.close();
	}
	
	static String toString(Queue<Integer> solution){
		StringBuilder str = new StringBuilder();
		int sum = 0;
		for(Integer i : solution){
			sum += i;
			str.append(i);
			str.append(" ");
		}
		str.append("=:" + sum);
		return str.toString();
	}
	
	static Queue<Integer> optimize(int[] tracks, int availableLength){
		return optimize(tracks, 0, availableLength, new LinkedList<Integer>(), Integer.MAX_VALUE);
	}
	
	static Queue<Integer> optimize(
			int[] tracks, 
			int from, 
			int availableLength, 
			Queue<Integer> currentSolution,
			int minCost){
		Queue<Integer> bestSolution = null;
		for(int i = from; i < tracks.length; i++){
			if(tracks[i] <= availableLength){
				currentSolution.add(tracks[i]);
				int newAvailableLength = availableLength - tracks[i];
				optimize(tracks, i + 1, newAvailableLength, currentSolution, minCost);
				if(newAvailableLength < minCost){
					minCost = newAvailableLength;
					bestSolution = new LinkedList<Integer>(currentSolution);
					System.out.println(toString(currentSolution));
				}
				currentSolution.remove();
			}
		}
		return bestSolution;
	}
}
