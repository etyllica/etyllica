package br.com.luvia.linear;

import java.awt.Color;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Point3D {
	
	protected Color color;
	
	protected double x;
	protected double y;
	protected double z;

	public Point3D(){
		this(0,0,0);
	}
	
	public Point3D(double x, double y){
		this(x,y,0);
	}
	public Point3D(double x, double y, double z){
		
		this.x = x;
		this.y = y;
		this.z = z;
				
		color = new Color(0,0,0);
	}

	public void setCoordenadas(double x, double y){
		this.x = x;
		this.y = y;
	}
	public void setCoordenadas(double x, double y, double z){
		setCoordenadas(x, y);
		this.z = z;
	}

	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getZ(){
		return z;
	}

	public void setX(double x){
		this.x = x;
	}
	public void setOffsetX(double x){
		this.x += x;
	}
	public void setY(double y){
		this.y = y;
	}
	public void setOffsetY(double y){
		this.y += y;
	}
	public void setZ(double z){
		this.z = z;
	}
	public void setOffsetZ(double z){
		this.z += z;
	}

	public void girar(double angulo){

		//http://ca.answers.yahoo.com/question/index?qid=20100403151916AAbJHxV

		double cos = Math.cos(angulo);
		double sen = Math.sin(angulo);

		x = (x*cos+y*sen);
		y = (-x*sen+y*cos);

	}
		
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String toString(){
		return x+" , "+y+" , "+z;
	}
	
	

}
