import java.util.*;

public class DAA042 {

    static class Graph {
        int m;
        int n;
        int adjMatrix[][];

        public Graph(int n, int m) {
            this.m=m;
            this.n=n;
            adjMatrix=new int[n+1][m+1];
        }

        public void addLink(int n, int m) {
            adjMatrix[n][m]=1;

        }
    }

    public int maxMatching(Graph graph) {
        int n=graph.n;
        int m=graph.m;

        int aux[]=new int[m];   
        for (int i=0; i<m; i++){
            aux[i]=-1;   
        }
        int count=0;

        for (int i=0; i<n;i++) {    
            boolean visited[]=new boolean[m];

            if (isbipartiteMatch(graph, i, visited, aux)) {
                count++;
            }
        }
        return count;
    }

    boolean isbipartiteMatch(Graph graph, int i, boolean visited[], int aux[]) {
        
        for (int j=0; j<graph.m;j++) {
              if (graph.adjMatrix[i][j] == 1 && !visited[j]){
                visited[j]=true;
                int temp=aux[j];
                if (temp<0 || isbipartiteMatch(graph, temp, visited, aux)) {
                    aux[j]=i;    
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);

        int c = in.nextInt();
        for(int j=0; j<c; j++){
        int n = in.nextInt();
        int m = in.nextInt();
       
        Graph graph = new Graph(n, m);
        for(int k=0; k<m; k++){
            int a = in.nextInt();
            int b = in.nextInt();
            graph.addLink(a,b);
        }
       
       
        DAA042 g = new DAA042();
        int res=0;
        res= g.maxMatching(graph);

        if(res==n){
            System.out.println("YES");
        }else{
        System.out.println("NO");
                }
         }
    }
}