import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 1196 Tiling Up Blocks
Problem url: https://uva.onlinejudge.org/external/11/1196.pdf
Author: Andrey Yemelyanov
*/
public class _1196_TilingUpBlocks {
  static class Block{
    public int L;
    public int M;
    public Block(int L, int M){
      this.L = L;
      this.M = M;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      Block[] blocks = new Block[s.nextInt()];
      if(blocks.length == 0) break;
      for(int i = 0; i < blocks.length; i++){
        blocks[i] = new Block(s.nextInt(), s.nextInt());
      }
      System.out.println(tileBlocks(blocks));
    }
    System.out.println("*");
  }

  static int tileBlocks(Block[] blocks){
    Arrays.sort(blocks, new Comparator<Block>(){
      @Override
      public int compare(Block b1, Block b2){
        if(b1.L != b2.L) return Integer.compare(b1.L, b2.L);
        return Integer.compare(b1.M, b2.M);
      }
    });
    return lis(blocks);
  }

  static int lis(Block[] blocks){
    int[] memo = new int[blocks.length];
    int lisLength = 0;
    for(int i = 0; i < blocks.length; i++){
      lisLength = max(lisLength, lis(blocks, i, memo));
    }
    return lisLength;
  }

  static int lis(Block[] blocks, int i, int[] memo){
    if(i == 0) return 1;
    if(memo[i] != 0) return memo[i];
    int lisLength = 1;
    for(int j = 0; j < i; j++){
      if(blocks[j].M <= blocks[i].M){
        lisLength = max(lisLength, 1 + lis(blocks, j, memo));
      }
    }
    return memo[i] = lisLength;
  }
}
