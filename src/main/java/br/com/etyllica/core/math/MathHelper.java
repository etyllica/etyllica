package br.com.etyllica.core.math;

import com.badlogic.gdx.math.Vector3;

import br.com.etyllica.core.linear.Point3D;
import br.com.etyllica.core.linear.Triangle;

public class MathHelper {

	public static float triangleArea(Triangle triangle) {
		Point3D a = triangle.getA();
		Point3D b = triangle.getB();
		Point3D c = triangle.getC();
		
		Vector3 ab = new Vector3((float)(b.getX()-a.getX()), (float)(b.getY()-a.getY()), (float)(b.getZ()-a.getZ()));
		Vector3 ac = new Vector3((float)(c.getX()-a.getX()), (float)(c.getY()-a.getY()), (float)(c.getZ()-a.getZ()));
		
		Vector3 result = ab.crs(ac);
		
		return result.len()/2;
	}
	
	public static float trianglularPrismaVolume(Triangle triangle, float height) {
		float triangleArea = triangleArea(triangle);
		return triangleArea * height;
	}
	
}
