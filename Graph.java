import java.util.*;

class Graph {
	static public class Vertex implements Comparable<Vertex> {
		public Integer index;
		public int key;
		public Vertex par;
		public boolean inMST;

		public Vertex (int index) {
			this.index = new Integer(index);
			this.key = Integer.MAX_VALUE;
			this.par = null;
			this.inMST = false;
		}

		@Override
		public boolean equals (Object obj) {
			if (obj == null) return false;

			if (!Vertex.class.isAssignableFrom(obj.getClass())) {
        		return false;
    		}

			final Vertex v = (Vertex) obj;
			return this.index.equals(v.index);
		}

		@Override
		public int hashCode() {
		    int hash = 3;
		    hash = 53 * hash + this.index.hashCode();
		    return hash;
		}

		@Override
		public int compareTo (Vertex v) {
			return this.key - v.key;
		}
	}

	static public class Edge {
		public Vertex dst;
		public Vertex src;
		public Integer weight;

		public Edge (Vertex src, Vertex dst, int weight) {
			this.src = src;
			this.dst = dst;
			this.weight = new Integer(weight);
		}

		@Override
		public boolean equals (Object obj) {
			if (obj == null) return false;

			if (!Edge.class.isAssignableFrom(obj.getClass())) {
        		return false;
    		}

			final Edge e = (Edge) obj;
			return this.src.index == e.src.index;
		}

		@Override
		public int hashCode() {
		    int hash = 3;
		    hash = 53 * hash + src.hashCode();
		    return hash;
		}
	}

	private HashMap<Vertex, LinkedList<Edge>> edges;
	private List<Vertex> vertexs;

	public Graph(HashMap<Vertex, LinkedList<Edge>> edges, List<Vertex> vertexs) {
		this.edges = edges;
		this.vertexs = vertexs;
	}

	public List<Vertex> MST_Prim (int r) {
		vertexs.get(r).key = 0;

		PriorityQueue<Vertex> pq = new PriorityQueue<>(vertexs);
		List<Vertex> MST = new LinkedList<>();
		while (!pq.isEmpty()) {
			Vertex u = pq.poll();
			MST.add(u);
			u.inMST = true;

			for (Edge e : edges.get(u)) {
				if (!MST.contains(e.dst) && e.weight < e.dst.key) {
					pq.remove(e.dst);
					e.dst.par = u;
					e.dst.key = e.weight;
					pq.offer(e.dst);
				}
			}
		}

		return MST;
	}
}
