import java.util.*;
class Daa010{
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int[] set = new int[n];

    for(int i = 0; i < n; i++){
      set[i] = stdin.nextInt();
    }
    int q = stdin.nextInt();
    int[] p = new int[q];

    for(int i = 0; i < q; i++){
      p[i] = stdin.nextInt();
    }

    int nsomas = (n*n-n)/2;
    int[] somas = new int[nsomas];
    int aux = 0;
    for(int i = 0; i < n; i++){
      for(int j = i + 1; j<=n-1; j++){
        somas[aux] = set[i] + set[j];
        aux++;
      }
    }
    Arrays.sort(somas);
    for(int i = 0; i < q; i++){
      System.out.println(bsearch(somas,0,somas.length - 2, p[i]));
    }

  }
  public static int bsearch(int[] v,int low,int high, int key){
      int middle=0;
      while(low<= high){
          middle= low + ((high-low)/2);
          if (key <=v[middle]){
              high= middle-1;
          }
          else{
              low = middle +1;
          }
      }

      if(high==-1)
          return v[0];

      if(Math.abs(v[low]-key) < Math.abs(key-v[high]))
          return v[low];
      else if(Math.abs(v[low]-key) == Math.abs(key-v[high])){
          System.out.print(v[high] + " " );
          return v[low];
      }

          return v[high];
  }
}
