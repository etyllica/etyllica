package br.com.etyllica.linear.graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import examples.etyllica.graph.model.ComplexGraph;

public class ComplexGraphTest {

	private Node root;
	
	private Node firstChild;
	
	private GenericComplexGraph<Node, Edge> graph;
	
	@Before
	public void setUp() {
	
		graph = new ComplexGraph();
		
		root = new Node();
		root.setLocation(80, 190);
		
		firstChild = new Node();
		Node secondChild = new Node();
		Node thirdChild = new Node();
		
		Node firstChildSon = new Node();
		
		//Add three child nodes
		graph.addNode(root);
		graph.addNode(firstChild);
		graph.addNode(secondChild);
		graph.addNode(thirdChild);
		
		graph.addEdge(new Edge(root, firstChild));
		graph.addEdge(new Edge(root, secondChild));
		graph.addEdge(new Edge(root, thirdChild));
		
		graph.addEdge(new Edge(firstChild, firstChildSon));
		graph.addEdge(new Edge(firstChild, new Node()));
				
	}	
	
	@Test
	public void testAddEdges() {

		Assert.assertEquals(6, graph.getNodes().size());
		
		//Just two nodes have at least one child
		Assert.assertEquals(2, graph.getAllEdges().size());
				
		Assert.assertEquals(3, graph.getEdges(root).size());
		
		Assert.assertEquals(2, graph.getEdges(firstChild).size());
		
	}
	
}
