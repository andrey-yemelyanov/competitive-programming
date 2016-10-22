package helvidios.cp.ch3.dp;

import java.util.*;
import java.util.stream.*;
import static java.lang.Math.*;

public class _481_WhatGoesUp {
    public static void main(String[] args) {
        String data = "-7\n10\n9\n2\n3\n8\n8\n6";
        Scanner scanner = new Scanner(data);
        List<Integer> list = new ArrayList<>();
        while(scanner.hasNext()){
            list.add(scanner.nextInt());
        }
        int[] solution = lis(list);
        System.out.println(solution.length);System.out.println("-");
        System.out.println(toString(solution));

        /*List<Integer> list = Arrays.asList(-7,10,9,2,3,8,8,1);
		System.out.println(toString(lis(list)));
      	list = Arrays.asList(5,4,3,2,1);
		System.out.println(toString(lis(list)));*/
        scanner.close();
    }

    static String toString(int[] sequence){
        return Arrays.stream(sequence)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining("\n"));
    }

    static int[] lis(List<Integer> sequence){
        int maxLisLength = 0;
        List<Integer> l = new ArrayList<>();
        int[] p = new int[sequence.size()];
        l.add(0);
        for(int i = 1; i < sequence.size(); i++){
            int k = binarySearch(l, sequence, sequence.get(i));
            if(k < 0){
                k = -k - 1;
                if(k == l.size()) l.add(i);
                else l.set(k, i);
                if(k > 0) p[i] = l.get(k - 1);
                maxLisLength = max(maxLisLength, l.size());
            }
        }

        int[] solution = new int[maxLisLength];
        int k = l.get(maxLisLength - 1);
        for(int i = maxLisLength - 1; i >= 0; i--){
            solution[i] = sequence.get(k);
            k = p[k];
        }

        return solution;
    }

    static int binarySearch(List<Integer> list, List<Integer> sequence, int key){
        int from = 0; int to = list.size() - 1;
        while(from <= to){
            int mid = from + (to - from) / 2;
            if(sequence.get(list.get(mid)) == key) return mid; // key found
            if(sequence.get(list.get(mid)) < key){

                from = mid + 1;
            }else{
                to = mid - 1;
            }
        }
        // key not found
        return -from - 1;
    }
}