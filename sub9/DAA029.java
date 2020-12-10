import java.util.LinkedList;
import java.util.Scanner;
public class DAA029 {
    static String str;
    static String str1;
    static boolean adj[][] = new boolean[26][26];
    static boolean visited[] = new boolean[26];
    static int n;
    static LinkedList<Integer> order = new LinkedList<>();
    static void setAdj(){
        int i = 0;
        while(i < str.length() && i < str1.length()){
            int seq = str.charAt(i) - 'A';
            int seq1 = str1.charAt(i) - 'A';
            if(seq != seq1){
                adj[seq][seq1] = true;
                visited[seq] = false;
                visited[seq1] = false;
                break;
            }
            i++;
        }
        str = str1;
    }
    static void dfs(int v){
        visited[v] = true;
        for(int i = 0; i < 26; i++){
            if(adj[v][i] && !visited[i]){
                dfs(i);
            }
        }
        order.add(v);
    }
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        n = stdin.nextInt();

        for(int i = 0; i < 26; i++){
            visited[i] = true;
        }
        
        str = stdin.nextLine();
        for(int i = 0; i < n; i++){
            str1 = stdin.nextLine();
            setAdj();
        }
        for(int i = 0; i < 26; i++){
            if(!visited[i]){
                dfs(i);
            }
        }
        for(int i = order.size() - 1; i >= 0; i--){
            System.out.print((char)(order.get(i) + 'A'));
        }
        System.out.println();
    }
}