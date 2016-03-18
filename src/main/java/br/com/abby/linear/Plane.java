package br.com.abby.linear;

import com.badlogic.gdx.math.Vector3;

public class Plane {
	
	public Vector3 d;
	public Vector3 normal;
	
	public Plane() {
		super();
	}
	
	/**
	 * Constructor expects points in clockwise order
	 * @param a
	 * @param b
	 * @param c
	 */
	public Plane(Vector3 a, Vector3 b, Vector3 c) {
		super();
		
		setPoints(a, b, c);
	}
	
	public void setPoints(Vector3 a, Vector3 b, Vector3 c) {
		Vector3 v = new Vector3(b).sub(a);
		Vector3 u = new Vector3(c).sub(a);
		
		normal = new Vector3(v).crs(u);
		normal.nor();

		Vector3 position = new Vector3(b);
		
		Vector3 negateNormal = normal.scl(-1f);
		d = new Vector3(negateNormal).crs(position);
	}
	
	public double distance(Vector3 point) {
		double dist = normal.dot(point) + d.len();
		return dist;
	}
	
}