package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _01262_Password{
	public static void main(String[] args){
		String data = "3\r\n1\r\nAYGSU\r\nDOMRA\r\nCPFAS\r\nXBODG\r\nWDYPK\r\nPRXWO\r\nCBOPT\r\nDOSBG\r\nGTRAR\r\nAPMMS\r\nWSXNU\r\nEFGHI\r\n5\r\nAYGSU\r\nDOMRA\r\nCPFAS\r\nXBODG\r\nWDYPK\r\nPRXWO\r\nCBOPT\r\nDOSBG\r\nGTRAR\r\nAPMMS\r\nWSXNU\r\nEFGHI\r\n64\r\nFGHIJ\r\nEFGHI\r\nDEFGH\r\nCDEFG\r\nBCDEF\r\nABCDE\r\nWBXDY\r\nUWYXZ\r\nXXZFG\r\nYYFYH\r\nEZWZI\r\nZGHIJ";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int k = scanner.nextInt() - 1;
			char[][] grid1 = new char[6][5];
			char[][] grid2 = new char[6][5];
			for(int i = 0; i < grid1.length; i++){
				String row = scanner.next();
				for(int j = 0; j < grid1[0].length; j++){
					grid1[i][j] = row.charAt(j);
				}
			}
			for(int i = 0; i < grid2.length; i++){
				String row = scanner.next();
				for(int j = 0; j < grid2[0].length; j++){
					grid2[i][j] = row.charAt(j);
				}
			}
			System.out.println(getKthPassword(grid1, grid2, k));
		}
		scanner.close();
	}
	
	static String getKthPassword(char[][] grid1, char[][] grid2, int k){
		Set<String> set1 = generatePasswords(grid1);
		Set<String> set2 = generatePasswords(grid2);
		List<String> common = new ArrayList<String>();
		for(String password : set1){
			if(set2.contains(password)){
				common.add(password);
			}
		}
		if(k >= common.size()) return "NO";
		return common.get(k);
	}
	
	static Set<String> generatePasswords(char[][] grid){
		Set<String> set = new TreeSet<String>();
		generatePasswords(grid, 0, "", set);
		return set;
	}
	
	static void generatePasswords(char[][] grid, int column, String password, Set<String> passwords){
		if(column == grid[0].length){
			passwords.add(password);
			return;
		}
		for(int row = 0; row < grid.length; row++){
			generatePasswords(grid, column + 1, password + grid[row][column], passwords);
		}
	}
}