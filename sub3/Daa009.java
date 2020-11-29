import java.io.*;
import java.util.*;

class Leters implements Comparable<Leters>{
 public int freq;
 public int pos;
 public char ch;

 Leters(int f,int p,char c){
   freq=f;
   pos=p;
   ch=c;
 }

    @Override
    public int compareTo(Leters c) {
      if (freq < c.freq){
        return +1;
      }
      else if (freq > c.freq){
        return -1;
      }
      else if(pos < c.pos){
        return -1;
      }
      else if(pos > c.pos){
        return +1;
      }
      return 0;
    }
}

public class Daa009{

 public static void main(String args[]){
  Scanner stdin = new Scanner(System.in);
  Leters v  []= new Leters[26];
  String str=stdin.nextLine();
//  System.out.println("str: " + str);
  int n = 26;

  //inicializar
  for(int i=0; i<n; i++){
    v[i]= new Leters(0,-1,(char)('A'+i));
  }


  for(int i=0;i<str.length();i++){
//    System.out.println("pos antes: " + v[i].pos + " ch: " + str.charAt(i));
    if(v[(str.charAt(i))-'A'].pos == -1){
      v[(str.charAt(i))-'A'].pos = i;
    }
  //  System.out.println("pos depois: " + v[i].pos + " ch: " + str.charAt(i));
    v[(str.charAt(i))-'A'].freq +=1;
  }

  Arrays.sort(v);

  for(int i=0;i<n;i++){
   if(v[i].freq>0){
     System.out.println(v[i].ch + " " +v[i].freq);
   }
  }
 }
}
