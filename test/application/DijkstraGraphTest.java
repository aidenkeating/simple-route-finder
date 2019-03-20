package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DijkstraGraphTest {
	private Graph testGraph;
	private List<Node> testNodes;
	
	@BeforeEach
	void beforeEach() {
		this.testNodes = new ArrayList<Node>();
		for(int i = 0; i < 5; i++) {
			testNodes.add(new Node(Integer.toString(i)));
		}
		// Node 0's neighbors.
		testNodes.get(0).addTwoWayNeighbor(testNodes.get(1), 1);
		testNodes.get(0).addTwoWayNeighbor(testNodes.get(2), 6);

		// Node 1's neighbors.
		testNodes.get(1).addTwoWayNeighbor(testNodes.get(2), 2);
		testNodes.get(1).addTwoWayNeighbor(testNodes.get(3), 1);

		// Node 2's neighbors.
		testNodes.get(2).addTwoWayNeighbor(testNodes.get(3), 2);
		testNodes.get(2).addTwoWayNeighbor(testNodes.get(4), 5);

		// Node 3's neighbors.
		testNodes.get(3).addTwoWayNeighbor(testNodes.get(4), 5);
		
		this.testGraph = new Graph(this.testNodes);
	}
	
	@Test
	void testGetShortestPath_long() {
		DijkstraGraph dGraph = new DijkstraGraph(this.testGraph);
		List<Node> path = dGraph.getShortestPath(testNodes.get(0), testNodes.get(4));
		assertEquals(4, path.size());
	}
	
	@Test
	void testGetShortestPath_alternative() {
		Node newNode = new Node("test");
		newNode.addTwoWayNeighbor(testGraph.getNodes().get(0), 3);
		newNode.addTwoWayNeighbor(testGraph.getNodes().get(4), 3);
		testGraph.getNodes().add(newNode);
		
		DijkstraGraph dGraph = new DijkstraGraph(this.testGraph);
		List<Node> path = dGraph.getShortestPath(testNodes.get(0), testNodes.get(4));
		assertEquals(3, path.size());
	}

	@Test
	void testGetShortestPath_short() {
		DijkstraGraph dGraph = new DijkstraGraph(this.testGraph);
		testNodes.get(0).addTwoWayNeighbor(testNodes.get(4), 4);
		List<Node> path = dGraph.getShortestPath(testNodes.get(0), testNodes.get(4));
		assertEquals(2, path.size());
	}

	@Test
	void testGetShortestPath_unidirectional() {
		DijkstraGraph dGraph = new DijkstraGraph(this.testGraph);
		testNodes.get(0).addOneWayNeighbor(testNodes.get(4), 4);
		List<Node> path = dGraph.getShortestPath(testNodes.get(0), testNodes.get(4));
		assertEquals(2, path.size());
	}

	@Test
	void testGetShortestPath_unidirectionalSkip() {
		DijkstraGraph dGraph = new DijkstraGraph(this.testGraph);
		testNodes.get(4).addOneWayNeighbor(testNodes.get(0), 4);
		List<Node> path = dGraph.getShortestPath(testNodes.get(0), testNodes.get(4));
		assertEquals(4, path.size());
	}

}
