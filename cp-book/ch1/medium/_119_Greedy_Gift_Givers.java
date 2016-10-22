package helvidios.cp.ch1.medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class _119_Greedy_Gift_Givers {
	public static class Solver{
		private String[] people;
		private int[] amountsSpent;
		private boolean[][] giftMatrix;
		private int[] netResults;

		private BufferedReader bufferedReader;
		private BufferedReader getBufferedReader(InputStream inputStream){
			return new BufferedReader(new InputStreamReader(inputStream));
		}
		
		public Solver(InputStream inputStream){
			bufferedReader = getBufferedReader(inputStream);
		}
		
		public int[] getNetResults(){
			return netResults;
		}
		
		public String[] getPeople(){
			return people;
		}
		
		public int[] getAmountsSpent(){
			return amountsSpent;
		}
		
		public boolean[][] getGiftMatrix(){
			return giftMatrix;
		}
		
		/**
		 * Calculates the net gift-giving result for each member of the group.
		 */
		public void solve(){
			// for each member of the group
			for(int i = 0; i < giftMatrix.length; i++){
				// calculate how many friends person i will give gifts to
				int friendCount = 0;
				for(int j = 0; j < giftMatrix[i].length; j++){
					if(giftMatrix[i][j])
						friendCount++;
				}
				
				// gift amount that each friend will receive
				int giftAmount = divideMoneyEvenly(amountsSpent[i], friendCount);
				
				// total amount that person i will spend
				int totalAmountSpent = giftAmount * friendCount;

				// increment person i net result by amount that person i will keep
				netResults[i] += amountsSpent[i] - totalAmountSpent;
				
				// decrement person i net result by the value of total spent
				netResults[i] -= amountsSpent[i];
				
				// increment friends' net result by the value of giftAmount
				for(int j = 0; j < giftMatrix[i].length; j++){
					if(giftMatrix[i][j])
						netResults[j] += giftAmount;
				}
			}
		}
		
		private int divideMoneyEvenly(int amount, int friendCount){
			if(friendCount == 0) return 0;
			return amount / friendCount;
		}
		
		/**
		 * Returns net results for each member of the group.
		 */
		public String printResults(){
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < people.length; i++){
				builder.append(people[i]);
				builder.append(" ");
				builder.append(netResults[i]);
				if(i != people.length - 1)
					builder.append("\n");
			}
			return builder.toString();
		}
		
		/**
		 * Reads the next gift-giving group.
		 * @return true if a group is available, false otherwise.
		 * @throws IOException
		 */
		public boolean readData() throws IOException{
			String line = bufferedReader.readLine();
			if(line == null) return false;
			
			int peopleCount = Integer.parseInt(line);
			amountsSpent = new int[peopleCount];
			netResults = new int[peopleCount];
			giftMatrix = new boolean[peopleCount][peopleCount];
			people = bufferedReader.readLine().split("\\s+");
			for(int i = 0; i < peopleCount; i++)
				readGifts(bufferedReader.readLine().split("\\s+"));
			
			return true;
		}
		
		private void readGifts(String[] data){
			int personIndex = getPersonIndex(data[0]);
			amountsSpent[personIndex] = Integer.parseInt(data[1]);
			for(int i = 3; i < data.length; i++){
				giftMatrix[personIndex][getPersonIndex(data[i])] = true;
			}
		}
		
		private int getPersonIndex(String name){
			for(int i = 0; i < people.length; i++){
				if(people[i].equals(name))
					return i;
			}
			return -1;
		}
	}
	
	public static void main(String... args) throws IOException{
		Solver solver = new Solver(System.in);
		boolean firstGroup = true;
		while(solver.readData()){
			if(!firstGroup) System.out.println();
			solver.solve();
			System.out.println(solver.printResults());
			firstGroup = false;
		}
	}
}
