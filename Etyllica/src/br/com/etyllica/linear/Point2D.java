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

}
