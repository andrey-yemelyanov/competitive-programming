import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 437 The Tower of Babylon
Problem url: https://uva.onlinejudge.org/external/4/437.pdf
Author: Andrey Yemelyanov
*/
public class _437_TheTowerOfBabylon {
  static class Block{
    private int base1;
    private int base2;
    public int height;
    public boolean hasGreaterBaseThan(Block block){
      return (base1 > block.base1 && base2 > block.base2) || (base1 > block.base2 && base2 > block.base1);
    }
    public Block(int base1, int base2, int height){
      this.base1 = base1;
      this.base2 = base2;
      this.height = height;
    }
    public String toString(){
      return String.format("(%d, %d) %d", base1, base2, height);
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int t = 1;
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      int[][] b = new int[n][3];
      for(int i = 0; i < n; i++){
        for(int j = 0; j < 3; j++){
          b[i][j] = s.nextInt();
        }
      }
      System.out.printf("Case %d: maximum height = %d\n", t++, highestTower(getBlocks(b)));
    }
  }

  static Block[] getBlocks(int[][] b){
    Block[] blocks = new Block[b.length * 6];
    for(int i = 0; i < b.length; i++){
      int x = b[i][0]; int y = b[i][1]; int z = b[i][2];
      blocks[i * 6] =     new Block(x, y, z);
      blocks[i * 6 + 1] = new Block(x, z, y);
      blocks[i * 6 + 2] = new Block(y, x, z);
      blocks[i * 6 + 3] = new Block(y, z, x);
      blocks[i * 6 + 4] = new Block(z, x, y);
      blocks[i * 6 + 5] = new Block(z, y, x);
    }
    Arrays.sort(blocks, new Comparator<Block>(){
      @Override
      public int compare(Block b1, Block b2){
        if(b1.base1 != b2.base1) return Integer.compare(b1.base1, b2.base1);
        if(b1.base2 != b2.base2) return Integer.compare(b1.base2, b2.base2);
        return Integer.compare(b1.height, b2.height);
      }
    });
    return blocks;
  }

  static int highestTower(Block[] blocks){
    //System.out.println(Arrays.toString(blocks));
    int[] memo = new int[blocks.length];
    Arrays.fill(memo, -1);
    int highestTower = 0;
    for(int i = 0; i < blocks.length; i++){
      highestTower = max(highestTower, highestTower(blocks, i, memo));
    }
    return highestTower;
  }

  static int highestTower(Block[] blocks, int i, int[] memo){
    if(i == 0) return blocks[i].height;
    if(memo[i] != -1) return memo[i];
    int highestTower = blocks[i].height;
    for(int j = 0; j < i; j++){
      if(blocks[i].hasGreaterBaseThan(blocks[j])){
        highestTower = max(highestTower, blocks[i].height + highestTower(blocks, j, memo));
      }
    }
    return memo[i] = highestTower;
  }
}
