import java.util.Scanner;
import java.util.Arrays;

class Shoes implements Comparable<Shoes>{
  int days;
  int fee;
  int index;
  float ratio;

  public Shoes(int d, int f, int i, float r){
    days = d;
    fee = f;
    index = i;
    ratio = r;
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
      if(index < s.index) return -1;
      else if(index > s.index) return 1;
    }
    return 0;
  }
}

class DAA014{
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    Shoes[] s = new Shoes[n];

    for(int i = 0; i < n; i++){
      int days = stdin.nextInt();
      int fee = stdin.nextInt();
      s[i] = new Shoes(days,fee,i+1,(float)fee/(float)days);
    }

    Arrays.sort(s);

    for(int i = 0; i < n-1; i++){
      System.out.print(s[i].index + " ");
    }

    System.out.println(s[n-1].index);
  }
}
