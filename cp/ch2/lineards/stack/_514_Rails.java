package helvidios.cp.ch2.lineards.stack;

import java.util.Scanner;
import java.util.Stack;

public class _514_Rails {
	public static void main(String... args){
		String data = "5\r\n" + 
				"1 2 3 4 5\r\n" + 
				"5 4 1 2 3\r\n" + 
				"0\r\n" + 
				"6\r\n" + 
				"6 5 4 3 2 1\r\n" + 
				"2 1 3 4 6 5\n" +
				"0\r\n" + 
				"0\r\n" + 
				"\r\n" + 
				"";
		String data2 = "5\n"+
				"1 2 3 4 5\n"+
				"1 2 5 3 4\n"+
				"0\n0\n";
		String data3 = "10\n5 6 4 8 7 3 2 9 1 10\n0\n0";
		Scanner scanner = new Scanner(data3);
		while(true){
			int n = scanner.nextInt();
			if(n == 0) break;
			read:while(true){
				int[] perm = new int[n];
				for(int i = 0; i < n; i++){
					perm[i] = scanner.nextInt();
					if(perm[i] == 0) break read;
				}
				boolean possible = permutationPossible(perm);
				if(possible) System.out.println("Yes");
				else System.out.println("No");
			}
			System.out.println();
		}
		scanner.close();
	}
	
	public static boolean permutationPossible(int[] permutation){
		Stack<Integer> stackA = new Stack<Integer>();
		Stack<Integer> stationStack = new Stack<Integer>();
		
		for(int i = permutation.length; i > 0; i--){
			stackA.push(i);
		}
		
		int coachIndex = 0;
		while (coachIndex < permutation.length) {
			int coach = permutation[coachIndex];
			if(!stationStack.isEmpty() && stationStack.peek() == coach){
				stationStack.pop();
				coachIndex++;
			}else if(!stackA.isEmpty() && stackA.peek() == coach){
				stackA.pop();
				coachIndex++;
			}else if(stackA.isEmpty() || coach < stackA.peek()){
				return false;
			}else{
				while (stackA.peek() != coach){
					stationStack.push(stackA.pop());
				}
				stackA.pop();
				coachIndex++;
			}
		}
		
		return true;
	}
}
