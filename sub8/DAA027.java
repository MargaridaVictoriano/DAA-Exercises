import java.util.Scanner;
public class DAA027 {
    static int node,edges,color;              // Numero de nos do grafo
    static boolean adj[][];    // Matriz de adjacencias
    static int colored[];  // Que nos ja foram visitados?
    public static boolean coloring(int x, int color){
        //0 : nao foi visitado, 1 : azul, 2 : vermelho
        int newColor;
        if(color == 1) newColor = 2;
        else newColor = 1;
        if(colored[x] != 0){
            if(colored[x] != newColor){
                return false;
            }
            return true;
        }
        if(colored[x] == 0){
            colored[x] = newColor;
            for(int i = 0; i < node; i++){
                if(adj[x][i]){
                    if(!coloring(i,newColor)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        for(int i = 0; i < n; i++){
            node = stdin.nextInt();
            edges = stdin.nextInt();
            adj = new boolean[node+1][node+1];
            colored = new int[node+1];
            for(int j = 0; j < edges; j++){
                int x = stdin.nextInt();
                int y = stdin.nextInt();
                x--;
                y--;
                adj[x][y] = true;
                adj[y][x] = true;
            }
            boolean answer = coloring(0,1);
            if(answer) {
                System.out.println("sim");
            }
            else{
                System.out.println("nao");
            }
        }
    }

}