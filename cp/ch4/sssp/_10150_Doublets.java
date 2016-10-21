package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;

public class _10150_Doublets{
	public static void main(String[] args){
		String data = "booster\r\nrooster\r\nroaster\r\ncoasted\r\nroasted\r\ncoastal\r\npostal\r\n\r\nbooster roasted\r\ncoastal postal";
		String data2 = "aba\nabc\n\naba aba";
		Scanner s = new Scanner(data);
		String line;
		Set<String> dict = new HashSet<>();
		while(!(line = s.nextLine()).isEmpty()){
			dict.add(line);
		}
		boolean first = true;
		while(s.hasNext()){
			if(!first) {
				System.out.println();
			}else first = false;
			String[] pair = s.nextLine().split("\\s+");
			String initialWord = pair[0];
			String targetWord = pair[1];
			Map<String, String> path = bfs(initialWord, targetWord, dict);
			if(path == null) System.out.println("No solution.");
			else System.out.println(toString(path(targetWord, path)));
		}
	}

	static List<String> path(String targetWord, Map<String, String> parent){
		List<String> path = new ArrayList<>();
		path.add(targetWord);
		String p = parent.get(targetWord);
		while(p != null){
			path.add(p);
			p = parent.get(p);
		}
		Collections.reverse(path);
		return path;
	}

	static String toString(List<String> path){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < path.size(); i++){
			sb.append(path.get(i));
			if(i < path.size() - 1) sb.append("\n");
		}
		return sb.toString();
	}
	
	static List<String> doublets(String word, Set<String> dict){
		List<String> doublets = new ArrayList<>();
		for(int i = 0; i < word.length(); i++){
			for(int j = 'a'; j <= 'z'; j++){
				String candidate = word.substring(0, i) + (char)j + word.substring(i + 1);
				if(!candidate.equals(word) && dict.contains(candidate)) doublets.add(candidate);
			}
		}
		return doublets;
	}

	static final int INF = -1;
	static Map<String, String> bfs(String initialWord, String targetWord, Set<String> dict){
		Queue<String> q = new LinkedList<>();
		Map<String, Integer> dist = new HashMap<>();
		for(String word : dict){
			dist.put(word, INF);
		}
		dist.put(initialWord, 0);
		q.add(initialWord);
		Map<String, String> parent = new HashMap<>();
		parent.put(initialWord, null);
		while(!q.isEmpty()){
			String word = q.remove();
			if(word.equals(targetWord)) return parent;
			for(String nextWord : doublets(word, dict)){
				if(dist.get(nextWord) == INF){
					parent.put(nextWord, word);
					q.add(nextWord);
					dist.put(nextWord, dist.get(word) + 1);
				}
			}
		}
		return null;
	}
}