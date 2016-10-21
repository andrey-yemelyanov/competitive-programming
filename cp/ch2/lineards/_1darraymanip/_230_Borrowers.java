package helvidios.cp.ch2.lineards._1darraymanip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _230_Borrowers {
	public static class Book implements Comparable<Book>{
		public String title;
		public String author;
		@Override
		public int compareTo(Book other) {
			if(!this.author.equals(other.author)) return this.author.compareTo(other.author);
			return this.title.compareTo(other.title);
		}
	}
	public static void main(String... args){
		String data = "\"The Canterbury Tales\" by Chaucer, G.\r\n" + 
				"\"Algorithms\" by Sedgewick, R.\r\n" + 
				"\"The C Programming Language\" by Kernighan, B. and Ritchie, D.\r\n" + 
				"END\r\n" + 
				"BORROW \"Algorithms\"\r\n" + 
				"BORROW \"The C Programming Language\"\r\n" + 
				"RETURN \"Algorithms\"\r\n" + 
				"RETURN \"The C Programming Language\"\r\n" + 
				"SHELVE\r\n" + 
				"END\r\n" + 
				"";
		String data2 = "\"Power\" by Jane A. Austin\n"+
				"END\n"+
				"BORROW \"Power\"\n"+
				"SHELVE\n" +
				"RETURN \"Power\"\n" +
				"SHELVE\n" +
				"END";
		String data3 = "\"The Canterbury Tales\" by Chaucer, G.\r\n" + 
				"\"The Canterbury Taless\" by Chaucer, B.\r\n" + 
				"\"Algorithms\" by Sedgewick, R.\r\n" + 
				"\"The C Programming Language\" by Kernighan, B. and Ritchiee, D.\r\n" + 
				"\"The C Programming Languag\" by Kernighan, B. and Ritchiee, D.\r\n" + 
				"\"The D Programming Language\" by Kernighan, B. and Ritchiee, D.\r\n" + 
				"\"A House for Mr. Biswas\" by Naipaul, V.S.\r\n" + 
				"\"A Congo Diary\" by Naipaul, V.S.\r\n" + 
				"END\r\n" + 
				"BORROW \"Algorithms\"\r\n" + 
				"BORROW \"The C Programming Language\"\r\n" + 
				"BORROW \"The C Programming Languag\"\r\n" + 
				"BORROW \"The Canterbury Taless\"\r\n" + 
				"SHELVE\r\n" + 
				"RETURN \"Algorithms\"\r\n" + 
				"SHELVE\r\n" + 
				"RETURN \"The C Programming Languag\"\r\n" + 
				"SHELVE\r\n" + 
				"BORROW \"The C Programming Languag\"\r\n" + 
				"BORROW \"The Canterbury Taless\"\r\n" + 
				"BORROW \"A House for Mr. Biswas\"\r\n" + 
				"RETURN \"The Canterbury Taless\" \r\n" + 
				"SHELVE\r\n" + 
				"RETURN \"The C Programming Language\"\r\n" + 
				"RETURN \"A House for Mr. Biswas\"\r\n" + 
				"SHELVE\r\n" + 
				"END";
		Scanner scanner = new Scanner(data3);
		List<Book> books = new ArrayList<Book>();
		while(true){
			String book = scanner.nextLine().trim();
			if(!book.isEmpty()){
				if(book.equals("END")) break;
				books.add(parseBook(book));
			}
		}
		
		Book[] shelve = books.toArray(new Book[0]);
		Arrays.sort(shelve);
		boolean[] borrowed = new boolean[shelve.length];
		boolean[] returned = new boolean[shelve.length];
		while(true){
			String line = scanner.nextLine().trim();
			if(!line.isEmpty()){
				String op = line.split("\\s+")[0];
				if(op.equals("SHELVE")){
					int[][] instructions = restock(shelve, borrowed, returned);
					for(int i = 0; i < instructions.length; i++){
						if(instructions[i][0] == 0 && instructions[i][1] == 0) break;
						if(instructions[i][0] == -1){
							System.out.printf("Put %1$s first\n", shelve[instructions[i][1]].title);
						}else{
							System.out.printf("Put %1$s after %2$s\n", 
									shelve[instructions[i][1]].title, 
									shelve[instructions[i][0]].title);
						}
					}
					System.out.println("END");
					returned = new boolean[shelve.length];
				}else if(op.equals("BORROW")){
					borrowBook(shelve, borrowed, line.substring(op.length() + 1));
				}else if(op.equals("RETURN")){
					returnBook(shelve, borrowed, returned, line.substring(op.length() + 1));
				}
				else break;
			}
		}
		
		scanner.close();
	}
	
	public static Book parseBook(String str){
		Book book = new Book();
		String[] s = str.split("by");
		book.title = s[0].trim();
		book.author = s[1].trim();
		return book;
	}
	
	public static int[][] restock(Book[] shelve, boolean[] borrowed, boolean[] returned){
		int prev = -1;
		int[][] instructions = new int[shelve.length][2];
		int current = 0;
		for(int i = 0; i < shelve.length; i++){
			if(returned[i]){
				int[] pair = new int[]{prev, i};
				instructions[current++] = pair;
				prev = i;
			}
			else if(!borrowed[i]) prev = i;
		}
		
		return instructions; 
	}
	
	public static void borrowBook(
			Book[] shelve, 
			boolean[] borrowed, 
			String title){
		int i = findBook(shelve, title);
		borrowed[i] = true;
	}
	
	public static void returnBook(
			Book[] shelve, 
			boolean[] borrowed, 
			boolean[] returned, 
			String title){
		int i = findBook(shelve, title);
		borrowed[i] = false;
		returned[i] = true;
	}
	
	static int findBook(Book[] books, String title){
		for(int i = 0; i < books.length; i++){
			if(books[i].title.equals(title)) return i;
		}
		return -1;
	}
}
