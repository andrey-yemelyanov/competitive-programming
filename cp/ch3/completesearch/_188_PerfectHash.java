package helvidios.cp.ch3.completesearch;

import java.util.Arrays;
import java.util.Scanner;

public class _188_PerfectHash {
	public static final int BITS_PER_LETTER = 5;
	public static void main(String... args){
		String data = "this is a test of some words to try out\r\n" + 
				"a bee see dee\r\n" + 
				"the of and to a in that is i it with for as\n"
				+ "i want to break free";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			String[] input = line.split("\\s+");
			int[] words = new int[input.length];
			for(int i = 0; i < words.length; i++){
				words[i] = toInt(input[i]);
			}
			int c = findPerfectHash(words);
			System.out.println(line);
			System.out.println(c);
			System.out.println();
		}
		scanner.close();
	}
	
	public static int findPerfectHash(int[] words){
		Arrays.sort(words);
		int c = words[0];
		while(true){
			int[] conflict = isPerfectHash(c, words);
			if(conflict == null) return c;
			c = nextCandidate(c, words, conflict[0], conflict[1]);
		}
	}
	
	public static int nextCandidate(int c, int[] words, int i, int j){
		return Math.min((c / words[i] + 1) * words[i], (c / words[j] + 1) * words[j]);
	}
	
	public static int[] isPerfectHash(int c, int[] words){
		int n = words.length;
		// check if for all 1 <= i < j <= n
		// floor(c/Wi) mod n != floor(c/Wj) mod n
		for(int i = 0; i < n; i++){
			for(int j = i + 1; j < n; j++){
				if(hash(c, words[i], n) == hash(c, words[j], n)) {
					return new int[]{i,j};
				}
			}
		}
		return null;
	}
	
	public static int hash(int c, int w, int n){
		return (c / w) % n;
	}
	
	public static int toInt(String word){
		// use 5 bits for each letter
		// a=1, b=2,...,z=26
		int code = 0;
		for(int i = 0; i < word.length(); i++){
			code = (code << BITS_PER_LETTER) + (word.charAt(i) - 'a' + 1);
		}
		return code;
	}
	
	public static String toWord(int code){
		StringBuilder word = new StringBuilder();
		while(code > 0){
			char c = (char) ((code % (1 << BITS_PER_LETTER)) + 'a' - 1);
			word.insert(0, c);
			code /= (1 << BITS_PER_LETTER);
		}
		return word.toString();
	}
}
