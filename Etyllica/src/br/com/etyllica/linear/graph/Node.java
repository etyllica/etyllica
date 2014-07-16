package br.com.etyllica.linear.graph;

import br.com.etyllica.linear.Point2D;

public class Node extends Point2D {
	
	public Node() {
		super();
	}
	
	public Node(double x, double y) {
		super(x, y);
	}
	
	public Node(Point2D point) {
		super(point.getX(), point.getY());
	}
	
}
