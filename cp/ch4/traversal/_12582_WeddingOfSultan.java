package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _12582_WeddingOfSultan {
    public static void main(String[] args) {
        String data = "2\nAEFFGGEBDDCCBA\nZAABBZ";
        Scanner scanner = new Scanner(data);
		int nTests = scanner.nextInt();
		for(int i = 1; i <= nTests; i++){
			String traversal = scanner.next();
			System.out.println("Case " + i);
			System.out.print(toString(getVertexDegrees(traversal)));
		}
        scanner.close();
    }
	// comment
	static String toString(int[] map){
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < map.length; i++){
			if(map[i] != 0){
				str.append(String.format("%c = %d\n", (char)(i + 'A'), map[i]));
			}
		}
		return str.toString();
	}
	
	static int[] getVertexDegrees(String traversal){
		Stack<Character> s = new Stack<>();
		final int alphabetSize = 26;
		int[] map = new int[alphabetSize];
		for(char vertex : traversal.toCharArray()){
			if(s.isEmpty()) s.push(vertex);
			else{
				if(s.peek() == vertex) s.pop();
				else{
					map[vertex - 'A']++;
					map[s.peek() - 'A']++;
					s.push(vertex);
				}
			}
		}
		return map;
	}
}