package helvidios.cp.ch4.traversal;

import java.util.*;

import static java.lang.Math.*;

public class _11831_StickerCollector {
    public static void main(String[] args) {
        String data = "3 3 2\n" +
                "***\n" +
                "*N*\n" +
                "***\n" +
                "DE\n" +
                "4 4 5\n" +
                "...#\n" +
                "*#O.\n" +
                "*.*.\n" +
                "*.#.\n" +
                "FFEFF\n" +
                "10 10 20\n" +
                "....*.....\n" +
                ".......*..\n" +
                ".....*....\n" +
                "..*.#.....\n" +
                "...#N.*..*\n" +
                "...*......\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "FDFFFFFFEEFFFFFFEFDF\n" +
                "0 0 0\n";
        Scanner scanner = new Scanner(data);
        while(scanner.hasNext()){
            int n = scanner.nextInt(); int m = scanner.nextInt(); int s = scanner.nextInt();
            if(n == 0 && m == 0 && s == 0) break;
            scanner.nextLine();
            char[][] arena = new char[n][m];
            for(int i = 0; i < n; i++){
                arena[i] = scanner.nextLine().toCharArray();
            }
            char[] instructions = scanner.nextLine().toCharArray();
            System.out.println(collectStickers(arena, instructions));
        }
        scanner.close();
    }

    static final char EMPTY = '.';
    static final char STICKER = '*';
    static final char PILLAR = '#';
    static final char NORTH = 'N';
    static final char SOUTH = 'S';
    static final char EAST = 'L';
    static final char WEST = 'O';
    static final char RIGHT = 'D';
    static final char LEFT = 'E';
    static final char FORWARD = 'F';

    static int collectStickers(char[][] arena, char[] instructions){
        int nStickers = 0;
        int[] startingPos = startingPos(arena);
        int row = startingPos[0]; int col = startingPos[1];
        char dir = arena[row][col];
        for(char instruction : instructions){
            switch (instruction){
                case RIGHT:
                    dir = turnRobotRight(dir);
                    break;
                case LEFT:
                    dir = turnRobotLeft(dir);
                    break;
                case FORWARD:
                    int[] newCoords = moveRobotForward(arena, row, col, dir);
                    row = newCoords[0]; col = newCoords[1];
                    if(arena[row][col] == STICKER){
                        nStickers++;
                        arena[row][col] = EMPTY;
                    }
                    break;
            }
        }
        return nStickers;
    }

    static char turnRobotLeft(char dir){
        switch (dir){
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            default:
                return SOUTH;
        }
    }

    static char turnRobotRight(char dir){
        switch (dir){
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            default:
                return NORTH;
        }
    }

    static int[] moveRobotForward(char[][] arena, int fromRow, int fromCol, char dir){
        int row = fromRow; int col = fromCol;
        switch (dir){
            case NORTH:
                row--;
                break;
            case EAST:
                col++;
                break;
            case SOUTH:
                row++;
                break;
            case WEST:
                col--;
                break;
        }

        if(row < 0 || row >= arena.length || col < 0 || col >= arena[0].length) {
            return new int[] {fromRow, fromCol};
        }

        if(arena[row][col] == PILLAR){
            return new int[] {fromRow, fromCol};
        }

        return new int[] {row, col};
    }

    static int[] startingPos(char[][] arena){
        for(int i = 0; i < arena.length; i++){
            for(int j = 0; j < arena[0].length; j++){
                if(Arrays.asList(NORTH, SOUTH, EAST, WEST).contains(arena[i][j])){
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }
}
