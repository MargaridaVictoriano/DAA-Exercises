import java.util.Scanner;
class DAA025{
    static int n;              // Numero de nos do grafo
    static boolean adj[][];    // Matriz de adjacencias
    static boolean visited[];  // Que nos ja foram visitados?

    static void dfs(int v) {
	//System.out.print(v + " ");
	visited[v] = true;
	for (int i=1; i<=n; i++)
	    if (adj[v][i] && !visited[i])
		dfs(i);
    }
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        n = stdin.nextInt();
        int links = stdin.nextInt();
        adj = new boolean[n+1][n+1];
        visited = new boolean[n+1];
        for(int i = 0; i < links; i++){
            int x = stdin.nextInt();
            int y = stdin.nextInt();
            adj[x][y] = true;
            adj[y][x] = true;
        }
        int counter = 0;
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                counter++;
                dfs(i);
            }
        }
        System.out.println(counter);
    }
}