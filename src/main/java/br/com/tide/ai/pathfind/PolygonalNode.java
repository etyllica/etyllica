package br.com.tide.ai.pathfind;

import br.com.etyllica.linear.Point2D;
import br.com.etyllica.linear.PolygonalRegion;
import br.com.etyllica.linear.graph.Node;

public class PolygonalNode extends Node {
	
	private PolygonalRegion polygon = new PolygonalRegion();

	public void addPoint(int x, int y) {
		addPoint(new Point2D(x, y));
	}
	
	public void addPoint(Point2D point) {
		polygon.addPoint(point);
	}

	public PolygonalRegion getPolygon() {
		return polygon;
	}
	
}
