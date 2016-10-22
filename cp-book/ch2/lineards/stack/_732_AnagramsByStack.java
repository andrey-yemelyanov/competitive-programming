package helvidios.cp.ch2.lineards.stack;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class _732_AnagramsByStack {
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	
	public static void main(String... args) throws IOException{
		String data = "madam\r\n" + 
				"adamm\r\n" + 
				"bahama\r\n" + 
				"bahama\r\n" + 
				"long\r\n" + 
				"short\r\n" + 
				"eric\r\n" + 
				"rice\r\n";
		String data2 = "step\ntpes\n";
		
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data.getBytes()));
		String line;
		StringBuilder sb = new StringBuilder();
		while((line = reader.readLine()) != null){
			String sourceWord = line;
			String targetWord = reader.readLine();
			sb.append(toString(generateValidSequences(sourceWord, targetWord)));
		}
		System.out.print(sb.toString());
	}
	
	public static String toString(List<String> sequences){
		StringBuilder builder = new StringBuilder();
		builder.append("[\n");
		for(int i = 0; i < sequences.size(); i++){
			for(int j = 0; j < sequences.get(i).length(); j++){
				builder.append(sequences.get(i).charAt(j));
				if(j < sequences.get(i).length() - 1) builder.append(" ");
			}
			if(sequences.size() > 0) builder.append("\n");
		}
		builder.append("]\n");
		return builder.toString();
	}
	
	public static List<String> generateValidSequences(
			String sourceWord,
			String targetWord){
		List<String> sequences = new ArrayList<String>();
		if(sourceWord.length() != targetWord.length()) return sequences;
		generateValidSequences(new StringBuilder(), 0, 0, sourceWord, targetWord, sequences);
		Collections.sort(sequences);
		return sequences;
	}
	
	public static void generateValidSequences(
			StringBuilder sequence,
			int pushCommandCount,
			int popCommandCount,
			String sourceWord,
			String targetWord,
			List<String> sequences){
		if(popCommandCount > pushCommandCount) return;
		if(!isValidSequence(sequence.toString(), sourceWord, targetWord)) return;
		if(sequence.length() == 2 * sourceWord.length()){
			if(isValidSequence(sequence.toString(), sourceWord, targetWord)){
				sequences.add(sequence.toString());
			}
		}else{
			if(pushCommandCount == sourceWord.length()){
				sequence.append("o");
				generateValidSequences(
						sequence, 
						pushCommandCount, 
						popCommandCount + 1,
						sourceWord, 
						targetWord, 
						sequences);
				sequence.deleteCharAt(sequence.length() - 1);
			}else{
				sequence.append("o");
				generateValidSequences(
						sequence, 
						pushCommandCount, 
						popCommandCount + 1,
						sourceWord, 
						targetWord, 
						sequences);
				sequence.deleteCharAt(sequence.length() - 1);
				
				sequence.append("i");
				generateValidSequences(
						sequence, 
						pushCommandCount + 1, 
						popCommandCount,
						sourceWord, 
						targetWord, 
						sequences);
				sequence.deleteCharAt(sequence.length() - 1);
			}
		}
	}
	
	public static boolean isValidSequence(String sequence, String sourceWord, String targetWord){
		StringBuilder wordBuilder = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		char[] sourceWordChars = sourceWord.toCharArray();
		int i = 0;
		for(char command : sequence.toCharArray()){
			if(command == 'i') stack.push(sourceWordChars[i++]);
			else {
				if(stack.isEmpty()) return false;
				wordBuilder.append(stack.pop());
			}
		}
		return targetWord.startsWith(wordBuilder.toString());
	}
}
