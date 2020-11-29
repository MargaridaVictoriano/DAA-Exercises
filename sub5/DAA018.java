import java.util.Scanner;

class DAA018{
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    int type = stdin.nextInt();
    int[] values = new int[type];
    int max = 0;
    for(int i = 0; i < type; i++){
      int k = stdin.nextInt();
      values[i] = k;
    }
    int q = stdin.nextInt();
    int[] quantity = new int[q];
    for(int i = 0; i < q; i++){
      int k = stdin.nextInt();
      if(k > max){
        max = k;
      }
      quantity[i] = k;
    }
    int[] use = new int[max+1];
    int[] first = new int[max+1];
    for(int i = 1; i < max+1; i++){
      use[i] = 10000;
      first[i] = values[0];
      for(int j = 0; j < type; j++){
        if(values[j] <= i && 1 + use[i-values[j]] < use[i]){
          boolean used = false;
          use[i] = 1 + use[i-values[j]];
          if(!used){
            first[i] = values[j];
            used = true;
          }
        }
      }
    }
    for(int i = 0; i < q; i++){
      int k = quantity[i];
      System.out.print(k + ": "+"[" + use[k] +"]");
      System.out.print(" " + first[k]);
      while(k != 0){
        k -= first[k];
        if(first[k] != 0){
          System.out.print(" " + first[k]);
        }
      }
      System.out.println();
    }
  }
}
