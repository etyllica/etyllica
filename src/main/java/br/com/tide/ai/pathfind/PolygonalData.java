package br.com.tide.ai.pathfind;

import br.com.etyllica.linear.Point2D;
import br.com.etyllica.linear.PolygonalRegion;

public class PolygonalData {
	
	private String name;
	
	private PolygonalRegion polygon = new PolygonalRegion();

	public PolygonalData(String name) {
		super();
		this.name = name;
	}
	
	public void addPoint(int x, int y) {
		addPoint(new Point2D(x, y));
	}
	
	public void addPoint(Point2D point) {
		polygon.addPoint(point);
	}

	public PolygonalRegion getPolygon() {
		return polygon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
}
