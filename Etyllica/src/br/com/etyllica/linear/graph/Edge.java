package br.com.etyllica.linear.graph;

public class Edge {

	protected Node origin;
	
	protected Node destination;
	
	public Edge(Node origin, Node destination) {
		super();
		
		this.origin = origin;
		this.destination = destination;
	}

	public Node getOrigin() {
		return origin;
	}

	public void setOrigin(Node origin) {
		this.origin = origin;
	}

	public Node getDestination() {
		return destination;
	}

	public void setDestination(Node destination) {
		this.destination = destination;
	}
		
}
