import java.util.*;

public class DAA030 {
    public static void main(String[] args){

        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        Graph g = new Graph(n);
        int edges = stdin.nextInt();
        int maxValue[] = new int[n+1]; //valores maximos

        for(int i = 0; i < edges; i++){
            int a = stdin.nextInt();
            int b = stdin.nextInt();
            g.addLink(a,b);
        }

        int max = -1, min= -1;
        for(int i = 1; i <= n; i++){
            int vBfs = g.bfs(i);
            maxValue[i] = vBfs;
            if(vBfs > max){
                max = vBfs;
            }
            if(vBfs < min || min == -1){
                min = vBfs;
            }
        }
        System.out.println(max);
        System.out.println(min);

        int flag = 0;
        for(int i = 0; i <= n; i++){
            if(maxValue[i] == min && flag == 0){
                flag = 1;
                System.out.print(i);
            }
            else if(maxValue[i] == min){
                System.out.print(" " + i);

            }
        }
        System.out.println();

        flag = 0;
        for(int i = 0; i <= n; i++){
            if(maxValue[i] == max && flag == 0){
                flag = 1;
                System.out.print(i);
            }
            else if(maxValue[i] == max){
                System.out.print(" " + i);

            }
        }
        System.out.println();
    }
}