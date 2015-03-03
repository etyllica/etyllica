package examples.etyllica.graph.model;

import br.com.etyllica.linear.Point2D;
import br.com.etyllica.linear.graph.Edge;
import br.com.etyllica.linear.graph.GenericComplexGraph;
import br.com.etyllica.linear.graph.Node;

public class ComplexGraph extends GenericComplexGraph<Node, Edge> {
	
	public void addNode(Point2D point) {
		nodes.add(new Node(point.getX(), point.getY()));
	}
	
}