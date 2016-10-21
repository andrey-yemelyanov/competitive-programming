package helvidios.cp.ch1.adhoc.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class _170_ClockPatience {
	private static char[] values = new char[]{'A','2','3','4','5','6','7','8','9','T','J','Q','K'};
	private static char[] suits = new char[]{'C','D','H','S'};
	
	static int index(int code){
		return code / suits.length;
	}
	
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
		return values[index(card)];
	}
	
	static String displayCard(int card){
		return value(card) + "" + suit(card);
	}
	
	public static void main(String... args){
		String data = "TS QC 8S 8D QH 2D 3H KH 9H 2H TH KS KC\r\n" + 
				"9D JH 7H JD 2S QS TD 2C 4H 5H AD 4D 5D\r\n" + 
				"6D 4S 9S 5S 7S JS 8H 3D 8C 3S 4C 6S 9C\r\n" + 
				"AS 7C AH 6H KD JC 7D AC 5C TC QD 6C 3C\r\n" + 
				"AS 7C AH 6H KD JC 7D AC 5C TC QD 6C 3C\r\n" + 
				"TS QC 8S 8D QH 2D 3H KH 9H 2H TH KS KC\r\n" + 
				"6D 4S 9S 5S 7S JS 8H 3D 8C 3S 4C 6S 9C\r\n" + 
				"9D JH 7H JD 2S QS TD 2C 4H 5H AD 4D 5D\n"+
				"#\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(true){
			int[] deck = new int[52];
			String line = scanner.nextLine().trim();
			if(line.equals("#")) break;
			String[] line1 = line.split(" ");
			String[] line2 = scanner.nextLine().split(" ");
			String[] line3 = scanner.nextLine().split(" ");
			String[] line4 = scanner.nextLine().split(" ");
			int index = 0;
			for(int i = line4.length - 1; i >= 0; i--){
				deck[index++] = encode(line4[i].charAt(0), line4[i].charAt(1));
			}
			for(int i = line3.length - 1; i >= 0; i--){
				deck[index++] = encode(line3[i].charAt(0), line3[i].charAt(1));
			}
			for(int i = line2.length - 1; i >= 0; i--){
				deck[index++] = encode(line2[i].charAt(0), line2[i].charAt(1));
			}
			for(int i = line1.length - 1; i >= 0; i--){
				deck[index++] = encode(line1[i].charAt(0), line1[i].charAt(1));
			}
			int[] result = play(deck);
			System.out.printf("%1$02d,%2$s\n",result[0], displayCard(result[1]));
		}
		scanner.close();
	}
	
	public static int[] play(int[] deck){
		final int nPiles = 13;
		List<Stack<Integer>> piles = new ArrayList<Stack<Integer>>(nPiles);
		for(int i = 0; i < nPiles; i++){
			piles.add(new Stack<Integer>());
		}
		
		for(int i = 0; i < deck.length; i++){
			piles.get(i % nPiles).add(deck[i]);
		}
		
		int currentCard = piles.get(nPiles - 1).pop();
		int nCardsExposed = 1;
		while (true) {
			int pileIndex = index(currentCard);
			if(piles.get(pileIndex).isEmpty()) break;
			currentCard = piles.get(pileIndex).pop();
			nCardsExposed++;
		}
		
		return new int[]{nCardsExposed,currentCard};
	}
}
