package helvidios.cp.ch2.lineards.stack;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _127_AccordianPatience {
	private static char[] values = new char[]{'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
	private static char[] suits = new char[]{'C','D','H','S'};
	
	public static int encode(char value, char suit){
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
	
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream), 1000 * 1024);
	}
	
	public static void main(String... args) throws IOException{
		String data = "QD AD 8H 5S 3H 5H TC 4D JH KS 6H 8S JS AC AS 8D 2H QS TS 3S AH 4H TH TD 3C 6S\r\n" + 
				"8C 7D 4C 4S 7S 9H 7C 5D 2S KD 2D QH JD 6D 9D JC 2C KH 3D QC 6C 9S KC 7H 9C 5C\r\n" + 
				"AC 2C 3C 4C 5C 6C 7C 8C 9C TC JC QC KC AD 2D 3D 4D 5D 6D 7D 8D TD 9D JD QD KD\r\n" + 
				"AH 2H 3H 4H 5H 6H 7H 8H 9H KH 6S QH TH AS 2S 3S 4S 5S JH 7S 8S 9S TS JS QS KS\r\n" + 
				"#";
		
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data.getBytes()));
		String line;
		StringBuilder result = new StringBuilder();
		while((line = reader.readLine()) != null){
			if(line.equals("#")) break;
			int[] cards = new int[52];
			String[] c = line.split(" ");
			for(int i = 0; i < 26; i++){
				cards[i] = encode(c[i].charAt(0), c[i].charAt(1));
			}
			line = reader.readLine();
			c = line.split(" ");
			for(int i = 26; i < 52; i++){
				cards[i] = encode(c[i - 26].charAt(0), c[i - 26].charAt(1));
			}
			
			List<Stack<Integer>> piles = new ArrayList<Stack<Integer>>();
			for(int i = 0; i < cards.length; i++){
				Stack<Integer> s = new Stack<Integer>();
				s.push(cards[i]);
				piles.add(s);
			}
			play(piles);
			if(result.length() != 0) result.append("\n");
			result.append(printResult(piles));
		}
		System.out.println(result.toString());
	}
	
	private static String printResult(List<Stack<Integer>> piles){
		StringBuilder sb = new StringBuilder();
		sb.append(piles.size());
		sb.append(" pile");
		if(piles.size() > 1) sb.append("s");
		sb.append(" remaining:");
		for(Stack<Integer> pile : piles){
			sb.append(" ");
			sb.append(pile.size());
		}
		return sb.toString();
	}
	
	public static void play(List<Stack<Integer>> piles){
		for(int i = 0; i < piles.size(); i++){
			int moveTo = moveToIndex(piles.get(i).peek(), piles, i);
			if(moveTo != -1){
				piles.get(moveTo).push(piles.get(i).pop());
				if(piles.get(i).isEmpty()) {
					piles.remove(i);
				}
				i = 0;
			}
		}
	}
	
	public static int moveToIndex(int card, List<Stack<Integer>> piles, int index){
		if(index >= 3 && cardsMatch(card, piles.get(index - 3).peek())){
			return index - 3;
		}
		
		if(index >= 1 && cardsMatch(card, piles.get(index - 1).peek())){
			return index - 1;
		}
		
		return -1;
	}
	
	public static boolean cardsMatch(int card1, int card2){
		return suit(card1) == suit(card2) || value(card1) == value(card2);
	}
}
