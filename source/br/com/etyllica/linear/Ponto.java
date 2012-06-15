package br.com.etyllica.linear;

import java.awt.geom.Point2D;

public class Ponto extends Point2D {

	protected double x;
	protected double y;
	
	public Ponto(double x, double y){
		this.x = x;
		this.y = y;
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



}
