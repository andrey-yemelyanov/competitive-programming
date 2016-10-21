package helvidios.cp.ch3.completesearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _10344_23OutOf5 {
	public static void main(String... args){
		String data = "1 1 1 1 1\r\n" + 
				"1 2 3 4 5\r\n" + 
				"2 3 5 7 11\r\n" + 
				"0 0 0 0 0\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		StringBuilder builder = new StringBuilder();
		while(scanner.hasNext()){
			int operand1 = scanner.nextInt();
			int operand2 = scanner.nextInt();
			int operand3 = scanner.nextInt();
			int operand4 = scanner.nextInt();
			int operand5 = scanner.nextInt();
			if(operand1 == 0 && operand2 == 0 && operand3 == 0 && operand4 == 0 && operand5 == 0) break;
			List<Integer> operands = Arrays.asList(operand1, operand2, operand3, operand4, operand5);
			int[] expression = new int[9];
			char[] operators = new char[]{'-','+','*'};
			boolean possible = solve(0, expression, operands, operators);
			if(possible) builder.append("Possible");
			else builder.append("Impossible");
			builder.append("\n");
		}
		System.out.print(builder.toString());
		scanner.close();
	}
	
	static boolean solve(int i, int[] expression, List<Integer> operands, char[] operators){
		if(i == expression.length){
			return eval(expression, operators) == 23;
		}
		
		if(i % 2 == 0){// operand
			for(int operand = 0; operand < operands.size(); operand++){
				expression[i] = operands.get(operand);
				List<Integer> remainingOperands = new ArrayList<Integer>(operands);
				remainingOperands.remove(operand);
				if(solve(i + 1, expression, remainingOperands, operators)) return true;
			}
		}else{// operator
			for(int operator = 0; operator < operators.length; operator++){
				expression[i] = operator;
				if(solve(i + 1, expression, operands, operators)) return true;
			}
		}
		
		return false;
	}
	
	static int eval(int[] expression, char[] operators){
		int result = eval(expression[0], expression[2], operators[expression[1]]);
		result = eval(result, expression[4], operators[expression[3]]);
		result = eval(result, expression[6], operators[expression[5]]);
		result = eval(result, expression[8], operators[expression[7]]);
		return result;
	}
	
	static int eval(int operand1, int operand2, char operator){
		switch(operator){
			case '+':
				return operand1 + operand2;
			case '-':
				return operand1 - operand2;
			case '*':
				return operand1 * operand2;
		}
		throw new RuntimeException("Unknown operator " + operator);
	}
}
