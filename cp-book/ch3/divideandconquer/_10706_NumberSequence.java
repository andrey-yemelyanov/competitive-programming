import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 10706 Number Sequence
Problem url: https://uva.onlinejudge.org/external/107/10706.pdf
Author: Andrey Yemelyanov
*/
public class _10706_NumberSequence {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    List<Long> sums = precalcDigitSums(Integer.MAX_VALUE);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int i = s.nextInt();
      int group = lowerBound(sums, i);
      if(i == sums.get(group)){
        List<Integer> seq = buildDigitSequence(group + 1);
        System.out.println(last(seq));
      }else{
        List<Integer> seq = buildDigitSequence(group + 2);
        System.out.println(seq.get(i - sums.get(group).intValue() - 1));
      }
    }
  }

  static List<Integer> buildDigitSequence(int max){
    List<Integer> seq = new ArrayList<>();
    for(int i = 1; i <= max; i++){
      seq.addAll(getDigits(i));
    }
    return seq;
  }

  static List<Integer> getDigits(int n){
    if(n == 0) return Arrays.asList(0);
    List<Integer> digits = new ArrayList<>();
    while(n != 0){
      digits.add(n % 10);
      n /= 10;
    }
    Collections.reverse(digits);
    return digits;
  }

  /*
    Returns index of the largest element less than or equal to key in arr
  */
  static int lowerBound(List<Long> arr, long key){
    int from = 0;
    int to = arr.size() - 1;
    while(to >= from){
      int mid = from + (to - from) / 2;
      if(arr.get(mid) == key){
        if(mid + 1 == arr.size()) return mid;
        if(arr.get(mid + 1) > key) return mid;
        else from = mid + 1;
      }else if(arr.get(mid) < key){
        if(mid + 1 == arr.size() || arr.get(mid + 1) > key) return mid;
        else from = mid + 1;
      }else{
        to = mid - 1;
      }
    }
    return to;
  }

  static long countDigits(int n){
    if(n == 0) return 1;
    int nDigits = 0;
    while(n != 0){
      nDigits++;
      n /= 10;
    }
    return nDigits;
  }

  static List<Long> precalcDigitSums(int boundary){
    List<Long> sums = new ArrayList<>();
    List<Integer> nDigits = new ArrayList<>();
    sums.add(1L);
    nDigits.add(1);
    int i = 2;
    while(last(sums) <= boundary){
      long digitCount = countDigits(i++);
      sums.add(digitCount + last(nDigits) + last(sums));
      nDigits.add((int)(last(nDigits) + digitCount));
    }
    return sums;
  }

  static <T> T last(List<T> list){
    return list.get(list.size() - 1);
  }
}
