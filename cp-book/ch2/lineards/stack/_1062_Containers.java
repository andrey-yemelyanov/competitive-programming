package helvidios.cp.ch2.lineards.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class _1062_Containers {
	public static void main(String... args){
		String data = "A \r\n" + 
				"CBACBACBACBACBA \r\n" + 
				"CCCCBBBBAAAA \r\n" + 
				"ACMICPC \r\n" + 
				"end\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int caseNum = 0;
		while(true){
			String containers = scanner.next();
			if(containers.equals("end")) break;
			System.out.printf("Case %1$d: %2$d\n", ++caseNum, minNumberOfStacks(containers));
		}
		scanner.close();
	}
	
	public static int minNumberOfStacks(String containers){
		List<Stack<Character>> stacks = new ArrayList<Stack<Character>>();
		for(char container : containers.toCharArray()){
			Stack<Character> stack = pickBestStack(container, stacks);
			if(stack == null){
				stack = new Stack<Character>();
				stacks.add(stack);
			}
			stack.push(container);
		}
		return stacks.size();
	}
	
	static Stack<Character> pickBestStack(char container, List<Stack<Character>> stacks){
		for(Stack<Character> stack : stacks){
			if(stack.peek() >= container) return stack;
		}
		return null;
	}
}
