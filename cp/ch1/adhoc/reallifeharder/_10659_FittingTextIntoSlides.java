package helvidios.cp.ch1.adhoc.reallifeharder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _10659_FittingTextIntoSlides {
	public static final int NO_SOLUTION = 0;
	public static void main(String... args){
		String data = "3\r\n" + 
				"3\r\n" + 
				"En un lugar de la mancha\r\n" + 
				"de cuyo nombre no quiero acordarme\r\n" + 
				"no ha mucho que vivia...\r\n" + 
				"375 35\r\n" + 
				"1\r\n" + 
				"LE LO\r\n" + 
				"40 40\r\n" + 
				"2\r\n" + 
				"I love this hyper-mega-cosmic-contest.\r\n" + 
				"The winner.\r\n" + 
				"100 80\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while (nTestCases-- > 0) {
			int nParagraphs = scanner.nextInt();
			List<String> paragraphs = new ArrayList<String>();
			while (nParagraphs-- > 0) {
				String line = scanner.nextLine();
				if(line.trim().isEmpty()) nParagraphs++; 
				else paragraphs.add(line);
			}
			int slideWidth = scanner.nextInt();
			int slideHeight = scanner.nextInt();
			int size = findMaxFontSize(paragraphs, slideWidth, slideHeight);
			if(size != NO_SOLUTION) System.out.println(size);
			else System.out.println("No solution");
		}
		scanner.close();
	}
	
	public static int findMaxFontSize(
			List<String> paragraphs, 
			int slideWidth, 
			int slideHeight){
		final int minFontSize = 8;
		final int maxFontSize = 28;
		for(int size = maxFontSize; size >= minFontSize; size--){
			if(textFits(paragraphs, slideWidth, slideHeight, size)){
				return size;
			}
		}
		return NO_SOLUTION;
	}
	
	public static boolean textFits(
			List<String> paragraphs, 
			int slideWidth, 
			int slideHeight, 
			int fontSize){
		int maxLineLength = slideWidth / fontSize;
		int maxLineCount = slideHeight / fontSize;
		
		int line = 1;
		int column = 1;
		for(int i = 0; i < paragraphs.size(); i++){
			String[] words = paragraphs.get(i).split(" ");
			for(int j = 0; j < words.length; j++)
			{
				String word = words[j];
				if(wordFitsWithinLine(word, column, maxLineLength)){
					column += word.length();
					if(j < words.length - 1) column++; // whitespace
				}
				else if(newLineAvailable(line + 1, maxLineCount)){
					line++;
					column = 1;
					j--;
				}
				else return false;
			}
			if(i < paragraphs.size() - 1){
				if(newLineAvailable(line + 1, maxLineCount)){
					line++;
					column = 1;
				} else return false;
			}
		}
		return true;
	}
	
	static boolean newLineAvailable(int lineNumber, int maxLineCount){
		return lineNumber <= maxLineCount;
	}
	
	static boolean wordFitsWithinLine(String word, int offset, int maxLineLength){
		return offset + (word.length() - 1) <= maxLineLength;
	}
}
