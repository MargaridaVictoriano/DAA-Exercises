import java.util.Scanner;
import java.util.Arrays;
class Segment implements Comparable<Segment>{
  int left;
  int right;
  public Segment(int l, int r){
    left = l;
    right = r;
  }
  @Override
  public int compareTo(Segment s){
    if(left < s.left){
      return -1;
    }
    if(left > s.left){
      return 1;
    }
    if(left == s.left){
      if(right < s.right){
        return 1;
      }
      else if(right > s.right){
        return -1;
      }
    }
    return 0;
  }
}

class DAA013{
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    int size = stdin.nextInt();
    int n = stdin.nextInt();
    Segment[] list = new Segment[n];

    for(int i = 0; i < n; i++){
      list[i] = new Segment(stdin.nextInt(),stdin.nextInt());
    }

    Arrays.sort(list);
    int count = 0;
    int end = 0;

    for(int i = 0; i < n; i++){
      int aux = end;
      for(int j = i; list[j].left <= aux; j++){
        if(end >= size) break;
        if(list[j].right > end){
          end = list[j].right;
        }
      }
      if(aux != end) count++;
      if(end >= size) break;
    }
    System.out.println(count);
  }
}
