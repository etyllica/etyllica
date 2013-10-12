package br.com.luvia.linear;

import java.util.Vector;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Polygon3D extends Point3D{
	
	protected float anguloX;
	protected float anguloY;
	protected float anguloZ;
		
	protected Vector<Point3D> vertices;
	
	public Polygon3D(){
		this(0,0,0);
	}
	public Polygon3D(double x, double y, double z){
		super(x,y,z);
		
		vertices = new Vector<Point3D>();
			
		/*
		 * Teste
		 */
		//novoVertice( 0, 1, 0);					
		novoVertice( 1, 1,-1);					// Top Right Of The Quad (Top)
		novoVertice(-1, 1,-1);					// Top Left Of The Quad (Top)
		novoVertice(-1, 1, 1);					// Bottom Left Of The Quad (Top)
		novoVertice( 1, 1, 1);					// Bottom Right Of The Quad (Top)
		novoVertice( 1,-1, 1);					// Top Right Of The Quad (Bottom)
		novoVertice(-1,-1, 1);					// Top Left Of The Quad (Bottom)
		novoVertice(-1,-1,-1);					// Bottom Left Of The Quad (Bottom)
		novoVertice( 1,-1,-1);					// Bottom Right Of The Quad (Bottom)
		novoVertice( 1, 1, 1);					// Top Right Of The Quad (Front)
		novoVertice(-1, 1, 1);					// Top Left Of The Quad (Front)
		novoVertice(-1,-1, 1);					// Bottom Left Of The Quad (Front)
		novoVertice( 1,-1, 1);					// Bottom Right Of The Quad (Front)
		novoVertice( 1,-1,-1);					// Top Right Of The Quad (Back)
		novoVertice(-1,-1,-1);					// Top Left Of The Quad (Back)
		novoVertice(-1, 1,-1);					// Bottom Left Of The Quad (Back)
		novoVertice( 1, 1,-1);					// Bottom Right Of The Quad (Back)
		novoVertice(-1, 1, 1);					// Top Right Of The Quad (Left)
		novoVertice(-1, 1,-1);					// Top Left Of The Quad (Left)
		novoVertice(-1,-1,-1);					// Bottom Left Of The Quad (Left)
		novoVertice(-1,-1, 1);					// Bottom Right Of The Quad (Left)
		novoVertice( 1, 1,-1);					// Top Right Of The Quad (Right)
		novoVertice( 1, 1, 1);					// Top Left Of The Quad (Right)
		novoVertice( 1,-1, 1);					// Bottom Left Of The Quad (Right)
		novoVertice( 1,-1,-1);					// Bottom Right Of The Quad (Right)
		
	}
		
	public float getAnguloX(){
		return anguloX;
	}
	public void setAnguloX(float anguloX){
		this.anguloX = anguloX;
	}
	public void setOffsetAnguloX(float offset){
		anguloX = (anguloX+offset)%360;
	}
	public float getAnguloY(){
		return anguloY;
	}
	public void setAnguloY(float anguloY){
		this.anguloY = anguloY;
	}
	public void setOffsetAnguloY(float offset){
		anguloY = (anguloY+offset)%360;
	}
	public float getAnguloZ(){
		return anguloZ;
	}
	public void setAnguloZ(float anguloZ){
		this.anguloZ = anguloZ;
	}
	public void setOffsetAnguloZ(float offset){
		anguloZ = (anguloZ+offset)%360;
	}
	public void offsetAngulos(float offset){
		setOffsetAnguloX(offset);
		setOffsetAnguloY(offset);
		setOffsetAnguloZ(offset);
	}
	
	public void novoVertice(double x, double y, double z){
		vertices.add(new Point3D(x,y,z));
	}
	
	public Vector<Point3D> getVertices(){
		return vertices;
	}

}
