package examples.etyllica.graph.model;

import br.com.etyllica.linear.graph.GenericEdge;
import br.com.etyllica.linear.graph.Node;

public class IntegerEdge extends GenericEdge<Integer> {

	public IntegerEdge(Node<Integer> origin, Node<Integer> destination) {
		super(origin, destination);
	}
		
}
