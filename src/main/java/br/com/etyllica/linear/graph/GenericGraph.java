package br.com.etyllica.linear.graph;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.linear.Point2D;

public class GenericGraph<E extends Edge> {

	protected List<Node> nodes;
	
	protected List<E> edges;
	
	public GenericGraph() {
		super();
		
		nodes = new ArrayList<Node>();
		
		edges = new ArrayList<E>();
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node node) {
		this.nodes.add(node);
	}
	
	public Node addNode(int index, Point2D point) {
		
		Node node = new Node(point.getX(), point.getY());
		
		this.nodes.add(index, node);
		
		return node;
	}
	
	public Node addNode(Point2D point) {
		
		Node node = new Node(point.getX(), point.getY());
		
		this.nodes.add(node);
		
		return node;
	}

	public List<E> getEdges() {
		return edges;
	}

	public void setEdges(List<E> edges) {
		this.edges = edges;
	}
	
	public void addEdge(E edge) {
		this.edges.add(edge);
	}
			
	public void clear() {
		nodes.clear();
		edges.clear();
	}
	
}
