package br.com.abby.linear;

import java.util.Vector;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Polygon3D extends AimPoint {
			
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
		
	public void novoVertice(double x, double y, double z){
		vertices.add(new Point3D(x,y,z));
	}
	
	public Vector<Point3D> getVertices(){
		return vertices;
	}

}
