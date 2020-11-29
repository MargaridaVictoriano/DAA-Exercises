import java.util.Scanner;

class daa001{
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int count = 0;
    for(int i = 0; i < n; i++){
      int k = stdin.nextInt();
      if(k == 42)
        count++;
    }
    System.out.println(count);
  }
}
