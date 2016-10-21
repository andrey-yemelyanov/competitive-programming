package helvidios.cp.ch2.lineards._2darraymanip;

import java.util.Scanner;

public class _466_MirrorMirror {
	public static final int _90_DEG_ROTATON = 1;
	public static final int _180_DEG_ROTATON = 2;
	public static final int _270_DEG_ROTATON = 3;
	public static final int VERTICAL_REFLECTION = 4;
	public static final int PRESERVATION = 5;
	public static final int IMPROPER = 6;
	public static final int COMBINATION_90DEG = 7;
	public static final int COMBINATION_180DEG = 8;
	public static final int COMBINATION_270DEG = 9;
	
	public static void main(String... args){
		String data = "5\r\n" + 
				"X...X ....X \r\n" + 
				".X... ...X.\r\n" + 
				"...X. .X...\r\n" + 
				"..X.X ..X..\r\n" + 
				"....X XX..X\r\n" + 
				"6\r\n" + 
				"....XX X....X\r\n" + 
				"...X.. X.X...\r\n" + 
				"XX..X. .X..X.\r\n" + 
				"..X... ...X.X\r\n" + 
				"...X.. ..X...\r\n" + 
				"..X..X ..X...\r\n" + 
				"2\r\n" + 
				"X. X.\r\n" + 
				".X .X\r\n" + 
				"4 \r\n" + 
				"..X. ...X\r\n" + 
				"XX.. ....\r\n" + 
				".... XX..\r\n" + 
				"...X ..X.\r\n" + 
				"5\r\n" + 
				"X.... .X...\r\n" + 
				".X... ..X..\r\n" + 
				".X... ..X..\r\n" + 
				"...X. ....X\r\n" + 
				"....X X....\r\n" + 
				"4\r\n" + 
				".X.. ..X.\r\n" + 
				".X.X X...\r\n" + 
				".... ..XX\r\n" + 
				"..X. ....\r\n" + 
				"2\r\n" + 
				".. XX\r\n" + 
				"XX ..\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int index = 0;
		while(scanner.hasNext()){
			int dimension = scanner.nextInt();
			int[][] original = new int[dimension][dimension];
			int[][] transformed = new int[dimension][dimension];
			for(int i = 0; i < dimension; i++){
				char[] originalRow = scanner.next().toCharArray();
				char[] transformedRow = scanner.next().toCharArray();
				for(int j = 0; j < dimension; j++){
					original[i][j] = originalRow[j] == 'X' ? 1 : 0;
					transformed[i][j] = transformedRow[j] == 'X' ? 1 : 0;
				}
			}
			int result = findTransformationType(original, transformed);
			switch (result) {
				case _90_DEG_ROTATON:
					System.out.printf("Pattern %1$d was rotated 90 degrees.\n", ++index);
					break;
				case _180_DEG_ROTATON:
					System.out.printf("Pattern %1$d was rotated 180 degrees.\n", ++index);
					break;
				case _270_DEG_ROTATON:
					System.out.printf("Pattern %1$d was rotated 270 degrees.\n", ++index);
					break;
				case VERTICAL_REFLECTION:
					System.out.printf("Pattern %1$d was reflected vertically.\n", ++index);
					break;
				case PRESERVATION:
					System.out.printf("Pattern %1$d was preserved.\n", ++index);
					break;
				case IMPROPER:
					System.out.printf("Pattern %1$d was improperly transformed.\n", ++index);
					break;
				case COMBINATION_90DEG:
					System.out.printf("Pattern %1$d was reflected vertically and rotated 90 degrees.\n", ++index);
					break;
				case COMBINATION_180DEG:
					System.out.printf("Pattern %1$d was reflected vertically and rotated 180 degrees.\n", ++index);
					break;
				case COMBINATION_270DEG:
					System.out.printf("Pattern %1$d was reflected vertically and rotated 270 degrees.\n", ++index);
					break;
				default:
					break;
			}
		}
		scanner.close();
	}
	
	public static int findTransformationType(int[][] originalPattern, int[][] transformedPattern){
		if(patternsIdentical(originalPattern, transformedPattern)) return PRESERVATION;
		if(patternsIdentical(rotate(originalPattern), transformedPattern)) return _90_DEG_ROTATON;
		if(patternsIdentical(rotate(rotate(originalPattern)), transformedPattern)) return _180_DEG_ROTATON;
		if(patternsIdentical(rotate(rotate(rotate(originalPattern))), transformedPattern)) return _270_DEG_ROTATON;
		if(patternsIdentical(reflect(originalPattern), transformedPattern)) return VERTICAL_REFLECTION;
		originalPattern = reflect(originalPattern);
		if(patternsIdentical(rotate(originalPattern), transformedPattern)) return COMBINATION_90DEG;
		if(patternsIdentical(rotate(rotate(originalPattern)), transformedPattern)) return COMBINATION_180DEG;
		if(patternsIdentical(rotate(rotate(rotate(originalPattern))), transformedPattern)) return COMBINATION_270DEG;
		return IMPROPER;
	}
	
	public static int[][] rotate(int[][] pattern){
		int[][] rotated = new int[pattern.length][pattern[0].length];
		int nLayers = pattern.length / 2;
		for(int layer = 0; layer < nLayers; layer++){
			int upperRow = layer;
			int lowerRow = rotated.length - layer - 1;
			int rightCol = rotated[0].length - layer - 1;
			int leftCol = layer;
			
			// copy upper row to right column
			for(int i = leftCol; i <= rightCol; i++){
				rotated[i][rightCol] = pattern[upperRow][i];
			}
			
			// copy right column to lower row
			for(int i = upperRow; i <= lowerRow; i++){
				rotated[lowerRow][rightCol - i + layer] = pattern[i][rightCol];
			}
			
			// copy lower row to left column
			for(int i = leftCol; i <= rightCol; i++){
				rotated[i][leftCol] = pattern[lowerRow][i];
			}
			
			// copy left column to upper row
			for(int i = upperRow; i <= lowerRow; i++){
				rotated[upperRow][rightCol - i + layer] = pattern[i][leftCol];
			}
		}
		
		if(pattern.length % 2 != 0) rotated[nLayers][nLayers] = pattern[nLayers][nLayers];
		
		return rotated;
	}
	
	public static int[][] reflect(int[][] pattern){
		int[][] reflected = new int[pattern.length][pattern[0].length];
		for(int row = 0; row < reflected.length; row++){
			for(int col = 0; col < reflected[0].length; col++){
				reflected[row][col] = pattern[pattern.length - row - 1][col];
			}
		}
		return reflected;
	}
	
	static boolean patternsIdentical(int[][] pattern1, int[][] pattern2){
		for(int row = 0; row < pattern1.length; row++){
			for(int col = 0; col < pattern1[0].length; col++){
				if(pattern1[row][col] != pattern2[row][col]) return false;
			}
		}
		return true;
	}
}
