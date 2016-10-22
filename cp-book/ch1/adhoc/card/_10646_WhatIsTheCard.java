package helvidios.cp.ch1.adhoc.card;

import java.util.Scanner;

public class _10646_WhatIsTheCard {
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
	
	private static int gameValue(int card){
		if(value(card) >= '2' && value(card) <= '9')
			return Character.getNumericValue(value(card));
		return 10;
	}
	
	public static void main(String... args){
		String data = "2\r\n" + 
				"\r\n" + 
				"AC KC QC JC TC 9C 8C 7C 6C 5C 4C 3C 2C AD KD QD JD TD 9D 8D 7D 6D 5D 4D 3D 2D AH KH QH JH TH 9H 8H 7H 6H 5H 4H 3H 2H AS KS QS JS TS 9S 8S 7S 6S 5S 4S 3S 2S\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"AC KC QC JC TC 9C 8C 7C 6C 5C 4C 3C 2C AD KD QD JD TD 9D 8D 7D 6D 5D 4D 3D 2D AH KH QH JH TH 9H 8H 7H 6H 5H 4H 3H 2H AS KS QS JS TS 9S 8S 7S 6S 5S 4S 3S 2S\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		final int nCards = 52;
		int[] cards = new int[nCards];
		for(int test = 1; test <= nTestCases; test++){
			for(int card = 0; card < nCards; card++){
				String c = scanner.next();
				cards[card] = encode(c.charAt(0), c.charAt(1));
			}
			int card = getTargetCard(cards);
			System.out.printf("Case %d: %c%c\n", test, value(card), suit(card));
		}
		scanner.close();
	}
	
	private static int getTargetCard(int[] cards){
		int nTopCards = 25;
		// Take the top 25 cards of the pile in the hand
		int top = (cards.length - 1) - nTopCards;
		int y = 0;
		
		// Execute three times the following steps together
		for(int i = 0; i < 3; i++){
			// Take the top card of the cards of the pile and determine its value
			int x = gameValue(cards[top]);
			// Add X to Y.
			y += x;
			// Put this card and the top (10-X) cards of the pile away
			top -= (10 - x) + 1;
		}
		
		// At last put the 25 remaining cards in your hand on top of the pile. 
		// The task is to determine the Yth card from the pile, counted from the bottom.
		int index = -1;
		boolean isInTopPile = false;
		while (y-- > 0) {
			index++;
			if(!isInTopPile && index > top) {
				index = (cards.length - 1) - (nTopCards - 1);
				isInTopPile = true;
			}
		}
		
		return cards[index];
	}
}
