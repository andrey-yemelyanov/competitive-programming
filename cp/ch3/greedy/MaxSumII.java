package helvidios.cp.ch3.greedy;

import java.util.*;

public class MaxSumII{
	public static void main(String[] args){
		String data = "2\n3\n4\n0";
		String data2 = "5 \r\n1 \r\n2 \r\n3 \r\n0 \r\n0 \r\n3 \r\n1 \r\n1 \r\n1 \r\n3 \r\n0 \r\n0 \r\n0 \r\n0 ";
		Scanner scanner = new Scanner(data2);
		StringBuilder out = new StringBuilder();
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			if(n == 0) break;
			List<Integer> list = new ArrayList<Integer>();
			for(int i = 0; i < n; i++){
				int k = scanner.nextInt();
				if(k > 0) list.add(k);
			}
			if(list.size() == 0) out.append("0\n");
			else{
				for(int i = 0; i < list.size(); i++){
					out.append(list.get(i));
					if(i < list.size() - 1) out.append(" ");
					if(i == list.size() - 1) out.append("\n");
				}
			}
		}
		System.out.print(out.toString());
	}
}
