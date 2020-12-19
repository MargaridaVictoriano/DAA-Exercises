import java.io.*;
import java.util.*;
public class DAA035
{
	private static int[] bellman_ford (Grafo g, int s) throws Exception
	{
			int[] min_weight = new int[g.num_vertices() + 1];
			// best estimate so far of minimum weight from s to another vertex
			Arrays.fill(min_weight, 1000000000); // 1e9 to avoid overflow
			min_weight[s] = 0;

			// Repeat
			for (int i = 1; i <= g.num_vertices(); ++i)
			{
					// relax all the edges
					for (int u = 1; u <= g.num_vertices(); ++u)
					{
							for (Arco w : g.adjs_no(u))
							{
									int v = w.extremo_final(),
										c = w.valor_arco();
									min_weight[v] = Math.min(min_weight[u] + c, min_weight[v]);
									// Assert: v.min_weight >= true minimum weight from s to v
							}
					}
			}

			for (int u = 1; u <= g.num_vertices(); ++u)
			{
					for (Arco w : g.adjs_no(u))
					{
							int v = w.extremo_final(),
									c = w.valor_arco();
							if (min_weight[u] + c < min_weight[v])
							{
									throw new Exception("Negative-weight cycle detected.");
							}
					}
			}


			return min_weight;
	}

	private static int[] dijkstra (Grafo g, int s)
	{
			/* Set Data Structures */
			int[] dist = new int[g.num_vertices() + 1];
			Arrays.fill(dist, 1000000000); // 1e9 to avoid overflow
			TreeSet<Integer> to_explore = new TreeSet<>((i, j) -> {
					int delta_value = Integer.compare(dist[i], dist[j]);
					// If they are the same, compare by index instead
					if (delta_value == 0)
					{
							return Integer.compare(i, j);
					}
					return delta_value;
			});

			// Let's find distances from s
			dist[s] = 0;
			to_explore.add(s);

			while (!to_explore.isEmpty())
			{
					int v = to_explore.pollFirst();
					// Assert: v.distance is the true shortest distance from s to v
					// Assert: v is never put back into to_explore
					g.adjs_no(v).forEach((edge) -> {
							int w = edge.extremo_final(),
								edgecost = edge.valor_arco();
							int dist_w = dist[v] + edgecost;
							if (dist_w < dist[w])
							{
									dist[w] = dist_w;
									to_explore.remove(w);
									to_explore.add(w);
							}
					});
			}


			return dist;
	}

	private static int[][]
	johnson (Grafo g) throws Exception
	{
		   // Finish the helper graph by running Bellman-Ford on it
		   int[] h = bellman_ford(g, 0);
		   // Generate the tweaked graph
		   for (int u = 1; u <= g.num_vertices(); ++u)
		   {
				   for (Arco w : g.adjs_no(u))
				   {
						   int v = w.extremo_final(),
							   c = w.valor_arco();
						   w.novo_valor(c + h[u] - h[v]);
				   }
		   }

		   int [][] D = new int[g.num_vertices()+1][g.num_vertices()+1];

		   // Dijkstra on the tweaked graph
		   for (int u = 1; u <= g.num_vertices(); ++u)
		   {
				   for (int v = 1; v <= g.num_vertices(); ++v)
				   {
						   int[] delta = dijkstra(g, u);
						   D[u][v] = delta[v] + h[v] - h[u];
				   }
		   }


		   return D;
	}
	
	public static void
	main (String[] args)
	throws Exception
	{
		FastScanner stdin = new FastScanner(System.in);
		int n_cidades = stdin.nextInt();
		

		Grafo rede = new Grafo(n_cidades);
		for (int i = 0; i < n_cidades; ++i)
		{
			int cidade_de_origem = stdin.next().charAt(0)-'A'+1;
			//rede.insert_new_arc(cidade_de_origem, cidade_de_origem, 1);
			int n_destinos = stdin.nextInt();
			for (int j = 0; j < n_destinos; ++j)
			{
				int cidade_de_destino = stdin.next().charAt(0)-'A'+1;
				rede.insert_new_arc(cidade_de_origem, cidade_de_destino, 1);
			}
		}
		

		System.out.print("  "); 
		int[][] johnson_out = johnson(rede);
		FastPrint.out.print("A");
		for (int lmao = 1; lmao < n_cidades; ++lmao)
			FastPrint.out.print(" "+(char)(lmao+'A'));
		FastPrint.out.println();
		for (int i = 1; i < johnson_out.length; ++i)
		{
			FastPrint.out.print((char)(i+'A'-1));
			for (int j = 1; j < johnson_out[1].length; ++j)
			{
				FastPrint.out.print(" " + (johnson_out[i][j] == 1000000000 ? "0" : 1));
			}
			FastPrint.out.println();
		}
		
		FastPrint.out.close();
	}
}
//----------------------------------------------------------------------
class FastScanner {
    BufferedReader br;
    StringTokenizer st;
 
    public FastScanner(InputStream stream) {
	br = new BufferedReader(new InputStreamReader(stream));
    }
 
    String next() {
	while (st == null || !st.hasMoreElements()) {
	    try {
		st = new StringTokenizer(br.readLine());
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return st.nextToken();
    }
 
    int nextInt() {
	return Integer.parseInt(next());
    }
 
    long nextLong() {
	return Long.parseLong(next());
    }
 
    double nextDouble() {
	return Double.parseDouble(next());
    }
 
    String nextLine(){
	String str = "";
	try {
	    str = br.readLine();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return str;
    }

}
class FastPrint {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
}
class Arco {
    int no_final;
    int valor;
    
    Arco(int fim, int v){
	no_final = fim;
	valor = v;
    }

    int extremo_final() {
	return no_final;
    }

    int valor_arco() {
	return valor;
    }

    void novo_valor(int v) {
	valor = v;
    }
}
class No {
    //int label;
    LinkedList<Arco> adjs;

    No() {
	adjs = new LinkedList<Arco>();
    }
}
class Grafo {
    No verts[];
    int nvs, narcos;
			
    public Grafo(int n) {
	nvs = n;
	narcos = 0;
	verts  = new No[n+1];
	for (int i = 0 ; i <= n ; i++)
	    verts[i] = new No();
        // para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
    }
    
    public int num_vertices(){
	return nvs;
    }

    public int num_arcos(){
	return narcos;
    }

    public LinkedList<Arco> adjs_no(int i) {
	return verts[i].adjs;
    }
    
    public void insert_new_arc(int i, int j, int valor_ij){
	verts[i].adjs.addFirst(new Arco(j,valor_ij));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}
