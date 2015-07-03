package br.com.abby.adapter;

import org.lwjgl.util.vector.Vector3f;

import br.com.etyllica.linear.Point3D;

public class PointToVectorAdapter {

	public static Vector3f adapt(Point3D point) {
		
		float px = (float) point.getX();
		float py = (float) point.getY();
		float pz = (float) point.getZ();
		
		Vector3f vector = new Vector3f(px, py, pz);
		return vector;
	}
	
	public static Point3D adapt(Vector3f vector) {
		
		double px = vector.getX();
		double py = vector.getY();
		double pz = vector.getZ();
		
		Point3D point = new Point3D(px, py, pz);
		return point;
	}
	
}
