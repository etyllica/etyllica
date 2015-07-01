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
	
}
