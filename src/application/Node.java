package application;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private String name;
	private List<Edge> edges;

	public Node(String name) {
		this.name = name;
		this.edges = new ArrayList<Edge>();
	}

	public String getName() {
		return name;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public List<Node> getNeighbors() {
		List<Node> neighbors = new ArrayList<Node>(getEdges().size());
		for (Edge e : getEdges()) {
			neighbors.add(e.getTo());
		}
		return neighbors;
	}

	// Add an Edge that goes one way e.g. A -> B
	public void addOneWayNeighbor(Node neighbor, int weight) {
		this.edges.add(new Edge(this, neighbor, weight));
	}

	// Add an Edge that goes both ways e.g. A <-> B
	public void addTwoWayNeighbor(Node neighbor, int weight) {
		addOneWayNeighbor(neighbor, weight);
		neighbor.addOneWayNeighbor(this, weight);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return "Node [name=" + name + ", edges=" + edges.size() + "]";
	}
}
