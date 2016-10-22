package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _10336_RankLanguages{
	static class Pair{
		public char lang;
		public int count;
		public Pair(char lang, int count){
			this.lang = lang;
			this.count = count;
		}
	}
	public static void main(String[] args){
		String data = "2\r\n4 8\r\nttuuttdd\r\nttuuttdd\r\nuuttuudd\r\nuuttuudd\r\n9 9\r\nbbbbbbbbb\r\naaaaaaaab\r\nbbbbbbbab\r\nbaaaaacab\r\nbacccccab\r\nbacbbbcab\r\nbacccccab\r\nbaaaaaaab\r\nbbbbbbbbb";
		Scanner s = new Scanner(data);
		int nTests = s.nextInt(); 
		for(int t = 1; t <= nTests; t++){
			char[][] map = new char[s.nextInt()][s.nextInt()];
			s.nextLine();
			for(int i = 0; i < map.length; i++){
				map[i] = s.nextLine().toCharArray();
			}
			List<Pair> list = buildStats(map);
			System.out.printf("World #%d\n", t);
			System.out.print(toString(list));
		}
	}

	static String toString(List<Pair> list){
		StringBuilder sb = new StringBuilder();
		for(Pair p : list){
			sb.append(String.format("%c: %d\n", p.lang, p.count));
		}
		return sb.toString();
	}

	static List<Pair> buildStats(char[][] map){
		Map<Character, Integer> m = new HashMap<>();
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				if(map[i][j] != VISITED){
					char lang = map[i][j];
					floodfill(map, i, j, lang);
					if(!m.containsKey(lang)) m.put(lang, 1);
					else m.put(lang, m.get(lang) + 1);
				}
			}
		}

		List<Pair> list = toList(m);
		Collections.sort(list, new Comparator<Pair>(){
			@Override
			public int compare(Pair p1, Pair p2){
				if(p1.count != p2.count) return Integer.compare(p2.count, p1.count);
				return Character.compare(p1.lang, p2.lang);
			}
		});
		return list;
	}

	static List<Pair> toList(Map<Character, Integer> m){
		List<Pair> list = new ArrayList<>();
		for(Map.Entry<Character, Integer> e : m.entrySet()){
			list.add(new Pair(e.getKey(), e.getValue()));
		}
		return list;
	}

	static final char VISITED = '1';
	static final int[] dr = new int[] {-1, 0, 1, 0};
   	static final int[] dc = new int[] {0, 1, 0, -1};
   	static void floodfill(char[][] map, int row, int col, char color){
   		if(row < 0 || row >= map.length || col < 0 || col >= map[row].length) return;
   		if(map[row][col] == VISITED || map[row][col] != color) return;
   		map[row][col] = VISITED;
   		for(int d = 0; d < 4; d++){
   			floodfill(map, row + dr[d], col + dc[d], color);
   		}
   	}
}