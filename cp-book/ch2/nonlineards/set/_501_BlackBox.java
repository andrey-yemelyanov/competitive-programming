import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 501 Black Box
Problem url: https://uva.onlinejudge.org/external/5/501.pdf
Author: Andrey Yemelyanov
*/
public class _501_BlackBox {
  static <T> void println(){System.out.println();}
  static <T> void println(T item){System.out.println(item);}
  static <T> void print(T item){System.out.print(item);}
  static class BlackBox{
    private int size = 0;
    private int i = 1;
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    public void add(int item){
      maxHeap.add(item);
      if(maxHeap.size() > i) minHeap.add(maxHeap.poll());
      size++;
      //println("added " + item + " " + toString());
    }
    public int get(){
      //println("get " + toString());
      int item = maxHeap.peek();
      i++;
      if(minHeap.size() > 0) maxHeap.add(minHeap.poll());
      return item;
    }
    public int size(){return size;}
    public String toString(){
      return String.format("maxHeap=%s, minHeap=%s", maxHeap.toString(), minHeap.toString());
    }
  }

  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int t = 0; t < nTests; t++){
      s.nextLine();
      int M = s.nextInt();
      int N = s.nextInt();
      int[] A = new int[M];
      for(int i = 0; i < M; i++){
        A[i] = s.nextInt();
      }
      int[] u = new int[N];
      for(int i = 0; i < N; i++){
        u[i] = s.nextInt();
      }
      println(
        runSimulation(A, u).stream()
                           .map(Object::toString)
                           .collect(Collectors.joining("\n"))
      );
      if(t < nTests - 1) println();
    }
  }

  static List<Integer> runSimulation(int[] A, int[] u){
    List<Integer> list = new ArrayList<>();
    BlackBox box = new BlackBox();
    Map<Integer, Integer> getMap = new HashMap<>();
    for(int getCommand : u){
      if(!getMap.containsKey(getCommand)){
        getMap.put(getCommand, 0);
      }
      getMap.put(getCommand, getMap.get(getCommand) + 1);
    }
    for(int item : A){
      box.add(item);
      if(getMap.containsKey(box.size())){
        int repeatGet = getMap.get(box.size());
        for(int i = 0; i < repeatGet; i++){
          list.add(box.get());
        }
      }
    }
    return list;
  }
}
