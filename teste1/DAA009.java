import java.util.Scanner;
import java.util.Arrays;
class ADN implements Comparable<ADN>{
  char ch;
  int freq;
  int index;

  public ADN(char c, int f, int i){
    ch = c;
    freq = f;
    index = i;
  }
  @Override
  public int compareTo(ADN str){
    if(freq < str.freq){
      return 1;
    }
    if(freq > str.freq){
      return -1;
    }
    if(freq == str.freq){
      if(index < str.index){
        return -1;
      }
      else if(index > str.index){
        return 1;
      }
    }
    return 0;
  }

}
class DAA009{
  public static void main(String[] args){
    Scanner stdin = new Scanner(System.in);
    ADN[] ltr = new ADN[26];
    String str = stdin.nextLine();

    for(int i = 0; i < 26; i++){
      ltr[i] = new ADN((char)('A'+i),0,-1);
    }
    for(int i = 0; i < str.length(); i++){
      if(ltr[(str.charAt(i)) - 'A'].index == -1){
        ltr[str.charAt(i) - 'A'].index = i;
      }
      ltr[str.charAt(i) - 'A'].freq++;
    }
    Arrays.sort(ltr);

    for(int i = 0; i < 26; i++){
      if(ltr[i].freq > 0){
        System.out.println(ltr[i].ch + " " + ltr[i].freq);
      }
    }
  }

}
