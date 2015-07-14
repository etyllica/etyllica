package br.com.abby.linear;

import org.lwjgl.util.vector.Vector3f;

public class Plane {
	
	public Vector3f d;
	public Vector3f normal;
	
	public Plane() {
		super();
	}
	
	/**
	 * Constructor expects points in clockwise order
	 * @param a
	 * @param b
	 * @param c
	 */
	public Plane(Vector3f a, Vector3f b, Vector3f c) {
		super();
		
		setPoints(a, b, c);
	}
	
	public void setPoints(Vector3f a, Vector3f b, Vector3f c) {
		Vector3f v = Vector3f.sub(b, a, null);
		Vector3f u = Vector3f.sub(c, a, null);
		
		normal = Vector3f.cross(v, u, null);
		normal.normalise();

		Vector3f position = new Vector3f(b);
		
		Vector3f negateNormal = normal.negate(null);
		d = Vector3f.cross(negateNormal, position, null);
		
		System.out.println("DLEN: "+d.length());
		//DLEN: 0.8945184
		
		float dis = -Vector3f.dot(normal, position);
		System.out.println("NEWD: "+dis);
	}
	
	public double distance(Vector3f point) {
		double dist = Vector3f.dot(normal, point) + d.length();
		return dist;
	}
}