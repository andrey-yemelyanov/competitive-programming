package helvidios.cp.ch1.easy;

import java.util.Scanner;

public class _12015_GoogleIsFeelingLucky {
	public static void main(String... args){
		String data = "2\r\n" + 
				"www.youtube.com 1\r\n" + 
				"www.google.com 2\r\n" + 
				"www.google.com.hk 3\r\n" + 
				"www.alibaba.com 10\r\n" + 
				"www.taobao.com 5\r\n" + 
				"www.bad.com 10\r\n" + 
				"www.good.com 7\r\n" + 
				"www.fudan.edu.cn 8\r\n" + 
				"www.university.edu.cn 9\r\n" + 
				"acm.university.edu.cn 10\r\n" + 
				"www.youtube.com 1\r\n" + 
				"www.google.com 2\r\n" + 
				"www.google.com.hk 3\r\n" + 
				"www.alibaba.com 11\r\n" + 
				"www.taobao.com 5\r\n" + 
				"www.bad.com 10\r\n" + 
				"www.good.com 7\r\n" + 
				"www.fudan.edu.cn 8\r\n" + 
				"acm.university.edu.cn 9\r\n" + 
				"acm.university.edu.cn 10\r\n";
		Scanner scanner = new Scanner(data);
		int testCaseCount = scanner.nextInt();
		int caseNum = 0;
		while(testCaseCount-- > 0){
			String[] urls = new String[10];
			int[] ranks = new int[10];
			for (int i = 0; i < 10; i++) {
				urls[i] = scanner.next();
				ranks[i] = scanner.nextInt();
			}
			
			System.out.printf("Case #%1$d:\n", ++caseNum);
			int maxRank = getMaxRank(ranks);
			for(int url = 0; url < urls.length; url++)
				if(ranks[url] == maxRank)
					System.out.println(urls[url]);
				
		}
		scanner.close();
	}
	
	private static int getMaxRank(int[] ranks){
		int max = Integer.MIN_VALUE;
		for(int rank : ranks)
			if(rank > max) max = rank;
		return max;
	}
}
