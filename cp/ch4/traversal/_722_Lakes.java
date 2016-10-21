package helvidios.cp.ch4.traversal;

import java.util.*;

import static java.lang.Math.*;

public class _722_Lakes {
    public static void main(String[] args) {
        String data = "2\n" +
                "02 01\n" +
                "1001101\n" +
                "0011111\n" +
                "0001001\n" +
                "1100011\n" +
                "1111111\n" +
                "1100110\n" +
                "1110111\n\n" +
                "02 01\n" +
                "1001101\n" +
                "0011111\n" +
                "0001001\n" +
                "1100011\n" +
                "1111111\n" +
                "1100110\n" +
                "1110111\n";
        Scanner scanner = new Scanner(data);
        int nTests = scanner.nextInt();
        while(nTests-- > 0){
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            List<String> lines = new ArrayList<>();
            scanner.nextLine();
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                if(!line.isEmpty()) lines.add(line);
                else break;
            }
            char[][] grid = new char[lines.size()][lines.get(0).length()];
            for(int i = 0; i < grid.length; i++){
                grid[i] = lines.get(i).toCharArray();
            }
            System.out.println(floodfill(grid, row, col, '0', '2'));
            if(nTests > 0) System.out.println();
        }
        scanner.close();
    }

    static final int[] dr = new int[]{-1, 0, 1, 0};
    static final int[] dc = new int[]{0, 1, 0, -1};
    static int floodfill(char[][] grid, int row, int col, char color, char newColor){
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return 0;
        if(grid[row][col] != color) return 0;
        grid[row][col] = newColor;
        int size = 1;
        for(int d = 0; d < 4; d++){
            size += floodfill(grid, row + dr[d], col + dc[d], color, newColor);
        }
        return size;
    }
}
