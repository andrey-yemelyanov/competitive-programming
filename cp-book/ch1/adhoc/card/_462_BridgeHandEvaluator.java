package helvidios.cp.ch1.adhoc.card;

import java.util.Scanner;

public class _462_BridgeHandEvaluator {
	private static char[] values = new char[]{'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
	private static char[] suits = new char[]{'C','D','H','S'};
	
	private static int encode(char value, char suit){
		for(int i = 0; i < values.length; i++){
			if(values[i] == value){
				for(int j = 0; j < suits.length; j++){
					if(suits[j] == suit){
						return i*suits.length + j;
					}
				}
			}
		}
		throw new RuntimeException(String.format(
				"Bad card data: value = %1$c, suit = %2$c", 
				value, suit));
	}
	
	private static char suit(int card){
		return suits[card % suits.length];
	}
	
	private static char value(int card){
		return values[card / suits.length];
	}
	
	public static void main(String... args){
		String data = "KS QS TH 8H 4H AC QC TC 5C KD QD JD 8D\r\n" + 
				"AC 3C 4C AS 7S 4S AD TD 7D 5D AH 7H 5H\r\n"; 
		Scanner scanner = new Scanner(data);
		final int nCards = 13;
		while(scanner.hasNext()){
			String[] cards = new String[nCards];
			for(int card = 0; card < nCards; card++)
				cards[card] = scanner.next();
			System.out.println(evaluate(cards));
		}
		scanner.close();
	}
	
	private static String evaluate(String[] hand){
		int[] cards = new int[hand.length];
		for(int c = 0; c < hand.length; c++){
			cards[c] = encode(hand[c].charAt(0), hand[c].charAt(1));
		}
		
		if(pass(cards)) return "PASS";
		if(noTrump(cards)) return "BID NO-TRUMP";
		return String.format("BID %1$c", bidSuit(cards));
	}
	
	private static char bidSuit(int[] hand){
		int nSpades = countCardsBySuit(hand, 'S');
		int nHearts = countCardsBySuit(hand, 'H');
		int nDiamonds = countCardsBySuit(hand, 'D');
		int nClubs = countCardsBySuit(hand, 'C');
		int max = Math.max(Math.max(nSpades, nHearts), Math.max(nDiamonds, nClubs));
		if(nSpades == max) return 'S';
		if(nHearts == max) return 'H';
		if(nDiamonds == max) return 'D';
		return 'C';
	}
	
	/*
	 * One may open bidding in "no trump" if the hand 
	 * evaluates to 16 or more points ignoring rules 5, 6, and 7 
	 * and if all four suits are stopped. A no trump bid is always preferred 
	 * over a suit bid when both are possible.
	 */
	private static boolean noTrump(int[] hand){
		int totalPoints = 0;
		totalPoints += applyRule1(hand);
		totalPoints += applyRule2(hand);
		totalPoints += applyRule3(hand);
		totalPoints += applyRule4(hand);
		return 	totalPoints >= 16 
				&& isSuitStopped(hand, 'C') 
				&& isSuitStopped(hand, 'D') 
				&& isSuitStopped(hand, 'H')
				&& isSuitStopped(hand, 'S');
	}
	
	/*
	 * If the hand evaluates to fewer than 14 points, then the player must pass.
	 */
	private static boolean pass(int[] hand){
		int totalPoints = 0;
		totalPoints += applyRule1(hand);
		totalPoints += applyRule2(hand);
		totalPoints += applyRule3(hand);
		totalPoints += applyRule4(hand);
		totalPoints += applyRule5(hand);
		totalPoints += applyRule6(hand);
		totalPoints += applyRule7(hand);
		return totalPoints < 14;
	}
	
	/*
	 * A suit is stopped if it contains an ace, or if it contains a king 
	 * and at least one other card, or if it contains a queen and 
	 * at least two other cards. 
	 */
	private static boolean isSuitStopped(int[] hand, char suit){
		boolean containsAce = hasCard(hand, 'A', suit);
		boolean containsKing = hasCard(hand, 'K', suit) && countCardsBySuit(hand, suit) >= 2;
		boolean containsQueen = hasCard(hand, 'Q', suit) && countCardsBySuit(hand, suit) >= 3;
		return containsAce || containsKing || containsQueen;
	}
	
	private static boolean hasCard(int[] hand, char value, char suit){
		for(int card : hand){
			if(value(card) == value && suit(card) == suit)
				return true;
		}
		return false;
	}
	
	private static int countCardsBySuit(int[] hand, char suit){
		int count = 0;
		for(int card : hand)
			if(suit(card) == suit) count++;
		return count;
	}
	
	/*
	 * Each ace counts 4 points. 
	 * Each king counts 3 points. 
	 * Each queen counts 2 points. 
	 * Each jack counts one point.
	 */
	private static int applyRule1(int[] hand){
		int score = 0;
		for(int card : hand){
			if(value(card) == 'A') score += 4;;
			if(value(card) == 'K') score += 3;
			if(value(card) == 'Q') score += 2;
			if(value(card) == 'J') score += 1;
		}
		return score;
	}
	
	/*
	 * Subtract a point for any king of a suit in 
	 * which the hand holds no other cards.
	 */
	private static int applyRule2(int[] hand){
		int score = 0;
		for(int card : hand){
			if(value(card) == 'K'){
				if(countCardsBySuit(hand, suit(card)) == 1) score--;
			}
		}
		return score;
	}
	
	/*
	 * Subtract a point for any queen in a suit in which 
	 * the hand holds only zero or one other cards.
	 */
	private static int applyRule3(int[] hand){
		int score = 0;
		for(int card : hand){
			if(value(card) == 'Q'){
				if(countCardsBySuit(hand, suit(card)) <= 2)
					score--;
			}
		}
		return score;
	}
	
	/*
	 * Subtract a point for any jack in a suit in which the hand 
	 * holds only zero, one, or two other cards.
	 */
	private static int applyRule4(int[] hand){
		int score = 0;
		for(int card : hand){
			if(value(card) == 'J'){
				if(countCardsBySuit(hand, suit(card)) <= 3)
					score--;
			}
		}
		return score;
	}
	
	/*
	 * Add a point for each suit in which the hand contains exactly two cards.
	 */
	private static int applyRule5(int[] hand){
		int score = 0;
		for(char suit : suits){
			if(countCardsBySuit(hand, suit) == 2)
				score++;
		}
		return score;
	}
	
	/*
	 * Add two points for each suit in which the hand contains exactly one card.
	 */
	private static int applyRule6(int[] hand){
		int score = 0;
		for(char suit : suits){
			if(countCardsBySuit(hand, suit) == 1)
				score += 2;
		}
		return score;
	}
	
	/*
	 * Add two points for each suit in which the hand contains no cards.
	 */
	private static int applyRule7(int[] hand){
		int score = 0;
		for(char suit : suits){
			if(countCardsBySuit(hand, suit) == 0)
				score += 2;
		}
		return score;
	}
}
