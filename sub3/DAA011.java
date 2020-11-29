import java.io.*;
import java.util.*;

public class DAA011 {
  public static void main(String[] args) {
    FastScanner stdin = new FastScanner(System.in);
    int n = stdin.nextInt();
    int[] distances = new int[n];
    int maxDists = 0;

    for(int i = 0; i < n; i++){
      distances[i] = stdin.nextInt();
      maxDists += distances[i];
    }

    int question = stdin.nextInt();
    for(int i = 0; i < question; i++){
      int days = stdin.nextInt();
      FastPrint.out.println(bsearch(distances,0,maxDists,days));
    }

    FastPrint.out.close();
}

public static int bsearch(int[] haystack, int left, int right, int k){

  while(left < right){
    int middle = left + (right-left)/2;
    if(condition(haystack,middle,k)){
      right = middle;
    }
    else{
      left = middle + 1;
    }
  }
    return left;
}

  public static boolean condition(int[] distances, int needle, int k){
    int acc = 0;
    int partition = 0;
    for(int i : distances){
      acc += i;
      if(acc > needle){
        acc = i;
        if(i > needle) {
          return false;
        }
        if(partition++ > k){
          return false;
        }
      }
    }
    return partition < k;
  }
}
