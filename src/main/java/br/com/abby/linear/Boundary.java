package br.com.abby.linear;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.graphics.Graphic;

/**
 * 
 * Based on Dean Povey's answer at: http://stackoverflow.com/questions/8721406/how-to-determine-if-a-point-is-inside-a-2d-convex-polygon
 * @license LGPLv3
 * 
 */

public class Boundary extends ColoredPoint3D{

	protected List<ColoredPoint3D> points = new ArrayList<ColoredPoint3D>(); // Points making up the boundary

	public Boundary(){
		super(0,0,0);
	}
	public Boundary(int x, int y){
		super(x,y,0);
	}
	public Boundary(int x, int y, int z){
		super(x,y,z);
	}
	
	public void add(double x, double y, double z){
		add(new ColoredPoint3D(x, y, z));
	}
	
	public void add(ColoredPoint3D ponto){
		points.add(ponto);		
	}

	/**
	 * Return true if the given point is contained inside the boundary.
	 * See: http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
	 * @param test The point to check
	 * @return true if the point is inside the boundary, false otherwise
	 *
	 */
	public boolean contains(int px, int py) {
		int i;
		int j;
		boolean result = false;

		for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {
			if ((points.get(i).getY() > py) != (points.get(j).getY() > py) &&
					(px < (points.get(j).getX() - points.get(i).getX()) * (py - points.get(i).getY()) / (points.get(j).getY()-points.get(i).getY()) + points.get(i).getX())) {
				result = !result;
			}
		}

		return result;
	}

	public void desenha(Graphic g){

		int i;
		
		for(i=0;i<points.size()-1;i++){
			g.drawLine((int)(x+points.get(i).getX()),(int)(y+points.get(i).getY()),(int)(x+points.get(i+1).getX()),(int)(y+points.get(i+1).getY()));
		}
		
		g.drawLine((int)(x+points.get(i).getX()),(int)(y+points.get(i).getY()),(int)(x+points.get(0).getX()),(int)(y+points.get(0).getY()));

	}
	
	public List<ColoredPoint3D> getPoints() {
		return points;
	}
	
	public void setPoints(List<ColoredPoint3D> points) {
		this.points = points;
	}
	
	
}
