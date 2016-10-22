package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _00416_LEDTest {
	static Map<String, Integer> map = new HashMap<String, Integer>();
	static{
		map.put("YYYYYYN", 0);
		map.put("NYYNNNN", 1);
		map.put("YYNYYNY", 2);
		map.put("YYYYNNY", 3);
		map.put("NYYNNYY", 4);
		map.put("YNYYNYY", 5);
		map.put("YNYYYYY", 6);
		map.put("YYYNNNN", 7);
		map.put("YYYYYYY", 8);
		map.put("YYYYNYY", 9);
	}
	static class LedConfig{
		public boolean[] config;
		public boolean[] burnedLeds;
		public String name;
		public LedConfig(boolean[] config, boolean[] burnedLeds, String name){
			this.config = config;
			this.burnedLeds = burnedLeds;
			this.name = name;
		}
		public String toString(){
			return name;
		}
	}
	
	public static void main(String[] args){
		String data = "1\r\n" + 
				"YYYYNYY\r\n" + 
				"2\r\n" + 
				"NNNNNNN\r\n" + 
				"NNNNNNN\r\n" + 
				"2\r\n" + 
				"YYYYYYY\r\n" + 
				"YYYYYYY\r\n" + 
				"3\r\n" + 
				"YNYYYYY\r\n" + 
				"YNYYNYY\r\n" + 
				"NYYNNYY\r\n" + 
				"3\r\n" + 
				"YNYYYYN\r\n" + 
				"YNYYNYN\r\n" + 
				"NYYNNYN\r\n" + 
				"3\r\n" + 
				"YNYYYYN\r\n" + 
				"YNYYNYN\r\n" + 
				"NYYNYYN\r\n" + 
				"4\r\n" + 
				"YYYYYYY\r\n" + 
				"NYYNNNN\r\n" + 
				"NNYYYYN\r\n" + 
				"NNNYNNN\r\n" + 
				"3\r\n" + 
				"NNNNNNN\r\n" + 
				"YNNNNNN\r\n" + 
				"NNNNYNN\r\n" + 
				"0";
		
		String data2 = "4\r\nYYYYYNN\r\nYYYNNNN\r\nYNYYYNN\r\nYNYYNNN\r\n5\r\nYYYYYYY\r\nNNNNNNN\r\nNNNNNNN\r\nNNNNNNN\r\nNNNNNNN\r\n1\r\nYYYYYYY\r\n1\r\nNNNNNNN\r\n4\r\nYYYYYNN\r\nYYYNNNN\r\nYYYYYNN\r\nYNYYNNN\r\n2\r\nYYYYYYY\r\nNNNNYYY\r\n2\r\nYYYNNNN\r\nYYYNNNN\r\n2\r\nYNNYNNY\r\nYNNYNNY\r\n10\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\n10\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNYNNNN\r\nNNNNNNN\r\nNNYNNNN\r\nNNYNNNN\r\n";
		String data3 = "2\nYYYNNNN\nYYYYYYY\n0";
		
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			if(n == 0) break;
			boolean[][] configs = new boolean[n][];
			for(int i = 0; i < n; i++){
				String line = scanner.next();
				configs[i] = new boolean[line.length()];
				for(int j = 0; j < line.length(); j++){
					configs[i][j] = line.charAt(j) == 'Y' ? true : false;
				}
			}
			if(isValidSequence(configs)){
				System.out.println("MATCH");
			}else System.out.println("MISMATCH");
		}
		scanner.close();
	}
	
	static boolean isValidSequence(boolean[][] configs){
		return isValidSequence(configs, 0, Integer.MIN_VALUE);
	}
	
	static boolean isValidSequence(boolean[][] configs, int currentConfig, int countdownStatus){
		if(currentConfig == configs.length){
			return true;
		}
		
		for(LedConfig config : deriveValidConfigs(configs[currentConfig])){
			if(!configContradictory(config, configs, currentConfig)){
				int digit = map.get(config.name);
				if(countdownStatus == Integer.MIN_VALUE || digit == countdownStatus - 1){
					if(isValidSequence(configs, currentConfig + 1, digit)){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	static boolean configContradictory(LedConfig config, boolean[][] configs, int currentConfig){
		for(int k = currentConfig + 1; k < configs.length; k++){
			for(int i = 0; i < config.burnedLeds.length; i++){
				if(config.burnedLeds[i] && configs[k][i]){

					return true;
				}
			}
		}
		
		return false;
	}
	
	static List<LedConfig> deriveValidConfigs(boolean[] config){
		boolean[] derivedConfig = new boolean[config.length];
		boolean[] burnedLeds = new boolean[config.length];
		List<LedConfig> configs = new ArrayList<LedConfig>();
		deriveValidConfigs(config, 0, derivedConfig, burnedLeds, "", configs);
		return configs;
	}
	
	static void deriveValidConfigs(
			boolean[] config, 
			int current, 
			boolean[] derivedConfig,
			boolean[] burnedLeds,
			String name,
			List<LedConfig> configs){
		if(current == config.length){
			if(map.containsKey(name)){
				configs.add(new LedConfig(
						Arrays.copyOf(derivedConfig, derivedConfig.length), 
						Arrays.copyOf(burnedLeds, burnedLeds.length), 
						name));
			}
			return;
		}
		
		if(config[current]){
			derivedConfig[current] = true;
			deriveValidConfigs(config, current + 1, derivedConfig, burnedLeds, name + "Y", configs);
		}else{
			deriveValidConfigs(config, current + 1, derivedConfig, burnedLeds, name + "N", configs);
			derivedConfig[current] = true;
			burnedLeds[current] = true;
			deriveValidConfigs(config, current + 1, derivedConfig, burnedLeds, name + "Y", configs);
		}
		
		derivedConfig[current] = false;
		burnedLeds[current] = false;
	}
}