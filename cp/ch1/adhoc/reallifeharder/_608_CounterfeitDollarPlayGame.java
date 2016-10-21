package helvidios.cp.ch1.adhoc.reallifeharder;

import java.util.Random;
import java.util.Scanner;

public class _608_CounterfeitDollarPlayGame {
	public static void main(String... args){
		Scanner scanner = new Scanner(System.in);
		String coins = "ABCDEFGHIJKL";
		Random randomizer= new Random();
		double[] masses = new double[]{0.5,1.5};
		
		while (true) {
			char counterfeit = coins.charAt(randomizer.nextInt(coins.length()));
			double mass = masses[randomizer.nextInt(2)];
			int weighings = 3;
			while(weighings-- > 0){
				System.out.println("Enter weighing: ");
				String[] weighing = scanner.nextLine().split(" ");
				double massLeft = calculateMass(counterfeit, mass, weighing[0]);
				double massRight = calculateMass(counterfeit, mass, weighing[1]);
				if(massLeft < massRight) System.out.println("Down");
				else if(massLeft > massRight) System.out.println("Up");
				else System.out.println("Even");
			}
			System.out.println("Which coin is counterfeit? ");
			String[] response = scanner.nextLine().split(" ");
			String counterfeitWeight = mass == 0.5 ? "light" : "heavy"; 
			if(response[0].toCharArray()[0] == counterfeit && response[1].toLowerCase().equals(counterfeitWeight)){
				System.out.println("CORRECT!!!");
			}else{
				System.out.println("WRONG!!! Correct is " + counterfeit + " " + counterfeitWeight);
			}
			
			System.out.println("Play again (Y/N)?");
			String answer = scanner.nextLine();
			if(answer.trim().toLowerCase().equals("n")) break;
		}
		
		scanner.close();
	}
	
	static double calculateMass(char counterfeit, double counterfeitMass, String coins){
		double mass = 0;
		for(int c = 0; c < coins.length(); c++){
			if(coins.charAt(c) == counterfeit) mass += counterfeitMass;
			else mass++;
		}
		return mass;
	}
}
