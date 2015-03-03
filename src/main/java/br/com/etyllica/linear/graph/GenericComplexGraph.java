package br.com.etyllica.linear.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.etyllica.linear.Point2D;

public abstract class GenericComplexGraph<N extends Node, E extends GenericEdge<N>> {

	protected Set<N> nodes;
	
	protected Map<N, List<E>> edges;
	
	public GenericComplexGraph() {
		super();
		
		nodes = new LinkedHashSet<N>();
		
		edges = new HashMap<N, List<E>>();
	}

	public Set<N> getNodes() {
		return nodes;
	}

	public void setNodes(Set<N> nodes) {
		this.nodes = nodes;
	}

	public void addNode(N node) {
		this.nodes.add(node);
	}
	
	public abstract void addNode(Point2D point);

	public Map<N, List<E>> getAllEdges() {
		return edges;
	}
	
	public List<E> getEdges(N node) {
		
		if(edges.containsKey(node)) {
			return edges.get(node);
		}
		
		return new ArrayList<E>();
	}
	
	public void addEdge(E edge) {
		
		addNodesFromEdge(edge);
		
		N origin = edge.getOrigin();		
		
		if(!edges.containsKey(origin)) {
			edges.put(origin, new ArrayList<E>());
		}
		
		edges.get(origin).add(0, edge);
				
	}
	
	private void addNodesFromEdge(GenericEdge<N> edge) {
		
		N origin = edge.getOrigin();

		if(!nodes.contains(origin)) {
			nodes.add(origin);
		}
		
		N destination = edge.getDestination();
		
		if(!nodes.contains(destination)) {
			nodes.add(destination);
		}
		
	}
	
	public boolean hasDiretionalEdge(N origin, N destination) {
		for(List<E> list: edges.values()) {
			for(E edge: list) {
				if(edge.getOrigin() == origin && edge.getDestination() == destination) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasEdge(N origin, N destination) {
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
