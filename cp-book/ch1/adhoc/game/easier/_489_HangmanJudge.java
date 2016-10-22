package helvidios.cp.ch1.adhoc.game.easier;

import java.util.Scanner;

public class _489_HangmanJudge {
	public static void main(String... args) throws NumberFormatException{
		String data2 = "1\r\n" + 
				"cheese\r\n" + 
				"chese\r\n" + 
				"2\r\n" + 
				"cheese\r\n" + 
				"abcdefg\r\n" + 
				"3\r\n" + 
				"cheese\r\n" + 
				"abcdefgij\r\n" + 
				"4\r\n" + 
				"rommel\r\n" + 
				"romlnptuyq\r\n" + 
				"5\r\n" + 
				"rommel\r\n" + 
				"romlnptuyqw\r\n" + 
				"6\r\n" + 
				"casa\r\n" + 
				"ca\r\n" + 
				"7\r\n" + 
				"otorrinolaringologia\r\n" + 
				"otr\r\n" + 
				"8\r\n" + 
				"peru\r\n" + 
				"abcdefghijklmno\r\n" + 
				"9\r\n" + 
				"lastima\r\n" + 
				"la\r\n" + 
				"10\r\n" + 
				"aaaaaaaaaaaaaaaaaaaaaa\r\n" + 
				"a\r\n" + 
				"11\r\n" + 
				"bobobobobobobo\r\n" + 
				"b\r\n" + 
				"12\r\n" + 
				"lalalalabababababaaaaa\r\n" + 
				"alhhhhhhhhhhhhhhhhhhhhhhh\r\n" + 
				"13\r\n" + 
				"lkjaskljfkjklsalsdjfslkjfjf\r\n" + 
				"dfklsdfskld\r\n" + 
				"14\r\n" + 
				"nbmbmmbnbbmbmbmbmnbnbnbmbmmbbnbnnbmbmbmboffiifififififfiif\r\n" + 
				"ppppppppppppppppppppppppppppppppppwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwweeeeeeeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrb\r\n" + 
				"15\r\n" + 
				"abcdefggegegegegegege\r\n" + 
				"gegegegegddabacecevbbdbdnndnenjejje\r\n" + 
				"16\r\n" + 
				"diccionariosdeportugues\r\n" + 
				"dcptgiowqqqaazzxxxx\r\n" + 
				"17\r\n" + 
				"b\r\n" + 
				"c\r\n" + 
				"18\r\n" + 
				"kljfdsjfoieoijefnvnenvionewveinvewv\r\n" + 
				"dkdjjshue\r\n" + 
				"19\r\n" + 
				"ooooooooooooopppppppppppppppppppppeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrwwwwwwwwwwwwwwwwwwwtttttttttttttttttt\r\n" + 
				"operwt\r\n" + 
				"20\r\n" + 
				"ooooooooooooopppppppppppppppppppppeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrwwwwwwwwwwwwwwwwwwwtttttttttttttttttt\r\n" + 
				"operwqzxcvbnmklo\r\n" + 
				"21\r\n" + 
				"ploploploploploploploploploploploploploploploploploploploploploploploploploploploploploploploploploploploploploploplo\r\n" + 
				"jslkfsdjfsjfljfsdjfkdsjkjflkf\r\n" + 
				"4\r\n" + 
				"t\r\n" + 
				"r\r\n" + 
				"1\r\n" + 
				"aaa\r\n" + 
				"bcdbcdbcdbcdefghja\r\n" + 
				"7\r\n" + 
				"aaa\r\n" + 
				"bcdbcdbcdbcdefdta\r\n" + 
				"2\r\n" + 
				"aaa\r\n" + 
				"bcdbcdbcdbcdegt\r\n" + 
				"1\r\n" + 
				"cheese\r\n" + 
				"ch\r\n" + 
				"1\r\n" + 
				"z\r\n" + 
				"abcdef\r\n" + 
				"1\r\n" + 
				"aaa\r\n" + 
				"bcdbcdbcdbcdefghja\r\n" + 
				"7\r\n" + 
				"aaa\r\n" + 
				"bcdbcdbcdbcdefdta\r\n" + 
				"2\r\n" + 
				"aaa\r\n" + 
				"bcdbcdbcdbcdegt\r\n" + 
				"167\r\n" + 
				"axyq\r\n" + 
				"eprxibexxyf\r\n" + 
				"171\r\n" + 
				"nho\r\n" + 
				"tonpyzwotkg\r\n" + 
				"1\r\n" + 
				"z\r\n" + 
				"abcdef\r\n" + 
				"1\r\n" + 
				"abcdef\r\n" + 
				"aghijklmcdefb\r\n" + 
				"2\r\n" + 
				"abcdef\r\n" + 
				"abcdklmnoegfi\r\n" + 
				"3\r\n" + 
				"abcdef\r\n" + 
				"abcklmnopdf\r\n" + 
				"-1";
		
		Scanner scanner = new Scanner(data2);
		StringBuilder b = new StringBuilder();
		while(scanner.hasNext()){
			int round = scanner.nextInt();
			if(round == -1) break;
			String solution = scanner.next();
			String guesses = scanner.next();
			b.append(String.format("Round %1$d\n%2$s\n", round, getGameOutcome(solution, guesses)));
		}
		System.out.print(b.toString());
		scanner.close();
	}
	
	static String getGameOutcome(String solution, String guessesString){
		int guessedSoFar = 0;
		int hangmanStrokes = 0;
		boolean[] guesses = new boolean[128];
		for(int i = 0; i < guessesString.length(); i++){
			char current = guessesString.charAt(i);
			if(!guesses[current]){
				int guessedCount = countOccurrences(current, solution);
				if(guessedCount == 0){ // wrong guess
					hangmanStrokes++;
					if(hangmanStrokes == 7) return "You lose.";
				}else{
					guessedSoFar += guessedCount;
					if(guessedSoFar >= solution.length()){
						return "You win.";
					}
				}
				guesses[current] = true;
			}
		}
		return "You chickened out.";
	}
	
	static int countOccurrences(char c, String str){
		int count = 0;
		for(int i = 0; i < str.length(); i++){
			if(c == str.charAt(i))
				count++;
		}
		return count;
	}
}
