package helvidios.cp.ch1.adhoc.game.harder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class _232_CrosswordAnswers {
	public static class Word{
		public String word;
		public int defNum;
		public Word(String word, int defNum){
			this.word = word;
			this.defNum = defNum;
		}
	}
	static char BLACK_SQUARE = '*';
	public static void main(String... args){
		String data = 
				"2 2\r\n" + 
				"AT\r\n" + 
				"*O\r\n" + 
				"6 7\r\n" + 
				"AIM*DEN\r\n" + 
				"*ME*ONE\r\n" + 
				"UPON*TO\r\n" + 
				"SO*ERIN\r\n" + 
				"*SA*OR*\r\n" + 
				"IES*DEA\r\n" + 
				"0";
		Scanner scanner = new Scanner(data);
		int puzzleCounter = 0;
		StringBuilder s = new StringBuilder();
		while(scanner.hasNext()){
			int nRows = scanner.nextInt();
			if(nRows == 0) break;
			int nCols = scanner.nextInt();
			char[][] crossword = new char[nRows][nCols];
			for(int row = 0; row < nRows; row++){
				char[] line = scanner.next().toCharArray();
				for(int col = 0; col < nCols; col++){
					crossword[row][col] = line[col];
				}
			}
			
			int[][] defNums = generateDefNums(crossword);
			if(puzzleCounter != 0) s.append("\n");
			s.append(String.format("puzzle #%1$d:\n", ++puzzleCounter));
			s.append("Across\n");
			s.append(printWords(getAcrossWords(crossword, defNums)));
			s.append("Down\n");
			s.append(printWords(getDownWords(crossword, defNums)));
		}
		System.out.print(s.toString());
		scanner.close();
	}
	
	public static int[][] generateDefNums(char[][] crossword){
		int counter = 0;
		int[][] defNums = new int[crossword.length][crossword[0].length];
		for(int row = 0; row < crossword.length; row++){
			for(int col = 0; col < crossword[0].length; col++){
				if(crossword[row][col] != BLACK_SQUARE && squareEligible(crossword, row, col)){
					defNums[row][col] = ++counter;
				}
			}
		}
		return defNums;
	}
	
	static boolean squareEligible(char[][] crossword, int row, int col){
		if(row - 1 < 0 || col - 1 < 0) return true;
		return crossword[row - 1][col] == BLACK_SQUARE || crossword[row][col - 1] == BLACK_SQUARE;
	}
	
	public static String printWords(List<Word> words){
		StringBuilder s = new StringBuilder();
		for (Word word : words) {
			if(word.defNum < 10) s.append(" ");
			s.append(" ");
			s.append(word.defNum);
			s.append(".");
			s.append(word.word);
			s.append("\n");
			//s.append(String.format("%1$2d.%2$s\n", word.defNum, word.word));
		}
		return s.toString();
	}
	
	public static List<Word> getDownWords(char[][] crossword, int[][] defNums){
		List<Word> words = new ArrayList<Word>();
		StringBuilder current = new StringBuilder();
		boolean newWord = true;
		int defRow = 0;
		int defCol = 0;
		for(int col = 0; col < crossword[0].length; col++){
			for(int row = 0; row < crossword.length; row++){
				if(crossword[row][col] != BLACK_SQUARE){
					if(newWord){
						defRow = row;
						defCol = col;
						newWord = false;
					}
					current.append(crossword[row][col]);
				}else{
					if(current.length() > 0){
						words.add(new Word(current.toString(), defNums[defRow][defCol]));
					}
					current.delete(0, current.length());
					newWord = true;
				}
			}
			if(current.length() > 0){
				words.add(new Word(current.toString(), defNums[defRow][defCol]));
			}
			current.delete(0, current.length());
			newWord = true;
		}
		
		Collections.sort(words, new Comparator<Word>() {
				@Override
				public int compare(Word o1, Word o2) {
					return Integer.compare(o1.defNum, o2.defNum);
				}
			});
		return words;
	}
	
	public static List<Word> getAcrossWords(char[][] crossword, int[][] defNums){
		List<Word> words = new ArrayList<Word>();
		StringBuilder current = new StringBuilder();
		boolean newWord = true;
		int defRow = 0;
		int defCol = 0;
		for(int row = 0; row < crossword.length; row++){
			for(int col = 0; col < crossword[0].length; col++){
				if(crossword[row][col] != BLACK_SQUARE){
					if(newWord){
						defRow = row;
						defCol = col;
						newWord = false;
					}
					current.append(crossword[row][col]);
				}else{
					if(current.length() > 0){
						words.add(new Word(current.toString(), defNums[defRow][defCol]));
					}
					current.delete(0, current.length());
					newWord = true;
				}
			}
			if(current.length() > 0){
				words.add(new Word(current.toString(), defNums[defRow][defCol]));
			}
			current.delete(0, current.length());
			newWord = true;
		}
		
		Collections.sort(words, new Comparator<Word>() {
			@Override
			public int compare(Word o1, Word o2) {
				return Integer.compare(o1.defNum, o2.defNum);
			}
		});
		return words;
	}
}
