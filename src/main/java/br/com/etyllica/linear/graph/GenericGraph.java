package br.com.etyllica.linear.graph;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.linear.Point2D;
import br.com.etyllica.linear.graph.common.IntegerEdge;

public class GenericGraph<T, E extends IntegerEdge> {

	protected List<Node<T>> nodes;
	
	protected List<E> edges;
	
	public GenericGraph() {
		super();
		
		nodes = new ArrayList<Node<T>>();
		
		edges = new ArrayList<E>();
	}

	public List<Node<T>> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node<T>> nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node<T> node) {
		this.nodes.add(node);
	}
	
	public Node<T> addNode(int index, Point2D point) {
		
		Node<T> node = new Node<T>(point.getX(), point.getY());
		
		this.nodes.add(index, node);
		
		return node;
	}
	
	public Node<T> addNode(Point2D point) {
		
		Node<T> node = new Node<T>(point.getX(), point.getY());
		
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
