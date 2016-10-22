package helvidios.cp.ch3.completesearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class _735_DartAMania {
	public static void main(String... args){
		String data = "162\r\n" + 
				"175\r\n" + 
				"2\r\n" + 
				"68\r\n" + 
				"211\r\n" + 
				"114\r\n" + 
				"49\n"+
				"-100";
		String data2 = "67\n68\n69\n70\n71\n72\n73";
		Scanner scanner = new Scanner(data2);
		StringBuilder out = new StringBuilder();
		final String separator = "**********************************************************************";
		while(scanner.hasNextInt()){
			int score = scanner.nextInt();
			if(score <= 0) break;
			int[] result = countPermutationsAndCombinations(score);
			if(result[0] == 0 && result[1] == 0){
				out.append(String.format("THE SCORE OF %1$d CANNOT BE MADE WITH THREE DARTS.\n", score));
			}else{
				out.append(String.format("NUMBER OF COMBINATIONS THAT SCORES %1$d IS %2$d.\n", score, result[0]));
				out.append(String.format("NUMBER OF PERMUTATIONS THAT SCORES %1$d IS %2$d.\n", score, result[1]));
			}
			out.append(separator);
			out.append("\n");
		}
		out.append("END OF OUTPUT");
		System.out.println(out.toString());
		scanner.close();
	}
	
	public static int[] countPermutationsAndCombinations(int currentScore){
		List<Integer> possibleScores = buildPossibleScores();
		int nPermutations = 0;
		Set<String> combinations = new HashSet<String>(); 
		for(int attempt1 : possibleScores){
			for(int attempt2 : possibleScores){
				for(int attempt3 : possibleScores){
					if(currentScore - attempt1 - attempt2 - attempt3 == 0){
						nPermutations++;
						int[] permutation = new int[]{attempt1, attempt2, attempt3};
						Arrays.sort(permutation);
						String candidate = permutation[0] + " " + permutation[1] + " " + permutation[2];
						if(!combinations.contains(candidate)) combinations.add(candidate);
					}
				}
			}
		}
		return new int[]{combinations.size(), nPermutations};
	}
	
	public static List<Integer> buildPossibleScores(){
		Set<Integer> scores = new HashSet<Integer>();
		for(int score = 0; score <= 20; score++){
			scores.add(score); // singles
		}
		scores.add(50); // bull's eye
		for(int score = 2; score <= 40; score += 2){
			scores.add(score); // doubles
		}
		for(int score = 3; score <= 60; score += 3){
			scores.add(score); // triples
		}
		return new ArrayList<Integer>(scores);
	}
}
