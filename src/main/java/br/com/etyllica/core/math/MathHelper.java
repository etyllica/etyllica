package br.com.etyllica.core.math;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.etyllica.core.linear.Point3D;
import br.com.etyllica.core.linear.Triangle;

import com.badlogic.gdx.math.Vector3;

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
	
	public static float trianglularPrismVolume(Triangle triangle, float height) {
		float triangleArea = triangleArea(triangle);
		return triangleArea * height;
	}
	
	/**
	 * Highly based on: http://www.mathpages.com/home/kmath393.htm
	 * @param triangle
	 * @return volume between the triangle and the plane z = 0
	 */
	public static float volumeUnderTriangle(Triangle triangle) {
		
		Point3D a = triangle.getA();
		Point3D b = triangle.getB();
		Point3D c = triangle.getC();
		
		List<Point3D> ordered = orderedInZ(a,b,c); 
		
		Point3D p1 = ordered.get(0);
		Point3D p2 = ordered.get(1);
		Point3D p3 = ordered.get(2);
		
		double x1 = p1.getX();
		double y1 = p1.getY();
		double z1 = p1.getZ();
		double x2 = p2.getX();
		double y2 = p2.getY();
		double z2 = p2.getZ();
		double x3 = p3.getX();
		double y3 = p3.getY();
		double z3 = p3.getZ();
		
		double volume = (z1+z2+z3)*(x1*y2-x2*y1+x2*y3-x3*y2+x3*y1-x1*y3)/6;
		return (float)volume;
	}
	
	public static List<Point3D> orderedInZ(Point3D ... points) {
		List<Point3D> ordered = Arrays.asList(points);
		
		Collections.sort(ordered, LOWER_Z_COMPARATOR);
		return ordered;
	}
		
	private static final Comparator<Point3D> LOWER_Z_COMPARATOR = new Comparator<Point3D>() {
	    @Override
	    public int compare(Point3D a, Point3D b) {
	    	double diff = a.getZ()-b.getZ();
	    			
	    	if (diff > 0) {
	    		return 1;
	    	} else if (diff < 0) {
	    		return -1;
	    	} else {
	    		return 0;
	    	}
	    }
	};
	
}
