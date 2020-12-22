
import java.util.*;

//class dos pares
class Coord implements Comparable<Coord>{
	int x;
	int y;

	Coord(int x, int y){
		this.x=x;
		this.y=y;
	}

	@Override
	public int compareTo(Coord p){
		if(x<p.x) return -1;
		if(x>p.x) return +1;
		if(y<p.y) return -1;
		if(y>p.y) return +1;
		return 0;
	}
}


// Classe que representa uma aresta
class Edge {
    int to;     // No destino
    double weight; // Peso da aresta
    
    Edge(int t, double w) {
	to = t;
	weight = w;
    }
}

// Classe que representa um no
class Node {
    public LinkedList<Edge> adj; // Lista de adjacencias
    public boolean visited;      // No ja foi visitado?
    public double distance;         // Distancia ao no origem da pesquisa
    
    Node() {
	adj = new LinkedList<>();
    } 
};

// Classe que representa um no para ficar na fila de prioridade
class NodeQ implements Comparable<NodeQ> {
    public double cost;
    public int node;

    NodeQ(double c, int n) {
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
    Node[] nodes;   // Array para counter os nos
    
    Graph(int n) {
		this.n = n;
		nodes = new Node[n+1];  // +1 se os nos comecam em 1 ao inves de 0
		for (int i=1; i<=n; i++)
		    nodes[i] = new Node();
    }
    
    void addLink(int a, int b, double c) {
		nodes[a].adj.add(new Edge(b, c));
		nodes[b].adj.add(new Edge(a, c));
    }

    // Algoritmo de Dijkstra
    double prim(int s) {
	
		//Inicializar nos como nao visitados e com distancia infinita
		for (int i=1; i<=n; i++) {
		    nodes[i].distance = Integer.MAX_VALUE;
		    //nodes[i].visited  = false;
		}
	
		// Inicializar "fila" com no origem
		nodes[s].distance = 0;
		TreeSet<NodeQ> q = new TreeSet<>();
		q.add(new NodeQ(0, s)); // Criar um par (dist=0, no=s)


		double finale=0.0;
		// Ciclo principal do Dijkstra
		while (!q.isEmpty()) {
	      
		    // Retirar no com menor distancia (o "primeiro" do set, que e uma BST)
		    NodeQ nq = q.pollFirst();
		    int  u = nq.node;
		    nodes[u].visited = true;
			finale+=nodes[u].distance;
		    // Relaxar arestas do no retirado
		    for (Edge e : nodes[u].adj) {
				int v = e.to;
				double cost = e.weight;
				if (!nodes[v].visited && cost < nodes[v].distance) {
				    q.remove(new NodeQ(nodes[v].distance, v)); // Apagar do set
				    nodes[v].distance = cost;
				    q.add(new NodeQ(nodes[v].distance, v));    // Inserir com nova (e menor) distancia
				}
		    }
		}
		return finale;
    }
};


public class DAA037 {

	public static double quad(Coord a, Coord b){
		return Math.sqrt(((Math.pow((a.x-b.x),2))+(Math.pow((a.y-b.y),2))));
	}

    public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);

		int n=stdin.nextInt();
		Graph g = new Graph(n);
		LinkedList<Coord> coord= new LinkedList<>();
		TreeMap<Coord,Integer> map= new TreeMap<>();
		
		for(int i=0;i<n;i++){
			int a= stdin.nextInt();//x
			int b= stdin.nextInt();//y
			Coord c= new Coord(a,b);
			coord.add(c);
		}
		//agora adicionamos a tree
		int count=1;
		for(Coord d : coord){
			map.put(d,count);
			count++;
		}

		for(int i=0;i<n;i++){
			for(int j=i+1;j<n;j++){
				Coord s= coord.get(i);
				Coord s1= coord.get(j);
				g.addLink(map.get(s),map.get(s1), quad(s,s1));
			}
		}

		double finale=0.0;
		for(int i=1;i<=n;i++){
			if(!g.nodes[i].visited)
				finale=Math.max(g.prim(i),finale);
		}

		System.out.println(finale);
    }
}