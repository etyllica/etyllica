package br.com.etyllica.linear.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.etyllica.linear.Point2D;

public abstract class GenericComplexGraph<N, E extends GenericEdge<N>> {

	protected Set<Node<N>> nodes;
	
	protected Map<Node<N>, List<E>> edges;
	
	public GenericComplexGraph() {
		super();
		
		nodes = new LinkedHashSet<Node<N>>();
		
		edges = new HashMap<Node<N>, List<E>>();
	}

	public Set<Node<N>> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node<N>> nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node<N> node) {
		this.nodes.add(node);
	}
	
	public void addNode(Point2D point) {
		Node<N> node = new Node<N>();
		node.setLocation(point.getX(), point.getY());
		
		nodes.add(node);		
	}

	public Map<Node<N>, List<E>> getAllEdges() {
		return edges;
	}
	
	public List<E> getEdges(Node<N> node) {
		
		if(edges.containsKey(node)) {
			return edges.get(node);
		}
		
		return new ArrayList<E>();
	}
	
	public void addEdge(E edge) {
		
		addNodesFromEdge(edge);
		
		Node<N> origin = edge.getOrigin();		
		
		if(!edges.containsKey(origin)) {
			edges.put(origin, new ArrayList<E>());
		}
		
		edges.get(origin).add(0, edge);
				
	}
	
	private void addNodesFromEdge(GenericEdge<N> edge) {
		
		Node<N> origin = edge.getOrigin();

		if(!nodes.contains(origin)) {
			nodes.add(origin);
		}
		
		Node<N> destination = edge.getDestination();
		
		if(!nodes.contains(destination)) {
			nodes.add(destination);
		}
		
	}
	
	public boolean hasDiretionalEdge(Node<N> origin, Node<N> destination) {
		for(List<E> list: edges.values()) {
			for(E edge: list) {
				if(edge.getOrigin() == origin && edge.getDestination() == destination) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasEdge(Node<N> origin, Node<N> destination) {
		for(List<E> list: edges.values()) {
			for(E edge: list) {
				if(edge.getOrigin() == origin && edge.getDestination() == destination) {
					return true;
				}
				if(edge.getDestination() == origin && edge.getOrigin() == destination) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void clear() {
		nodes.clear();
		edges.clear();
	}
	
}
