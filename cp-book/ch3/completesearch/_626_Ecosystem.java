import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 626 Ecosystem
Problem url: https://uva.onlinejudge.org/external/6/626.pdf
Author: Andrey Yemelyanov
*/
public class _626_Ecosystem {
  static class FoodChain{
    public int species1;
    public int species2;
    public int species3;
    public FoodChain(int species1, int species2, int species3){
      intializeSorted(species1, species2, species3);
    }
    private void intializeSorted(int species1, int species2, int species3){
      if(isSorted(species1, species2, species3)){
        this.species1 = species1;
        this.species2 = species2;
        this.species3 = species3;
      }else if(isSorted(species2, species3, species1)){
        this.species1 = species2;
        this.species2 = species3;
        this.species3 = species1;
      }else{
        this.species1 = species3;
        this.species2 = species1;
        this.species3 = species2;
      }
    }
    private boolean isSorted(int species1, int species2, int species3){
      return (species1 < species2 && species2 < species3) || (species1 > species2 && species2 > species3);
    }
    @Override
    public String toString(){
      return (species1 + 1) + " " + (species2 + 1) + " " + (species3 + 1);
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int nSpecies = s.nextInt();
      int[][] coincidenceMatrix = new int[nSpecies][nSpecies];
      for(int i = 0; i < nSpecies; i++){
        for(int j = 0; j < nSpecies; j++){
          coincidenceMatrix[i][j] = s.nextInt();
        }
      }
      Set<FoodChain> cyclicChains = find3MemberFoodChains(coincidenceMatrix);
      if(cyclicChains.size() > 0) System.out.println(cyclicChains.stream().map(Object::toString).collect(Collectors.joining("\n")));
      System.out.println("total:" + cyclicChains.size());
      System.out.println();
    }
  }

  static Set<FoodChain> find3MemberFoodChains(int[][] coincidenceMatrix){
    Set<FoodChain> s = new TreeSet<>(new Comparator<FoodChain>(){
      @Override
      public int compare(FoodChain fc1, FoodChain fc2){
        if(fc1.species1 != fc2.species1) return Integer.compare(fc1.species1, fc2.species1);
        if(fc1.species2 != fc2.species2) return Integer.compare(fc1.species2, fc2.species2);
        return Integer.compare(fc1.species3, fc2.species3);
      }
    });
    int nSpecies = coincidenceMatrix.length;
    for(int species1 = 0; species1 < nSpecies; species1++){
      for(int species2 = 0; species2 < nSpecies; species2++){
        for(int species3 = 0; species3 < nSpecies; species3++){
          if(species1 != species2 && species2 != species3 && species3 != species1){
            if(foodChainCyclic(species1, species2, species3, coincidenceMatrix)){
              s.add(new FoodChain(species1, species2, species3));
            }
          }
        }
      }
    }
    return s;
  }

  static boolean foodChainCyclic(int species1, int species2, int species3, int[][] coincidenceMatrix){
    return coincidenceMatrix[species1][species2] == 1 && coincidenceMatrix[species2][species3] == 1 && coincidenceMatrix[species3][species1] == 1;
  }
}
