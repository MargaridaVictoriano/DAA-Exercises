import java.util.*;

class DAA021{
  public static void main(String[] args){
      FastScanner stdin = new FastScanner(System.in);
      TreeMap<Integer,Integer> tree = new TreeMap<Integer,Integer>();

      int add = stdin.nextInt();
      int rem = stdin.nextInt();
      int n = add + rem;

      for(int i = 0; i < n; i++){
        String str = stdin.next();
        if(str.contains("BAK")){
          int energy = stdin.nextInt();
          Integer v = tree.get(energy);
          if(v == null){
            tree.put(energy,1);
          }
          else{
            tree.put(energy,v+1);
          }
        }
        if(str.contains("MIN")){
          int v = tree.get(tree.firstKey());
          tree.put(tree.firstKey(), v-1);
          v--;
          System.out.println(tree.firstKey());
          if(v == 0){
            tree.remove(tree.firstKey());
          }
        }
        if(str.contains("MAX")){
          int v = tree.get(tree.lastKey());
          tree.put(tree.lastKey(), v-1);
          v--;
          System.out.println(tree.lastKey());
          if(v == 0){
            tree.remove(tree.lastKey());
          }
        }
      }
      FastPrint.out.close();
  }
}
