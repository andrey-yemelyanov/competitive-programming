package helvidios.cp.ch2.lineards._2darraymanip;

import java.util.Scanner;

public class _10920_SpiralTap {
	public static void main(String... args){
		String data = "3 1\r\n" + 
				"3 3\r\n" + 
				"3 9\r\n" + 
				"5 9\r\n" + 
				"5 10\r\n" + 
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"99999 9999800001\n" +
				"0 0\r\n" + 
				"\r\n" + 
				"";
		String data2 = "3 2\r\n" + 
				"1 1\r\n" + 
				"7 2\r\n" + 
				"5 7\r\n" + 
				"5 8\r\n" + 
				"5 9\r\n" + 
				"7 16\r\n" + 
				"7 49\r\n" + 
				"21 137\r\n" + 
				"511 3215\r\n" + 
				"1001 31415\r\n" + 
				"1001 314159\r\n" + 
				"999 987323\r\n" + 
				"2465 1048576\r\n" + 
				"5001 5000\r\n" + 
				"5001 20000000\r\n" + 
				"19999 12857152\r\n" + 
				"50001 123123124\r\n" + 
				"99999 1324859802\r\n" + 
				"99999 100000000\r\n" + 
				"99999 3521578\r\n" + 
				"99999 16515\r\n" + 
				"99999 1\r\n" + 
				"99999 2\r\n" + 
				"0 0";
		Scanner scanner = new Scanner(data2);
		StringBuilder builder = new StringBuilder();
		while(true){
			long dimension = scanner.nextLong();
			long position = scanner.nextLong();
			if(dimension == 0 && position == 0) break;
			long[] coord = toCartesianCoordinates(getLocationInGrid(dimension, position), dimension);
			builder.append(String.format("Line = %1$d, column = %2$d.\n", coord[1], coord[0]));
		}
		System.out.print(builder.toString());
		scanner.close();
	}
	
	public static long[] toCartesianCoordinates(long[] coord, long dimension){
		long y = dimension - coord[0];
		long x = coord[1] + 1;
		return new long[]{x, y};
	}
	
	public static long[] getLocationInGrid(long dimension, long position){
		long row = 0;
		long col = 0;
		long[] location = findPositionLayer(position);
		long layer = location[0];
		long center = (dimension - 1) / 2;
		long minRow = center - layer;
		long maxRow = center + layer;
		long minCol = center - layer;
		long maxCol = center + layer;
		
		long currentPosition = location[1] * location[1];
		// check right column
		for(long i = minRow; i <= maxRow; i++){
			if(currentPosition == position){
				return new long[]{i, maxCol};
			}
			currentPosition--;
		}
		
		// check bottom row
		for(long i = maxCol - 1; i >= minCol; i--){
			if(currentPosition == position){
				return new long[]{maxRow, i};
			}
			currentPosition--;
		}
		
		// check left column
		for(long i = maxRow - 1; i >= minRow; i--){
			if(currentPosition == position){
				return new long[]{i, minCol};
			}
			currentPosition--;
		}
		
		// check upper row
		for(long i = minCol + 1; i < maxCol; i++){
			if(currentPosition == position){
				return new long[]{minRow, i};
			}
			currentPosition--;
		}
		
		return new long[]{row, col};
	}
	
	public static long[] findPositionLayer(long position){
		long layer = 0;
		long maxPosition = 1;
		long dimension = 1;
		while(position > maxPosition){
			layer++;
			dimension += 2;
			maxPosition = dimension * dimension;
		}
		return new long[]{layer, dimension};
	}
}
