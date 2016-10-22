package helvidios.cp.ch1.adhoc.card;

import java.util.Scanner;

class Pair{
	public Pair(int princessCard, int princeCard){
		this.princeCard = princeCard;
		this.princessCard = princessCard;
	}
	public int princessCard;
	public int princeCard;
}
class Tournament{
	public Tournament(Pair pair1, Pair pair2){
		this.pair1 = pair1;
		this.pair2 = pair2;
	}
	public Pair pair1;
	public Pair pair2;
}

public class _12247_Jollo {
	public static void main(String... args){
		String data = "3 5 7 6 8\r\n" +  
				"11 17 4 16 39\r\n" + 
				"32 18 14 8 30\r\n" + 
				"50 46 51 23 5\r\n" + 
				"44 32 45 40 21\r\n" + 
				"34 43 52 37 27\r\n" + 
				"19 27 23 18 52\r\n" + 
				"1 4 17 32 47\r\n" + 
				"31 12 41 44 47\r\n" + 
				"44 35 19 12 22\r\n" + 
				"16 45 40 15 5\r\n" + 
				"42 34 32 40 27\r\n" + 
				"31 41 6 23 20\r\n" + 
				"1 6 10 40 49\r\n" + 
				"49 7 31 44 18\r\n" + 
				"28 35 38 15 49\r\n" + 
				"8 4 7 14 3\r\n" + 
				"27 42 3 9 6\r\n" + 
				"23 10 39 8 25\r\n" + 
				"11 33 32 17 52\r\n" + 
				"25 44 34 11 6\r\n" + 
				"6 29 37 12 26\r\n" + 
				"3 47 5 10 8\r\n" + 
				"7 12 25 38 48\r\n" + 
				"6 2 24 13 18\r\n" + 
				"18 9 51 2 48\r\n" + 
				"52 33 43 5 18\r\n" + 
				"26 39 5 27 24\r\n" + 
				"51 42 1 50 2\r\n" + 
				"47 20 14 31 51\r\n" + 
				"13 49 35 11 26\r\n" + 
				"7 11 34 49 43\r\n" + 
				"51 50 29 31 52\r\n" + 
				"29 7 8 12 15\r\n" + 
				"33 29 2 22 36\r\n" + 
				"28 34 8 10 21\r\n" + 
				"33 16 31 43 40\r\n" + 
				"49 17 14 1 48\r\n" + 
				"13 29 2 20 40\r\n" + 
				"44 26 25 47 9\r\n" + 
				"7 3 37 36 24\r\n" + 
				"20 11 34 27 21\r\n" + 
				"52 4 18 13 32\r\n" + 
				"41 25 9 42 21\r\n" + 
				"24 33 22 25 3\r\n" + 
				"49 15 52 9 22\r\n" + 
				"32 7 20 16 24\r\n" + 
				"11 15 18 16 5\r\n" + 
				"8 48 19 37 33\r\n" + 
				"7 33 23 6 41\r\n" + 
				"33 50 30 13 35\r\n" + 
				"41 23 50 34 14\r\n" + 
				"30 42 9 48 26\r\n" + 
				"17 30 34 39 35\r\n" + 
				"23 6 16 18 25\r\n" + 
				"43 9 24 23 19\r\n" + 
				"38 3 16 15 43\r\n" + 
				"51 31 42 24 12\r\n" + 
				"13 34 52 4 51\r\n" + 
				"25 46 35 24 45\r\n" + 
				"1 9 23 17 51\r\n" + 
				"16 33 18 45 48\r\n" + 
				"18 50 28 48 9\r\n" + 
				"22 36 42 49 39\r\n" + 
				"17 21 9 51 44\r\n" + 
				"1 52 28 23 44\r\n" + 
				"2 12 34 6 14\r\n" + 
				"18 34 39 45 29\r\n" + 
				"24 42 40 13 39\r\n" + 
				"2 5 35 10 32\r\n" + 
				"3 38 7 6 9\r\n" + 
				"50 8 20 7 13\r\n" + 
				"27 49 29 42 18\r\n" + 
				"5 13 7 20 1\r\n" + 
				"21 22 6 4 7\r\n" + 
				"37 34 45 19 39\r\n" + 
				"1 16 46 48 50\r\n" + 
				"35 5 30 44 12\r\n" + 
				"25 19 37 2 8\r\n" + 
				"20 5 23 1 36\r\n" + 
				"5 13 17 49 31\r\n" + 
				"0 0 0 0 0";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int[] princess = new int[3];
			princess[0] = scanner.nextInt();
			princess[1] = scanner.nextInt();
			princess[2] = scanner.nextInt();
			if(princess[0] == 0) break;
			int[] prince = new int[2];
			prince[0] = scanner.nextInt();
			prince[1] = scanner.nextInt();
			System.out.println(getBestCard(princess, prince));
		}
		scanner.close();
	}
	
	private static int getBestCard(int[] princess, int[] prince){
		// generate all 6 possible card tournaments (3 from princess x 2 from prince)
		// and analyze each one (each tournament consists of 2 rounds):
		// if there is at least one tournament where prince loses 2 rounds then return -1
		// otherwise find a minimum card that will guarantee a win
		Tournament[] tournaments = generateTournaments(princess, prince);
		int bestCard = getLowestCardNotInPlay(princess, prince);
		for(int i = 0; i < tournaments.length; i++){
			if(getNumberOfWinningRounds(tournaments[i]) == 0) // win is impossible
				return -1;
			if(getNumberOfWinningRounds(tournaments[i]) == 1){ // win is possible, try to find a card that makes it guaranteed
				int winningCard = getMinimumWinningCard(tournaments[i], princess, prince);
				if(winningCard == Integer.MAX_VALUE) return -1; // remaining princess card is the highest, no win is possible
				if(winningCard > bestCard) bestCard = winningCard;
			}
		}
		return bestCard;
	}
	
	private static int getLowestCardNotInPlay(int[] princess, int[] prince){
		for(int card = 1; card <= 52; card++){
			if(!isCardInPlay(card, princess, prince))
				return card;
		}
		return 0;
	}
	
	private static int getNextBestCardAfter(int card, int[] princess, int[] prince){
		for(int i = card + 1; i <= 52; i++){
			if(!isCardInPlay(i, princess, prince))
				return i;
		}
		return Integer.MAX_VALUE;
	}
	
	private static int getMinimumWinningCard(Tournament tournament, int[] princess, int[] prince){
		int remainingPrincessCard = 0;
		for(int i = 0; i < princess.length; i++){
			if(princess[i] != tournament.pair1.princessCard 
					&& princess[i] != tournament.pair2.princessCard){
				remainingPrincessCard = princess[i];
				break;
			}
		}
		
		return getNextBestCardAfter(remainingPrincessCard, princess, prince);
	}
	
	private static boolean isCardInPlay(int card, int[] princess, int[] prince){
		return card == princess[0] || 
			   card == princess[1] || 
			   card == princess[2] || 
			   card == prince[0] || 
			   card == prince[1];
	}
	
	private static Tournament[] generateTournaments(int[] princess, int[] prince){
		Tournament[] tournaments = new Tournament[6];
		int index = 0;
		for(int i = 0; i < princess.length; i++){
			Pair pair1 = new Pair(princess[i], prince[0]);
			for(int j = 0; j < princess.length; j++){
				if(j != i){
					Pair pair2 = new Pair(princess[j], prince[1]);
					tournaments[index++] = new Tournament(pair1, pair2);
				}
			}
		}
		return tournaments;
	}
	
	private static int getNumberOfWinningRounds(Tournament tournament){
		int count = 0;
		if(princeWins(tournament.pair1)) count++;
		if(princeWins(tournament.pair2)) count++;
		return count;
	}
	
	private static boolean princeWins(Pair pair){
		return pair.princeCard > pair.princessCard;
	}
}
