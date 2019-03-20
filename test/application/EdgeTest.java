package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EdgeTest {
	
	@Test
	void testConstructor() {
		String testFromName = "from";
		String testToName = "to";
		Node testFrom = new Node(testFromName);
		Node testTo = new Node(testToName);
		int testWeight = 10;
		
		Edge edge = new Edge(testFrom, testTo, testWeight);
		
		assertEquals(testFromName, edge.getFrom().getName());
		assertEquals(testToName, edge.getTo().getName());
		assertEquals(testWeight, edge.getWeight());
	}

}
