package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _487_BoggleBlitz {
	public static void main(String... args){
		String data = "2\r\n" + 
				"\r\n" + 
				"3\r\n" + 
				"one\r\n" + 
				"top\r\n" + 
				"dog\r\n" + 
				"\r\n" + 
				"4\r\n" + 
				"abcd\r\n" + 
				"bcda\r\n" + 
				"cdab\r\n" + 
				"dabc";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int n = scanner.nextInt();
			char[][] grid = new char[n][n];
			for(int i = 0; i < n; i++){
				String line = scanner.next();
				for(int j = 0; j < line.length(); j++){
					grid[i][j] = line.charAt(j);
				}
			}
			SortedSet<String> words = new TreeSet<String>(new Comparator<String>(){
				@Override
				public int compare(String word1, String word2) {
					if(word1.length() != word2.length()) return Integer.compare(word1.length(), word2.length());
					return word1.compareTo(word2);
				}
			});
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					words.addAll(findWords(new Coord(i, j), grid));
				}
			}
			System.out.print(toString(words));
			if(nTestCases > 0) System.out.println();
		}
		scanner.close();
	}
	
	static String toString(Set<String> words){
		StringBuilder builder = new StringBuilder();
		for(String word : words){
			builder.append(word);
			builder.append("\n");
		}
		return builder.toString();
	}
	
	static class Coord{
		public int row;
		public int col;
		public Coord(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	static Coord up(Coord c){
		return new Coord(c.row - 1, c.col);
	}
	
	static Coord down(Coord c){
		return new Coord(c.row + 1, c.col);
	}
	
	static Coord left(Coord c){
		return new Coord(c.row, c.col - 1);
	}
	
	static Coord right(Coord c){
		return new Coord(c.row, c.col + 1);
	}
	
	static Coord leftUpDiag(Coord c){
		return new Coord(c.row - 1, c.col - 1);
	}
	
	static Coord leftDownDiag(Coord c){
		return new Coord(c.row + 1, c.col - 1);
	}
	
	static Coord rightUpDiag(Coord c){
		return new Coord(c.row - 1, c.col + 1);
	}
	
	static Coord rightDownDiag(Coord c){
		return new Coord(c.row + 1, c.col + 1);
	}
	
	static boolean coordValid(Coord c, char[][] grid){
		return c.row >= 0 && c.row < grid.length && c.col >= 0 && c.col < grid.length;
	}
	
	static final int VISITED = 1;
	static final int UNVISITED = 0;
	static boolean visited(Coord c, int[][] visited){
		return visited[c.row][c.col] == VISITED;
	}
	static void visit(Coord c, int[][] visited){
		visited[c.row][c.col] = VISITED;
	}
	static void unvisit(Coord c, int[][] visited){
		visited[c.row][c.col] = UNVISITED;
	}
	
	static boolean charValid(char c, String word){
		return word.length() == 0 || c > word.charAt(word.length() - 1);
	}
	
	static void processCoord(List<String> words, Coord c, char[][] grid, int[][] visited, String word){
		if(coordValid(c, grid) && !visited(c, visited) && charValid(grid[c.row][c.col], word)){
			visit(c, visited);
			findWords(words, c, grid, visited, word + grid[c.row][c.col]);
			unvisit(c, visited);
		}
	}
	
	static void findWords(List<String> words, Coord source, char[][] grid, int[][] visited, String word){
		if(word.length() >= 3) words.add(word);
		
		processCoord(words, up(source), grid, visited, word);
		processCoord(words, down(source), grid, visited, word);
		processCoord(words, left(source), grid, visited, word);
		processCoord(words, right(source), grid, visited, word);
		processCoord(words, leftUpDiag(source), grid, visited, word);		
		processCoord(words, leftDownDiag(source), grid, visited, word);
		processCoord(words, rightUpDiag(source), grid, visited, word);
		processCoord(words, rightDownDiag(source), grid, visited, word);		
	}
	
	static List<String> findWords(Coord source, char[][] grid){
		int[][] visited = new int[grid.length][grid.length];
		visit(source, visited);
		List<String> words = new ArrayList<String>();
		findWords(words, source, grid, visited, Character.toString(grid[source.row][source.col]));
		return words;
	}
}
