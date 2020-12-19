import java.util.*;

class Edge {
    public final int adjacent_node; int w(){return adjacent_node;}
    public final int weight;
    /*public int weight1;*/

    Edge(int adjacent_node, int weight/*, int weight1*/) {
        this.adjacent_node = adjacent_node;
        this.weight = weight;
        /*this.weight1 = weight1;*/
    }
}


class Node {
    //int label;
    final LinkedList<Edge> adjs;

    Node() {
        adjs = new LinkedList<>();
    }
}


class Graph {
    final Node[] nodes;
    public final int n_nodes;
    public int n_edges;

    public Graph(int n) {
        n_nodes = n;
        n_edges = 0;
        // We won't use position 0 if nodes are numbered starting with 1.
        nodes = new Node[n + 1];
        for (int u = 0; u <= n; ++u)
            nodes[u] = new Node();
    }

    public LinkedList<Edge> node_adjs(int u) {
        return nodes[u].adjs;
    }

    public void add_link(int u, int w, int weight/*, int weight1*/) {
        nodes[u].adjs.addFirst(new Edge(w, weight/*, weight1*/));
        ++n_edges;
    }

    public Edge find_edge(int u, int w) {
        for (Edge adj : node_adjs(u))
            if (adj.w() == w)
                return adj;
        return null;
    }
}

public class DAA034
{
	private static int[] bellman_ford (Graph g, int s) throws Exception
	{
			int[] min_weight = new int[g.n_nodes + 1];
			// best estimate so far of minimum weight from s to another vertex
			Arrays.fill(min_weight, 1000000000); // 1e9 to avoid overflow
			min_weight[s] = 0;

			// Repeat
			for (int i = 0; i < g.n_nodes; ++i)
			{
					// relax all the edges
					for (int u = 0; u < g.n_nodes; ++u)
					{
							for (Edge edge : g.node_adjs(u))
							{
									int w = edge.w(),
										c = edge.weight;
									min_weight[w] = Math.min(min_weight[u] + c, min_weight[w]);
									// Assert: v.min_weight >= true minimum weight from s to v
							}
					}
			}

			for (int u = 0; u < g.n_nodes; ++u)
			{
					for (Edge edge : g.node_adjs(u))
					{
							int w = edge.w(),
								c = edge.weight;
							if (min_weight[u] + c < min_weight[w])
							{
									throw new Exception("Negative-weight cycle detected.");
							}
					}
			}


			return min_weight;
	}

    public static void main (String[] args)
    {
        Scanner stdin = new Scanner(System.in);
        int t = stdin.nextInt();
        while (t --> 0)
        {
            int n_nodes = stdin.nextInt(),
                n_edges = stdin.nextInt();
            Graph g = new Graph(n_nodes);

            for (int edge = 0; edge < n_edges; ++edge)
            {
                    int u = stdin.nextInt(),
                        w = stdin.nextInt(),
                        cost = stdin.nextInt();
                    g.add_link(u, w, cost);
            }

            try {
                bellman_ford(g, 0);
                System.out.println("impossivel");
            } catch (Exception e) {
                System.out.println("possivel");
            }
        }
        stdin.close();
    }
}
