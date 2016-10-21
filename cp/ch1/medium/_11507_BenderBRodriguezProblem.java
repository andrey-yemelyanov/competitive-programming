package helvidios.cp.ch1.medium;

import java.util.Scanner;

public class _11507_BenderBRodriguezProblem {
	private static final String Z_POS = "+z";
	private static final String Z_NEG = "-z";
	private static final String X_POS = "+x";
	private static final String X_NEG = "-x";
	private static final String Y_POS = "+y";
	private static final String Y_NEG = "-y";
	private static final String NO_ACTION = "No";
	
	public static void main(String... args){
		String data = "3\r\n" + 
				"+z -z\r\n" + 
				"3\r\n" + 
				"+z +y\r\n" + 
				"2\r\n" + 
				"+z\r\n" + 
				"4\r\n" + 
				"+z +y +z\r\n" + 
				"5\r\n" + 
				"No +z No No\r\n" + 
				"0";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int wireLength = scanner.nextInt();
			if(wireLength == 0) break;
			
			String[] decisions = new String[wireLength - 1];
			for(int decision = 0; decision < decisions.length; decision++){
				decisions[decision] = scanner.next();
			}
			
			System.out.println(bendWire(wireLength, decisions));
		}
		scanner.close();
	}
	
	private static String bendWire(int wireLength, String[] decisions){
		String[] wire = new String[wireLength];
		for(int segment = 0; segment < wire.length; segment++)
			wire[segment] = X_POS;
		
		for(int decision = 0, i = wireLength - 1; i > 0 && decision < decisions.length; i--, decision++){
			if(!decisions[decision].equals(NO_ACTION)){
				wire[wireLength - 1] = getNewCurrentDirection(decisions[decision], wire[wireLength - 1]);
			}
		}
		
		return wire[wireLength - 1];
	}
	
	private static String getNewCurrentDirection(String newDirection, String currentDirection){
		switch (newDirection) {
			case Y_POS:
				if(currentDirection.equals(X_POS)) return Y_POS;
				if(currentDirection.equals(X_NEG)) return Y_NEG;
				if(currentDirection.equals(Y_POS)) return X_NEG;
				if(currentDirection.equals(Y_NEG)) return X_POS;
				return currentDirection;
			case Y_NEG:
				if(currentDirection.equals(X_POS)) return Y_NEG;
				if(currentDirection.equals(X_NEG)) return Y_POS;
				if(currentDirection.equals(Y_POS)) return X_POS;
				if(currentDirection.equals(Y_NEG)) return X_NEG;
				return currentDirection;
			case Z_POS:
				if(currentDirection.equals(X_POS)) return Z_POS;
				if(currentDirection.equals(X_NEG)) return Z_NEG;
				if(currentDirection.equals(Z_POS)) return X_NEG;
				if(currentDirection.equals(Z_NEG)) return X_POS;
				return currentDirection;
			case Z_NEG:
				if(currentDirection.equals(X_POS)) return Z_NEG;
				if(currentDirection.equals(X_NEG)) return Z_POS;
				if(currentDirection.equals(Z_POS)) return X_POS;
				if(currentDirection.equals(Z_NEG)) return X_NEG;
				return currentDirection;
			default: return currentDirection;
		}
	}
}
