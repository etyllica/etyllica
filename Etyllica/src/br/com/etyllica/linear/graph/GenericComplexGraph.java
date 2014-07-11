package br.com.etyllica.linear.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.etyllica.linear.Point2D;

public class GenericComplexGraph<E extends Edge> {

	protected List<Node> nodes;
	
	protected Map<Node, Set<E>> edges;
	
	public GenericComplexGraph() {
		super();
		
		nodes = new ArrayList<Node>();
		
		edges = new HashMap<Node, Set<E>>();
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
	
	public void addNode(Point2D point) {
		this.nodes.add(new Node(point.getX(), point.getY()));
	}

	public Map<Node, Set<E>> getAllEdges() {
		return edges;
	}
	
	public Set<E> getEdges(Node node) {
		
		if(edges.containsKey(node)) {
			return edges.get(node);
		}
		
		return null;
	}
	
	public void addEdge(E edge) {
		
		Node origin = edge.getOrigin();
		
		if(!edges.containsKey(origin)) {
			edges.put(origin, new HashSet<E>());
		}
		
		edges.get(origin).add(edge);		
	}
			
	public void clear() {
		nodes.clear();
		edges.clear();
	}
	
}
