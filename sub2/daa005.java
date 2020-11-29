import java.util.*;

class daa005{
  public static void main(String[] args){
    FastScanner stdin = new FastScanner(System.in);
    int size = stdin.nextInt();
    int[] energy = new int[size + 1];

    for(int i = 1; i < size + 1; i++){
      int k = stdin.nextInt();
      energy[i] = energy[i-1] + k;
    }

    int f = stdin.nextInt();
    for(int i = 0; i < f; i++){
      int a = stdin.nextInt();
      int b = stdin.nextInt();
      FastPrint.out.println(energy[b] - energy[a-1]);
    }
    FastPrint.out.close();
    //System.out.println(Arrays.toString(energy));
  }
}
