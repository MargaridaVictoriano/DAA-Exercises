import java.util.Scanner;
import java.util.Arrays;

class Shoes implements Comparable<Shoes>{
  int days;
  int fee;
  float ratio;
  int index;
  public Shoes(int d, int f, float r, int i){
    days = d;
    fee = f;
    ratio = r;
    index = i;
  }
  @Override
  public int compareTo(Shoes s){
    if(ratio < s.ratio){
      return 1;
    }
    if(ratio > s.ratio){
      return -1;
    }
    if(ratio == s.ratio){
      if(index < s.index){
        return -1;
      }
      else if(index > s.index){
        return 1;
      }
    }
    return 0;
  }
}
class DAA014{
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    Shoes[] list = new Shoes[n];
    for(int i = 0; i < n; i++){
      int day = stdin.nextInt();
      int fee = stdin.nextInt();
      list[i] = new Shoes(day,fee,(float)fee/(float)day, i+1);
    }
    Arrays.sort(list);

    for(int i = 0; i < n-1; i++){
      System.out.print(list[i].index + " ");
    }
    System.out.println(list[n-1].index);
  }
}
