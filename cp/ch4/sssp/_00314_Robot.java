package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;

/**
    Idea for the algorithm: http://ibra.hammadian.com/home/uva-solutions/314-robot
**/
public class _00314_Robot {
    public static class State{
        public int row;
        public int col;
        public int direction;
        public int distance;
        State(int row, int col, int direction, int distance){
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.distance = distance;
        }
    }
    public static void main(String[] args) {
        String data = "9 10\n" +
                "0 0 0 0 0 0 1 0 0 0\n" +
                "0 0 0 0 0 0 0 0 1 0\n" +
                "0 0 0 1 0 0 0 0 0 0\n" +
                "0 0 1 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 1 0 0 0\n" +
                "0 0 0 0 0 1 0 0 0 0\n" +
                "0 0 0 1 1 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "1 0 0 0 0 0 0 0 1 0\n" +
                "7 2 2 7 south\n" +
                "0 0\n";

        String data4 = "3 3\n" +
                "0 0 0\n" +
                "0 0 0\n" +
                "0 0 0\n" +
                "2 2 1 1 east\n" +
                "0 0";
        String data5 = "9 10\n" +
                "0 0 0 0 0 0 1 0 0 0\n" +
                "0 0 0 0 0 0 0 0 1 0\n" +
                "0 0 0 1 0 0 0 0 0 0\n" +
                "0 0 1 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 1 0 0 0\n" +
                "0 0 0 0 0 1 0 0 0 0\n" +
                "0 0 0 1 1 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "1 0 0 0 0 0 0 0 1 0\n" +
                "7 2 2 7 south\n" +
                "2 3\n" +
                "0 0 0\n" +
                "0 0 0\n" +
                "1 1 1 2 west\n" +
                "5 5\n" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n" +
                "0 1 1 1 0\n" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n" +
                "1 1 4 4 east\n" +
                "2 3\n" +
                "0 0 0\n" +
                "0 0 1\n" +
                "1 1 1 2 east\n" +
                "0 0";
        String data6 = "31 7\r\n1 1 0 1 0 0 1\r\n1 1 0 0 1 0 0\r\n0 1 1 1 1 0 0\r\n1 0 1 1 0 1 0\r\n1 1 0 0 0 1 0\r\n0 1 1 1 0 1 0\r\n0 1 0 0 0 1 1\r\n0 1 1 1 1 0 1\r\n1 1 1 0 0 0 0\r\n0 1 0 1 0 1 0\r\n0 0 0 0 1 0 0\r\n0 1 1 0 0 0 1\r\n1 0 0 0 1 0 0\r\n1 0 1 0 1 1 1\r\n1 1 1 1 1 0 0\r\n1 0 0 1 0 0 1\r\n0 0 0 0 1 1 0\r\n0 1 1 0 1 0 0\r\n0 1 1 1 0 0 0\r\n0 0 0 1 1 1 0\r\n1 1 1 1 1 1 1\r\n0 0 0 1 1 1 1\r\n0 1 1 0 0 0 1\r\n1 0 0 1 0 0 0\r\n0 1 0 1 0 1 0\r\n0 0 0 0 1 0 1\r\n0 1 0 1 0 1 1\r\n0 1 1 1 0 1 0\r\n1 1 0 1 1 0 0\r\n1 1 0 1 0 0 0\r\n1 0 1 1 1 0 0\r\n23 5 22 4 east\r\n0 0";
        String data7 = "4 4\n0 0 0 0\n0 0 0 0\n0 0 0 0\n0 0 0 0\n1 1 3 3 east";
        Scanner scanner = new Scanner(data);
        while(scanner.hasNext()){
            int M = scanner.nextInt(); int N = scanner.nextInt();
            if(M == 0 && N == 0) break;
            int[][] grid = new int[M][N];
            for(int i = 0; i < M; i++){
                for(int j = 0; j < N; j++){
                    grid[i][j] = scanner.nextInt();
                }
            }
            int[][] modifiedGrid = transform(grid);
            int[] source = new int[]{scanner.nextInt(), scanner.nextInt()};
            int[] dest = new int[]{scanner.nextInt(), scanner.nextInt()};
            int direction;
            switch (scanner.next().trim()){
                case "north":
                    direction = UP;
                    break;
                case "west":
                    direction = LEFT;
                    break;
                case "east":
                    direction = RIGHT;
                    break;
                default:
                    direction = DOWN;
                    break;
            }
            if(modifiedGrid[source[0]][source[1]] != OBSTACLE && modifiedGrid[dest[0]][dest[1]] != OBSTACLE){
                System.out.println(bfs(modifiedGrid, 
                    new State(source[0], source[1], direction, 0), 
                    dest[0], dest[1]));
            }else System.out.println(INF);
        }
        scanner.close();
    }

