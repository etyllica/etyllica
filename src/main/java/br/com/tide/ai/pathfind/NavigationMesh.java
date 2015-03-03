package br.com.tide.ai.pathfind;

import br.com.etyllica.linear.Point2D;
import br.com.etyllica.linear.graph.GenericComplexGraph;
import br.com.etyllica.linear.graph.GenericEdge;

public class NavigationMesh extends GenericComplexGraph<PolygonalNode, GenericEdge<PolygonalNode>>{

	@Override
	public void addNode(Point2D point) {
		nodes.add(new PolygonalNode());		
	}

}
