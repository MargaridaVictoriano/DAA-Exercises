import java.util.*;

// Classe que representa uma aresta
class Edge {
    int to;     // No destino
    int weight; // Peso da aresta
    
    Edge(int t, int w) {
	to = t;
	weight = w;
    }
}

// Classe que representa um no
class Node {
    public LinkedList<Edge> adj; // Lista de adjacencias
    public boolean visited;      // No ja foi visitado?
    public int distance;         // Distancia ao no origem da pesquisa
    
    Node() {
	adj = new LinkedList<>();
    } 
};

// Classe que representa um no para ficar na fila de prioridade
class NodeQ implements Comparable<NodeQ> {
    public int cost;
    public int node;

    NodeQ(int c, int n) {
	cost = c;
	node = n;
    }

    @Override
    public int compareTo(NodeQ nq) { 
        if (cost < nq.cost) return -1; 
        if (cost > nq.cost) return +1;
	if (node < nq.node) return -1; 
	if (node > nq.node) return +1;
        return 0; 
    } 
}

// Classe que representa um grafo
class Graph {
    int n;          // Numero de nos do grafo
    Node[] nodes;   // Array para conter os nos
    int a,b;
    int[] min;
    int ind,total;

    Graph(int n, int a, int b) {
    	this.a=a;
    	this.b=b;
		this.n = n;
		nodes = new Node[a+b+1];  // +1 se os nos comecam em 1 ao inves de 0
		for (int i=1; i<=a+b; i++)
		    nodes[i] = new Node();
		min= new int[b];
		ind=0;
		total=0;
    }
    
    void addLink(int d, int e, int f) {
		nodes[d].adj.add(new Edge(e, f));
		nodes[e].adj.add(new Edge(d, f));
    }

    // Algoritmo de Dijkstra
    void prim() {
	
		//Inicializar nos como nao visitados e com distancia infinita
		for (int i=1; i<=a+b; i++) {
		    nodes[i].distance = Integer.MAX_VALUE;
		}
		
		for(int i=1;i<=a;i++)
			nodes[i].visited=true;

		TreeSet<NodeQ> q = new TreeSet<>();

		for(int s=1;s<=a;s++){
			nodes[s].distance = 0;
			q.add(new NodeQ(0, s)); // Criar um par (dist=0, no=s)
		}
		while (!q.isEmpty()) {
		      
			   // Retirar no com menor distancia (o "primeiro" do set, que e uma BST)
		    NodeQ nq = q.pollFirst();
		    int  u = nq.node;
		    nodes[u].visited = true;
		    total+=nodes[u].distance;

		    if(nodes[u].distance!=0){
		    	min[ind]=nodes[u].distance;
		    	ind++;
		    }
			    // Relaxar arestas do no retirado
		    for (Edge e : nodes[u].adj) {
				int v = e.to;
				int cost = e.weight;
				if (!nodes[v].visited && cost < nodes[v].distance) {
				    q.remove(new NodeQ(nodes[v].distance, v)); // Apagar do set
				    nodes[v].distance = cost;
				    q.add(new NodeQ(nodes[v].distance, v));    // Inserir com nova (e menor) distancia
				}
			}
		}
	}
    
};


public class DAA038 {
    public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);

		int a= stdin.nextInt(); //casas ja na rede
		int b= stdin.nextInt(); //casas a adicionar
		int c= stdin.nextInt(); //possiveis novas ligaçoes

		Graph g = new Graph(c,a,b);

		for (int i=0; i<c; i++) 
		    g.addLink(stdin.nextInt(), stdin.nextInt(), stdin.nextInt());

		g.prim();
		System.out.println(g.total);

		Arrays.sort(g.min);

		for(int i=0;i<b;i++){
			if(i==0) 
				System.out.print(g.min[i]);
			else
				System.out.print(" "+g.min[i]);
		}
		System.out.println();
    }
}