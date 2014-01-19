package br.com.etyllica.linear;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Point2D extends java.awt.geom.Point2D {

	protected String nome;
	
	protected double x;
	
	protected double y;
	
	protected int cor;

	public Point2D(double x, double y, int cor){
		this.x = x;
		this.y = y;
		this.cor = cor;
		this.nome = "";
	}

	public Point2D(double x, double y, String nome){
		this.x = x;
		this.y = y;
		this.nome = nome;
		this.cor = 0;
	}

	public Point2D(double x, double y){
		this(x,y,"");
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setOffsetX(double x){
		this.x += x;
	}

	public void setOffsetY(double y){
		this.y += y;
	}
	
	public double angle(Point2D point){
	
		//From Peter O.'s Answer 
		//http://stackoverflow.com/questions/7586063/how-to-calculate-the-angle-between-two-points-relative-to-the-horizontal-axis
		
		double deltaX = point.x - x;
		double deltaY = point.y - y;

		double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;
		
		return angleInDegrees;
	}
	
}
