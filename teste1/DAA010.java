import java.util.Scanner;
import java.util.Arrays;
class DAA010{
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int[] set = new int[n];

    for(int i = 0; i < n; i++){
      int v = stdin.nextInt();
      set[i] = v;
    }

    int q = stdin.nextInt();
    int[] query = new int[q];
    for(int i = 0; i < q; i++){
      int v = stdin.nextInt();
      query[i] = v;
    }
    int nsomas = (n*n-n)/2;
    int[] somas = new int[nsomas];
    int aux = 0;
    for(int i = 0; i < n; i++){
      for(int j = i + 1; j <= n-1; j++){
        somas[aux] = set[i] + set[j];
        //System.out.println(somas[aux]);
        aux++;
      }
    }
    Arrays.sort(somas);
    System.out.println(Arrays.toString(somas));

  }
  public boolean bsearch(int[] v, int n, int k){
    int middle = left + (right - left)/2; //1
    int left = 0; //
    int right = n; //6

    // 0      1    2      3    4    5      6
    //false false false true true true  true
    //        ^
    while(left < right){
      if(v[middle]  ){
        right = middle;
      }
      else{
        left = middle + 1;
      }

    }
  }

}
