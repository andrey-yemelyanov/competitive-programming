package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _628_Passwords{
	public static void main(String[] args){
		String data = "2\nroot\n2super\n1\n#0\n1\nadmin\n1\n#0#";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int nWords = scanner.nextInt();
			List<String> dictionary = new ArrayList<String>();
			for(int i = 0; i < nWords; i++){
				dictionary.add(scanner.next());
			}
			int nRules = scanner.nextInt();
			List<String> rules = new ArrayList<String>();
			for(int i = 0; i < nRules; i++){
				rules.add(scanner.next());
			}
			System.out.println("--");
			for(String pattern : rules){
				System.out.print(printPasswords(generatePasswords(dictionary, pattern)));				
			}
		}
		scanner.close();

		//System.out.print(printPasswords(generatePasswords(Arrays.asList("root", "2super"), "#0")));
		//System.out.print(printPasswords(generatePasswords(Arrays.asList("admin"), "#0#")));
	}

	static String printPasswords(List<String> passwords){
		StringBuilder out = new StringBuilder();
		for(String pswd : passwords){
			out.append(pswd);
			out.append("\n");
		}
		return out.toString();
	}

	static List<String> generatePasswords(List<String> dictionary, String pattern){
		List<String> passwords = new ArrayList<String>();
		generatePasswords(dictionary, pattern, passwords, 0, "");
		return passwords;
	}

	static void generatePasswords(List<String> dictionary, String pattern, List<String> passwords, int i, String password){
		if(i == pattern.length()){
			passwords.add(password);
		}else{
			if(pattern.charAt(i) == '#'){
				for(String word : dictionary){
					generatePasswords(dictionary, pattern, passwords, i + 1, password + word);
				}
			}else{
				for(int digit = 0; digit < 10; digit++){
					generatePasswords(dictionary, pattern, passwords, i + 1, password + digit);	
				}
			}
		}
	}	
}