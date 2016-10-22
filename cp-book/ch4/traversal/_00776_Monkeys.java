package helvidios.cp.ch4.traversal;

import java.util.*;

import static java.lang.Math.*;

public class _00776_Monkeys {
    public static void main(String[] args) {
        String data = "A B D E C C D\n" +
                "F F W D D D D\n" +
                "P W E W W W W\n" +
                "%\n" +
                "a A b B c d E t\n" +
                "a a a a a c c t\n" +
                "e f g h c a a t\n";
        Scanner scanner = new Scanner(data);
        while(scanner.hasNext()){
            List<String> lines = new ArrayList<>();
            while(scanner.hasNext()){
                String line = scanner.nextLine().trim().replace(" ", "");
                if(line.equals("%"))
                {
                    break;
                }
                lines.add(line);
            }
            char[][] grid = new char[lines.size()][lines.get(0).length()];
            for(int i = 0; i < grid.length; i++){
                grid[i] = lines.get(i).toCharArray();
            }
            int[][] map = releaseMonkeys(grid);
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[0].length; j++){
                    int len = maxLength(map, j);
                    if(j > 0) len++;
                    System.out.printf("%" + len + "d", map[i][j]);
                }
                System.out.println();
            }
            System.out.println("%");
        }
        scanner.close();
    }

    static int maxLength(int[][] map, int col){
        int maxLength = 0;
        for(int i = 0; i < map.length; i++){
            maxLength = max(maxLength, Integer.toString(map[i][col]).length());
        }
        return maxLength;
    }

    static int[][] releaseMonkeys(char[][] grid){
        int[][] map = new int[grid.length][grid[0].length];
        int id = 1;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(map[i][j] == 0){
                    floodfill(grid, map, i, j, grid[i][j], id);
                    id++;
                }
            }
        }
        return map;
    }

    static final int[] dr = new int[]{-1,-1,-1,0,1,1,1,0};
    static final int[] dc = new int[]{-1,0,1,1,1,0,-1,-1};
    static void floodfill(char[][] grid, int[][] map, int row, int col, char color, int id){
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;
        if(grid[row][col] != color) return;
        if(map[row][col] != 0) return;
        map[row][col] = id;
        for(int d = 0; d < 8; d++){
            floodfill(grid, map, row + dr[d], col + dc[d], color, id);
        }
    }
}
