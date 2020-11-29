import java.util.Scanner;

class DAA017{
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    long[][] pyramid = new long[n][n];
    int broken = stdin.nextInt();

    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        pyramid[i][j] = 0;
        if(i == n-1){
          pyramid[i][j] = 1;
        }
      }
    }
    for(int i = 0; i < broken; i++){
      int x = stdin.nextInt();
      int y = stdin.nextInt();
      pyramid[n-x][y-1] = -1;
    }
    for(int i = n-2; i >= 0; i--){
      for(int j = 0; j <= i; j++){
        if(pyramid[i][j] != -1){
          //left
          if(pyramid[i+1][j] != -1){
            pyramid[i][j] += pyramid[i+1][j];
          }
          //right
          if(pyramid[i+1][j+1] != -1){
            pyramid[i][j] += pyramid[i+1][j+1];
          }
        }
      }
    }
    if(pyramid[0][0] == -1){
      System.out.println("0");
    }
    else{
      System.out.println(pyramid[0][0]);
    }
  }
}
