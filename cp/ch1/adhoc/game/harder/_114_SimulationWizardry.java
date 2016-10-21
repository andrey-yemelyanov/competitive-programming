package helvidios.cp.ch1.adhoc.game.harder;

import java.util.Scanner;

public class _114_SimulationWizardry {
	public static void main(String... args){
		String data = "4 4\r\n" + 
				"0\r\n" + 
				"2\r\n" + 
				"2 2 1 0\r\n" + 
				"3 3 1 0\r\n" + 
				"2 3 1 1\r\n" + 
				"2 3 1 2\r\n" + 
				"2 3 1 3\r\n" + 
				"2 3 1 4\r\n" + 
				"2 3 1 5\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		int wallCost = scanner.nextInt();
		int nBumpers = scanner.nextInt();
		int[][] bumpers = new int[nBumpers][4];
		for(int i = 0; i < nBumpers; i++){
			bumpers[i][0] = scanner.nextInt();
			bumpers[i][1] = scanner.nextInt();
			bumpers[i][2] = scanner.nextInt();
			bumpers[i][3] = scanner.nextInt();
		}
		int[][] surface = new int[n+1][m+1];
		for(int i = 0; i < surface.length; i++){
			for(int j = 0; j < surface[0].length; j++){
				surface[i][j] = -1;
			}
		}
		for(int i = 0; i < bumpers.length; i++){
			surface[bumpers[i][1]][bumpers[i][0]] = i; 
		}
		
		int total = 0;
		StringBuilder builder = new StringBuilder();
		while(scanner.hasNext()){
			int[] ball = new int[4];
			ball[0] = scanner.nextInt();
			ball[1] = scanner.nextInt();
			ball[2] = scanner.nextInt();
			ball[3] = scanner.nextInt();
			int score = simulate(surface, bumpers, wallCost, ball);
			total += score;
			builder.append(score + "\n");
		}
		builder.append(total + "\n");
		System.out.print(builder.toString());
		scanner.close();
	}
	
	static int simulate(int[][] surface, int[][] bumpers, int wallCost, int[] ball){
		int totalScore = 0;
		int ballLifetime = ball[3];
		while(ballLifetime-- > 0){
			// advance ball one step
			int[] previousBallPosition = new int[]{ball[0], ball[1]};
			takeStep(ball);
			// check if we hit an obstacle, rebound if so
			if(isBallOnWall(surface, ball)){
				ballLifetime -= wallCost;
				rebound(ball, previousBallPosition);
			}
			else{
				int[] bumper = isBallOnBumper(surface, bumpers, ball);
				if(bumper != null){
					if(ballLifetime > 0) {
						totalScore += bumper[2];
						ballLifetime -= bumper[3];
					}
					rebound(ball, previousBallPosition);
				}
			}	
		}
		return totalScore;
	}
	
	static void rebound(int[] ball, int[] previousBallPosition){
		// restore ball's previous position
		ball[0] = previousBallPosition[0];
		ball[1] = previousBallPosition[1];
		// set a new direction - clockwise (right) turn 90 degrees
		ball[2] = (ball[2] + 3) % 4;
	}
	
	static boolean isBallOnWall(int[][] surface, int[] ball){
		// see if the ball has hit a wall
		if(ball[0] == 1 || ball[0] == surface.length - 1
			|| ball[1] == 1 || ball[1] == surface[0].length - 1)
			return true;
		return false;
	}
	
	static int[] isBallOnBumper(int[][] surface, int[][] bumpers, int[] ball){
		// see if the ball has hit a bumper
		int bumperIndex = surface[ball[1]][ball[0]];
		if(bumperIndex != -1){
			int[] bumper = bumpers[bumperIndex];
			return bumper;
		}
		
		return null;
	}
	
	static void takeStep(int[] ball){
		int direction = ball[2];
		switch (direction) {
			case 0: // right
				ball[0]++;
				break;
			case 1: // up
				ball[1]++;
				break;
			case 2: // left
				ball[0]--;
				break;
			default: // down
				ball[1]--;
				break;
		}
	}
}
