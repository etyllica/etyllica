package br.com.etyllica.linear.vector;

/**
 * 
 * Based on js-aruco code: http://code.google.com/p/js-aruco/source/browse/trunk/src/posit2.js
 * 
 */

public class Vec3D {

	public double[] v;
	
	public Vec3D() {
		this(0,0,0);
	}
	
	public Vec3D(double x, double y, double z) {
		super();
		
		this.v = new double[3];
		
		v[0] = x;
		v[1] = y;
		v[2] = z;
		
	}

	public void copy(Vec3D a) {
		
		this.v[0] = a.v[0];
		
		this.v[1] = a.v[1];
		
		this.v[2] = a.v[2];
				
	}
	
	public void copy(double x, double y, double z) {
		
		this.v[0] = x;
		
		this.v[1] = y;
		
		this.v[2] = z;
				
	}

	public static Vec3D add(Vec3D a, Vec3D b) {
		
		double x = a.v[0] + b.v[0];
		double y = a.v[1] + b.v[1];
		double z = a.v[2] + b.v[2];
		
		Vec3D v = new Vec3D(x, y, z);

		return v;
	}

	public static Vec3D sub(Vec3D a, Vec3D b) {
		
		double x = a.v[0] - b.v[0];
		double y = a.v[1] - b.v[1];
		double z = a.v[2] - b.v[2];
		
		Vec3D v = new Vec3D(x, y, z);

		return v;
	}

	public static Vec3D mult(Vec3D a, Vec3D b) {
		
		double x = a.v[0] * b.v[0];
		double y = a.v[1] * b.v[1];
		double z = a.v[2] * b.v[2];
		
		Vec3D v = new Vec3D(x, y, z);

		return v;
	}

	public static Vec3D addScalar(Vec3D a, double b) {
		
		double x = a.v[0] + b;
		double y = a.v[1] + b;
		double z = a.v[2] + b;
		
		Vec3D v = new Vec3D(x, y, z);

		return v;
	}

	public static Vec3D multScalar(Vec3D a, double b) {
		
		double x = a.v[0] * b;
		double y = a.v[1] * b;
		double z = a.v[2] * b;
		
		Vec3D v = new Vec3D(x, y, z);

		return v;
	}

	public static double dot(Vec3D a, Vec3D b) {
		
		return a.v[0] * b.v[0] + a.v[1] * b.v[1] + a.v[2] * b.v[2];
	}

	public static Vec3D cross(Vec3D a, Vec3D b) {

		return new Vec3D(
				a.v[1] * b.v[2] - a.v[2] * b.v[1],
				a.v[2] * b.v[0] - a.v[0] * b.v[2],
				a.v[0] * b.v[1] - a.v[1] * b.v[0]);
	}

	public double normalize() {
		
		double len = Math.sqrt(this.square());

		if (len > 0.0) {
			v[0] /= len;
			v[1] /= len;
			v[2] /= len;
		}

		return len;
	}
	
	public double length() {
		return Math.sqrt(this.square());
	}

	public static Vec3D inverse(Vec3D a) {
		
		double x = 0;
		double y = 0;
		double z = 0;
		
		if (a.v[0] != 0.0) {
			x = 1.0 / a.v[0];
		}
		if (a.v[1] != 0.0) {
			y = 1.0 / a.v[1];
		}
		if (a.v[2] != 0.0) {
			z = 1.0 / a.v[2];
		}

		return new Vec3D(x, y, z);
	}

	public double square() {
		
		return v[0] * v[0] + v[1] * v[1] + v[2] * v[2];
	}

	public int minIndex() {
		
		return v[0] < v[1]? (v[0] < v[2]? 0: 2): (v[1] < v[2]? 1: 2);
	}

}
