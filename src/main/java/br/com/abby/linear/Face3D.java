package br.com.abby.linear;

import java.awt.Color;
import java.util.Vector;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Face3D {
	
	protected Color color;
	
	protected Vector<Point3D> points;

	public Face3D() {
		color = Color.BLACK;
		points = new Vector<Point3D>();
	}

	public void addPoint(int x, int y) {
		points.add(new Point3D(x,y));
	}
	
	public void addPoint(int x, int y, int z) {
		points.add(new Point3D(x,y,z));
	}
	
	public void addPoint(Point3D p) {
		points.add(p);
	}

	public int sides() {
		return points.size();
	}
	public Color getColor() {
		return color;
	}
	public void setColor(int r, int g, int b) {
		color = new Color(r,g,b);		
	}
	
	public void offset(int offsetX, int offsetY) {
		for(int i=0;i<points.size();i++) {
			points.get(i).setX(points.get(i).getX()+offsetX);
			points.get(i).setY(points.get(i).getY()+offsetY);
		}
	}

	/**
	 * Spin by angle based on http://ca.answers.yahoo.com/question/index?qid=20100403151916AAbJHxV
	 * @param angle
	 */
	public void spin(int angle) {
		
		double cos = Math.cos(angle);
		double sen = Math.sin(angle);
	
		for(int i=0;i<points.size();i++) {
			
			double rx = points.get(i).getX();
			double ry = points.get(i).getY();	
			
			points.get(i).setX((int) Math.round(rx*cos+ry*sen));
			points.get(i).setY((int) Math.round(-rx*sen+ry*cos));
		}
	}
	
	public Vector<Point3D> getPoints() {
		return points;
	}
	
	@Override
	public String toString() {
		
		String text = "";
		
		for(int p = 0;p<points.size();p++) {
			
			Point3D point = points.get(p);
			text +="Point "+Integer.toString(p)+": "+point.toString();
			text += "\n";
		}
		
		return text;
	}

}
