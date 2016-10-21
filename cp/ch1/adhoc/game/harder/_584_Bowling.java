package helvidios.cp.ch1.adhoc.game.harder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class _584_Bowling {
	public static final int STRIKE = 10;
	public static final int SPARE = 11;
	public static void main(String... args) throws NumberFormatException, IOException{
		String data = "1 0 1 / 2 2 X 3 3 X 1 / 3 / X 1 2\r\n" + 
				"1 0 1 / 2 2 X 3 3 X 1 / 3 / 1 / X 8 0\r\n" + 
				"1 0 1 / 2 2 X 3 3 X 1 / 3 / 1 / 8 / 9\r\n" + 
				"X X X X X X X X X X X X\r\n" + 
				"1 / 3 / 5 / X X X 3 / 5 / 1 2 8 / 2\r\n" + 
				"X 1 / X 1 / X 1 / X 1 / X 1 / X\r\n" + 
				"1 / X 1 / X 1 / X 1 / X 1 / X 9 / 0\r\n" + 
				"1 / X 1 / X 1 / X 1 / X 1 / X 2 / 0\r\n" + 
				"3 / 4 4 8 / 9 2 7 / X X 2 / 9 / X X X   \r\n" + 
				"3 / 4 4 8 / 9 2 7 / X X 2 / 9 / X 4 / \r\n" + 
				"Game Over";
		int[] rolls;
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = reader.readLine()) != null) {
			if(line.equals("Game Over")) break;
			String[] tokens = line.split(" ");
			rolls = new int[tokens.length];
			for(int i = 0; i < tokens.length; i++){
				if(tokens[i].equals("/")){
					rolls[i] = SPARE;
				}else if(tokens[i].equals("X")){
					rolls[i] = STRIKE;
				}else{
					rolls[i] = Integer.parseInt(tokens[i]);
				}
			}
			System.out.println(getScore(rolls));
		}
	}
	
	public static int getScore(int[] rolls){
		int score = 0;
		for(int i = 0; i < rolls.length; i++){
			if(getFrameNumber(rolls, i) <= STRIKE){
				if(rolls[i] == STRIKE){
					score += STRIKE;
					// add score of the next two rolls
					if(i + 1 < rolls.length){
						score += rolls[i + 1];
					}
					
					if(i + 2 < rolls.length){
						if(rolls[i + 2] == SPARE){
							score += STRIKE - rolls[i + 1];
						}else{
							score += rolls[i + 2];
						}
					}
				}
				else if(rolls[i] == SPARE){
					score += STRIKE - rolls[i - 1];
					if(i + 1 < rolls.length){
						// add score of the next roll
						score += rolls[i + 1];
					}
				}
				else{
					score += rolls[i];
				}
			}
		}
		return score;
	}
	
	public static int getFrameNumber(int[] rolls, int index){
		int frame = 0;
		boolean inFrame = false;
		for(int i = 0; i < rolls.length; i++){
			if(rolls[i] == STRIKE) {
				frame++;
				inFrame = false;
			}
			else if(rolls[i] == SPARE){
				inFrame = false;
			}
			else{
				if(!inFrame){
					frame++;
					inFrame = true;
				}else{
					inFrame = false;
				}
			}
			
			if(i == index) return frame;
		}
		
		return frame;
	}
}
