import java.util.*;

class Edge {
    public final int adjacent_node; int w(){return adjacent_node;}
    public final float weight;
    /*public int weight1;*/

    Edge(int adjacent_node, float weight/*, int weight1*/) {
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

    public LinkedList<Edge>
    node_adjs(int u) {
        return nodes[u].adjs;
    }

    public void add_link(int u, int w, float weight/*, int weight1*/) {
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

public class DAA033
{
    public static float[]
    dijkstra (Graph g, int s)
    {
            /* Set Data Structures */
            float[] dist = new float[g.n_nodes + 1];
            Arrays.fill(dist, 1000000000); // 1e9 to avoid overflow
            TreeSet<Integer> to_explore = new TreeSet<>((i, j) -> {
                    int delta_value = Double.compare(dist[i], dist[j]);
                    // If they are the same, compare by index instead
                    if (delta_value == 0)
                    {
                            return Double.compare(i, j);
                    }
                    return delta_value;
            });


            dist[s] = 0;
            to_explore.add(s);

            while (!to_explore.isEmpty())
            {
                    int v = to_explore.pollFirst();
                    // Assert: v.distance is the true shortest distance from s to v
                    // Assert: v is never put back into to_explore
                    g.node_adjs(v).forEach((edge) -> {
                            int w = edge.w();
                            float edgecost = edge.weight;
                            float dist_w = dist[v] + edgecost;
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

    public static void
    main (String[] args)
    {
        Scanner stdin = new Scanner(System.in);

        int n_nodes = stdin.nextInt(),n_edges = stdin.nextInt();
        Graph g = new Graph(n_nodes);
        last_place_idx = -1;
        place_idx = new HashMap<String, Integer>(n_nodes);

        String shome = stdin.next(),sdestination = stdin.next();
        int h = str2num(shome), d = str2num(sdestination);

        for (int edge = 0; edge < n_edges; ++edge)
        {
                String su = stdin.next(),
                       sw = stdin.next();
                int u = str2num(su),
                    w = str2num(sw);
                float cost = stdin.nextFloat();
                g.add_link(u, w, cost);
                g.add_link(w, u, cost);
        }

        System.out.printf("%.1f\n", dijkstra(g, h)[d]);

        stdin.close();
    }
    
    static int last_place_idx;
    static Map<String, Integer> place_idx;
    public static int str2num(String s)
    {
        if (!place_idx.containsKey(s)) {place_idx.put(s, ++last_place_idx);return last_place_idx;}
        else return place_idx.get(s);
    }
}
