package br.com.etyllica.linear.graph;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.linear.Point2D;

public class GenericGraph<T, E extends GenericEdge<T>> {

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
	
	public List<E> getEdges(Node<T> node) {
		
		List<E> nodeEdges = new ArrayList<E>();
		
		for(E edge:edges) {
			if(edge.getOrigin()==node||edge.getDestination()==node) {
				nodeEdges.add(edge);
			}
		}
		
		return nodeEdges;
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

	public List<Node<T>> neighbors(Node<T> node) {
		Node<T> parent = node.getParent();
		
		List<Node<T>> neighbors = new ArrayList<Node<T>>();
		
		if(parent == null) {
			return neighbors;
		}
		
		for(E edge:getEdges(node)) {
			
			Node<T> origin = edge.getOrigin();
			
			if(!neighbors.contains(origin)) {
				neighbors.add(origin);	
			}
			
			Node<T> destination = edge.getDestination();
			
			if(!neighbors.contains(destination)) {
				neighbors.add(destination);	
			}
		}
		
		return neighbors;
	}
}
