package br.com.etyllica.core.math;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.etyllica.core.linear.Point3D;
import br.com.etyllica.core.linear.Triangle;

import com.badlogic.gdx.math.Vector3;

public class MathHelper {

	public static Vector3 calculateNormal(Triangle triangle) {
		Vector3 a = new Vector3((float)triangle.getA().getX(), (float)triangle.getA().getY(), (float)triangle.getA().getZ());
		Vector3 b = new Vector3((float)triangle.getB().getX(), (float)triangle.getB().getY(), (float)triangle.getB().getZ());
		Vector3 c = new Vector3((float)triangle.getC().getX(), (float)triangle.getC().getY(), (float)triangle.getC().getZ());

		Vector3 v = new Vector3(b).sub(a);
		Vector3 u = new Vector3(c).sub(a);

		Vector3 normal = new Vector3(v).crs(u);
		normal.nor();

		return normal;
	}

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
	 * Method to calculate the volume under the triangle 
	 * @param triangle
	 * @return volume between the triangle and the plane z = 0
	 */
	public static double volumeUnderTriangle(Triangle triangle) {
		return volumeUnderTriangle(triangle, 0);
	}

	public static double volumeUnderTriangle(Triangle triangle, double z) {
		Point3D a = triangle.getA();
		Point3D b = triangle.getB();
		Point3D c = triangle.getC();

		//Projection points
		Point3D pa = new Point3D(a.getX(),a.getY(),z);
		Point3D pb = new Point3D(b.getX(),b.getY(),z);
		Point3D pc = new Point3D(c.getX(),c.getY(),z);

		Triangle t1 = new Triangle(a, c, pa);
		Triangle t2 = new Triangle(c, pc, pa);
		Triangle t3 = new Triangle(c, b, pc);
		Triangle t4 = new Triangle(b, pb, pc);
		Triangle t5 = new Triangle(b, a, pb);
		Triangle t6 = new Triangle(a, pa, pb);
		Triangle tp = new Triangle(pa, pb, pb);

		Set<Triangle> triangles = new HashSet<Triangle>();
		triangles.add(triangle);
		triangles.add(t1);
		triangles.add(t2);
		triangles.add(t3);
		triangles.add(t4);
		triangles.add(t5);
		triangles.add(t6);
		triangles.add(tp);
		
		return volumeOfMesh(triangles);
	}

	/**
	 * Method to calculate volume of a 3d mesh
	 * Cha Zhang and Tsuhan Chen - EFFICIENT FEATURE EXTRACTION 
	 * FOR 2D/3D OBJECTS IN MESH REPRESENTATION
	 * @param triangles
	 * @return the volume
	 */
	public static double volumeOfMesh(Set<Triangle> triangles) {
		double sum = 0;

		for(Triangle triangle: triangles) {
			sum += signedVolumeOfTriangle(triangle);
		}

		return sum;
	}

	public static double signedVolumeOfTriangle(Triangle triangle) {
		return signedVolumeOfTriangle(triangle.getA(), triangle.getB(), triangle.getC());
	}

	public static double signedVolumeOfTriangle(Point3D p1, Point3D p2, Point3D p3) {
		double v321 = p3.getX()*p2.getY()*p1.getZ();
		double v231 = p2.getX()*p3.getY()*p1.getZ();
		double v312 = p3.getX()*p1.getY()*p2.getZ();
		double v132 = p1.getX()*p3.getY()*p2.getZ();
		double v213 = p2.getX()*p1.getY()*p3.getZ();
		double v123 = p1.getX()*p2.getY()*p3.getZ();
		return (-v321 + v231 + v312 - v132 - v213 + v123)/6.0f;
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
