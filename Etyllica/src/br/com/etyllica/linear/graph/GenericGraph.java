package br.com.etyllica.linear.graph;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericGraph<E extends Edge> {

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

	public List<E> getEdges() {
		return edges;
	}

	public void setEdges(List<E> edges) {
		this.edges = edges;
	}
		
}
