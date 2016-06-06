import java.util.*;

class Solution {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		HashMap<Graph.Vertex, List<Graph.Edge>> edges = new HashMap<>(M);

		ArrayList<Graph.Vertex> vertexs = new ArrayList<>(N);
		for (int i = 1; i <= N; i++) {
			vertexs.add(new Graph.Vertex(i));
		}

		for (int i = 0; i < M; i++) {
			Graph.Vertex src = vertexs.get(sc.nextInt() - 1);
			Graph.Vertex dst = vertexs.get(sc.nextInt() - 1);
			int weight = sc.nextInt();

			if (edges.containsKey(vertexs.get(src.index - 1))) {
				edges.get(vertexs.get(src.index - 1)).add(new Graph.Edge(src, dst, weight));
			} else {
				List<Graph.Edge> links = new LinkedList<>();
				links.add(new Graph.Edge(src, dst, weight));
				edges.put(vertexs.get(src.index - 1), links);
			}

			if (edges.containsKey(vertexs.get(dst.index - 1))) {
				edges.get(vertexs.get(dst.index - 1)).add(new Graph.Edge(dst, src, weight));
			} else {
				List<Graph.Edge> links = new LinkedList<>();
				links.add(new Graph.Edge(dst, src, weight));
				edges.put(vertexs.get(dst.index - 1), links);
			}
		}

		Graph G = new Graph(edges, vertexs);
		int root = sc.nextInt() - 1;
		List<Graph.Vertex> prim_vertexs = G.MST_Prim(root);
		int sum = 0;
		for (Graph.Vertex v : prim_vertexs) {
			sum += v.key;
		}

		System.out.println(sum);

	}
}
