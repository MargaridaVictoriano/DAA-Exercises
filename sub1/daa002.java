import java.util.Scanner;

public class daa002{
  public static int sum(int n){
    int sum = 0;
    while(n != 0){
      sum += n % 10;
      n /= 10;
    }
    return sum;
  }
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    int ncases = stdin.nextInt();
    int size = ncases*2;
    int[] num = new int[size];
    for(int i = 0; i < size; i++){
      num[i] = stdin.nextInt();
    }
    for(int i = 0; i < size; i+=2){
      int cur = num[i] + 1;
      int n = num[i+1];
      int sum = sum(cur);
      while(sum != n){
        ++cur;
        sum = sum(cur);
      }
      System.out.println(cur);
    }
  }
}