    public static int bfs(int[][] grid, State source, int destRow, int destCol){
        Queue<State> q = new LinkedList<>();
        boolean[][][] visited = new boolean[grid.length][grid[0].length][6];
        visited[source.row][source.col][source.direction] = true;
        q.add(source);
        while(!q.isEmpty()){
            State state = q.remove();
            if(state.row == destRow && state.col == destCol) return state.distance;
            // max 5 possible state transitions (with distance=1) from current state:
            // turn left(t1), right(t2) from current direction and go forward 1(t3), 2(t4), 3(t5) meters in the current direction

            // turn left
            if(!visited[state.row][state.col][turnLeft(state.direction)]){
                visited[state.row][state.col][turnLeft(state.direction)] = true;
                q.add(new State(state.row, state.col, turnLeft(state.direction), state.distance + 1));
            }

            // turn right
            if(!visited[state.row][state.col][turnRight(state.direction)]){
                visited[state.row][state.col][turnRight(state.direction)] = true;
                q.add(new State(state.row, state.col, turnRight(state.direction), state.distance + 1));
            }

            // go forward 1, 2 and 3 meters
            for(int i = 1; i <= 3; i++){
                int newRow = nextRow(state.direction, state.row, i);
                int newCol = nextCol(state.direction, state.col, i);
                if(newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length) break;
                if(grid[newRow][newCol] == OBSTACLE) break;
                if(!visited[newRow][newCol][state.direction]){
                    visited[newRow][newCol][state.direction] = true;
                    q.add(new State(newRow, newCol, state.direction, state.distance + 1));
                }
            }
        }
        return INF;
    }

    static int nextRow(int direction, int currentRow, int nSteps){
        switch(direction){
            case UP:
                return currentRow - nSteps;
            case LEFT:
                return currentRow;
            case RIGHT:
                return currentRow;
            default:
                return currentRow + nSteps;
        }
    }

    static int nextCol(int direction, int currentCol, int nSteps){
        switch(direction){
            case UP:
                return currentCol;
            case LEFT:
                return currentCol - nSteps;
            case RIGHT:
                return currentCol + nSteps;
            default:
                return currentCol;
        }
    }

    static int turnLeft(int direction){
        return ((direction - 1) % 4) + 2;
    }

    static int turnRight(int direction){
        return ((direction + 1) % 4) + 2;
    }

    static int[][] surroundingCells(int row, int col){
        return new int[][]{
                {row - 1, col - 1},
                {row - 1, col},
                {row, col - 1},
                {row, col}
        };
    }

    static final int INF = -1;
    static final int OBSTACLE = 1;
    static final int UP = 2;
    static final int LEFT = 3;
    static final int DOWN = 4;
    static final int RIGHT = 5;
    static int[][] transform(int[][] grid){
        int[][] modifiedGrid = new int[grid.length + 1][grid[0].length + 1];
        for(int i = 0; i < modifiedGrid.length; i++){
            for(int j = 0; j < modifiedGrid[0].length; j++){
                if(i == 0 || i == modifiedGrid.length - 1) modifiedGrid[i][j] = OBSTACLE;
                else if(j == 0 || j == modifiedGrid[0].length - 1) modifiedGrid[i][j] = OBSTACLE;
                else{
                    for(int[] c : surroundingCells(i, j)){
                        if(grid[c[0]][c[1]] == OBSTACLE){
                            modifiedGrid[i][j] = OBSTACLE;
                        }
                    }
                }
            }
        }
        return modifiedGrid;
    }
}
