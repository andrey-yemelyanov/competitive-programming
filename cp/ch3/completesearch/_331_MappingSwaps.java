package helvidios.cp.ch3.completesearch;

import java.util.Scanner;

public class _331_MappingSwaps {
	public static void main(String... args){
		String data = "2 9 7\r\n" + 
				"2 12 50\r\n" + 
				"3 3 2 1\r\n" + 
				"3 9 1 5\r\n" + 
				"0";
		Scanner scanner = new Scanner(data);
		int dataSet = 1;
		while(true){
			int n = scanner.nextInt();
			if(n == 0) break;
			int[] array = new int[n];
			for(int i = 0; i < n; i++){
				array[i] = scanner.nextInt();
			}
			int nSwapMaps = getNSwapMaps(array);
			System.out.printf("There are %1$d swap maps for input data set %2$d.\n", nSwapMaps, dataSet++);
		}
		scanner.close();
	}
	
	static boolean isSorted(int[] array){
		for(int i = 0; i < array.length - 1; i++){
			if(array[i] > array[i + 1]) return false;
		}
		return true;
	}
	
	static void swap(int[] array, int i1, int i2){
		int temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}
	
	static int getNSwapMaps(int[] array){
		minSwaps = Integer.MAX_VALUE;
		nSwapMaps = 0;
		buildSwapMaps(array, 0);
		return nSwapMaps;
	}
	
	static int minSwaps;
	static int nSwapMaps;
	static void buildSwapMaps(int[] array, int nSwaps){
		if(isSorted(array)){
			if(nSwaps == 0) nSwapMaps = 0;
			else if(nSwaps < minSwaps){
				minSwaps = nSwaps;
				nSwapMaps = 1;
			}else if(nSwaps == minSwaps){
				nSwapMaps++;
			}
			return;
		}
		
		for(int i = 0; i < array.length - 1; i++){
			if(array[i] > array[i + 1]){
				swap(array, i, i + 1);
				buildSwapMaps(array, nSwaps + 1);
				swap(array, i, i + 1);
			}
		}
	}
}
