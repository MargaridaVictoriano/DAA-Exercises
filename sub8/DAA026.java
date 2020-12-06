import java.util.Scanner;
public class DAA026{
	static int n;              // Numero de nos do grafo
	static int rows;
	static int cols;
    static char adj[][];    // Matriz de adjacencias
    static boolean visited[][];  // Que nos ja foram visitados?

    static int dfs(int r,int c) {
    	if(r<0 || r>=rows || c<0 || c>=cols) return 0;
    	if(visited[r][c]) return 0;
    	if(adj[r][c]=='#'){
			visited[r][c] = true;
			return 1 + dfs(r+1,c-1)+dfs(r+1,c)+dfs(r+1,c+1) + dfs(r,c-1)
			+ dfs(r,c+1)+dfs(r-1,c-1)+dfs(r-1,c) + dfs(r-1,c+1);
    	}
    	return 0;
    }
    
    public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		
		n = stdin.nextInt(); 

		for(int i=0;i<n;i++){

			int counter=0,max=0;
			rows = stdin.nextInt();
			cols = stdin.nextInt();
	    	adj = new char[rows+1][cols+1];
			visited = new boolean[rows+1][cols+1];	



			for (int j=0; j<rows; j++) {
				String str= stdin.next();
			    adj[j]=str.toCharArray();
            }

			for(int j=0;j<rows;j++){
				for(int l=0;l<cols;l++){
					if(adj[j][l]=='#'){
						counter=dfs(j,l);
						if(counter>max) {
                            max=counter;
                        }
					}
				}
			}
			System.out.println(max);
		} 
    }
}