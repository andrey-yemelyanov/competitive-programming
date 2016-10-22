package helvidios.cp.ch1.adhoc.reallifeharder;

import java.util.Scanner;

public class _608_CounterfeitDollar {
	public static final String UNKNOWN = "unknown";
	public static final String FAIR = "fair";
	public static final String HEAVY = "heavy";
	public static final String LIGHT = "light";
	public static final String EVEN = "even";
	public static final String UP = "up";
	public static final String DOWN = "down";
	
	public static void main(String... args){
		String data = "1\r\n" + 
				"ABCD EFGH even\r\n" + 
				"ABCI EFJK up\r\n" + 
				"ABIJ EFGH even\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		scanner.nextLine();
		while (nTestCases-- > 0) {
			String[][] weighings = new String[3][3];
			weighings[0] = scanner.nextLine().split(" ");
			weighings[1] = scanner.nextLine().split(" ");
			weighings[2] = scanner.nextLine().split(" ");
			String[] coins = new String[12];
			for(int i = 0; i < coins.length; i++){
				coins[i] = UNKNOWN;
			}
			
			int index = findCounterfeitCoin(coins, weighings);
			System.out.printf("%1$c is the counterfeit coin and it is %2$s.\n", coin(index), coins[index]);
		}
		
		scanner.close();
	}
	
	public static int findCounterfeitCoin(String[] coins, String[][] weighings){
		for(int i = 0; i < weighings.length; i++){
			char[] leftSide = weighings[i][0].toCharArray();
			char[] rightSide = weighings[i][1].toCharArray();
			String weighingResult = weighings[i][2];
			analyzeWeighing(leftSide, rightSide, weighingResult, coins);
		}
		
		for(int i = 0; i < coins.length; i++){
			if(coins[i].equals(HEAVY) || coins[i].equals(LIGHT)){
				return i;
			}
		}
		
		throw new RuntimeException("Unable to find counterfeit coin.");
	}
	
	static void analyzeWeighing(
			char[] leftSide, 
			char[] rightSide, 
			String weighingResult, 
			String[] coins){
		if(weighingResult.equals(EVEN)){
			for(char coin : leftSide){
				setStatus(coin, coins, FAIR);
			}
			for(char coin : rightSide){
				setStatus(coin, coins, FAIR);
			}
		}
		else if(weighingResult.equals(UP)){
			// all coins not on scale become FAIR
			for (int i = 0; i < coins.length; i++) {
				char coin = coin(i);
				if(!contains(coin, leftSide) && !contains(coin, rightSide)){
					setStatus(coin, coins, FAIR);
				}
			}
			
			for(char coin : leftSide){
				if(getStatus(coin, coins).equals(UNKNOWN)){
					setStatus(coin, coins, HEAVY);
				}
				else if(getStatus(coin, coins).equals(LIGHT)){
					setStatus(coin, coins, FAIR);
				}
			}
			
			for(char coin : rightSide){
				if(getStatus(coin, coins).equals(UNKNOWN)){
					setStatus(coin, coins, LIGHT);
				}
				else if(getStatus(coin, coins).equals(HEAVY)){
					setStatus(coin, coins, FAIR);
				}
			}
		}
		else if(weighingResult.equals(DOWN)){
			// all coins not on scale become FAIR
			for (int i = 0; i < coins.length; i++) {
				char coin = coin(i);
				if(!contains(coin, leftSide) && !contains(coin, rightSide)){
					setStatus(coin, coins, FAIR);
				}
			}
						
			for(char coin : leftSide){
				if(getStatus(coin, coins).equals(UNKNOWN)){
					setStatus(coin, coins, LIGHT);
				}
				else if(getStatus(coin, coins).equals(HEAVY)){
					setStatus(coin, coins, FAIR);
				}
			}
			
			for(char coin : rightSide){
				if(getStatus(coin, coins).equals(UNKNOWN)){
					setStatus(coin, coins, HEAVY);
				}
				else if(getStatus(coin, coins).equals(LIGHT)){
					setStatus(coin, coins, FAIR);
				}
			}
		}
	}
	
	static boolean contains(char coin, char[] coins){
		for(char c : coins){
			if(c == coin) return true;
		}
		return false;
	}
	
	static int code(char coin){
		return coin - 'A';
	}
	
	static char coin(int code){
		return (char)(code + 'A');
	}
	
	static String getStatus(char coin, String[] coins){
		return coins[code(coin)];
	}
	
	static void setStatus(char coin, String[] coins, String status){
		coins[code(coin)] = status;
	}
}
