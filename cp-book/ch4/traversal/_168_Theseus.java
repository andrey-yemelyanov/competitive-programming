package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _168_Theseus {
    public static void main(String[] args) {
        String data = "A:BCD;B:AD;D:BG;F:H;G:DEH;E:FGH;H:EG;C:AD. A C 3\n#";
     	String data2 = "A:DBC. A B 20\n#";
      	String data3 = "A:B;B:C;C:A. A C 0\n#";
      	String data4 = "A:DBC. A B 1\n#";
        Scanner scanner = new Scanner(data4);
		while(scanner.hasNext()){
			String graphStr = scanner.next().replace(".","");
			if(graphStr.contains("#")) break;
			String[] vertices = graphStr.split(";");
			Map<Character, List<Character>> graph = new HashMap<>();
          	for(int i = 0; i < 26; i++){
              	graph.put((char)(i + 'A'), new ArrayList<Character>());
          	}
			for(String v : vertices){
				String[] e = v.split(":");
				char vertex = e[0].charAt(0);
				for(char neighbor : e[1].toCharArray()){
					graph.get(vertex).add(neighbor);
				}
			}
			char minotaurPos = scanner.next().charAt(0);
			char theseusPos = scanner.next().charAt(0);
			int k = scanner.nextInt();
			System.out.println(trapMinotaur(k, graph, minotaurPos, theseusPos));
		}
        scanner.close();
    }
	
	static String trapMinotaur(int k, Map<Character, List<Character>> graph, 
		char minotaurPos, char theseusPos){
		List<Character> litCaverns = new ArrayList<>();
		boolean[] cavernLit = new boolean[26];
		char trappedInCavern = trapMinotaur(minotaurPos, theseusPos, 1, k, graph, litCaverns, cavernLit);
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < litCaverns.size(); i++){
			str.append(litCaverns.get(i) + " ");
		}
		str.append("/" + trappedInCavern);
		return str.toString();
	}
	
	static char trapMinotaur(char source, char prevSource, int seqNum, int k,
		Map<Character, List<Character>> graph, List<Character> litCaverns, boolean[] cavernLit){
		if(seqNum == k && !isTrapped(source, prevSource, graph, cavernLit)){
			cavernLit[source - 'A'] = true;
			litCaverns.add(source);
			seqNum = 0;
		}
		for(char neighbor : graph.get(source)){
			if(neighbor != prevSource && !cavernLit[neighbor - 'A']){
				return trapMinotaur(neighbor, source, seqNum + 1, k, graph, litCaverns, cavernLit);
			}
		}
		return source;
	}
	
	static boolean isTrapped(char cavern, char prevSource, 
		Map<Character, List<Character>> graph, boolean[] cavernLit){
		boolean trapped = true;
		for(char neighbor : graph.get(cavern)){
			if(neighbor != prevSource && !cavernLit[neighbor - 'A']){
				trapped = false;
			}
		}
		return trapped;
	}
}