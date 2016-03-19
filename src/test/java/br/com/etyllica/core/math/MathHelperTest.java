package br.com.etyllica.core.math;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import br.com.etyllica.core.linear.Point3D;
import br.com.etyllica.core.linear.Triangle;

import com.badlogic.gdx.math.Vector3;

public class MathHelperTest {
	@Test
	public void testNormal() {
		Point3D a = new Point3D(0,0,0);
		Point3D b = new Point3D(10,0,0);
		Point3D c = new Point3D(0,10,0);
		Triangle triangle = new Triangle(a, b, c);
		
		Vector3 normal = MathHelper.calculateNormal(triangle);
		
		Assert.assertEquals(0, normal.x, 0.0001);
		Assert.assertEquals(0, normal.y, 0.0001);
		Assert.assertEquals(1, normal.z, 0.0001);
	}
	
	@Test
	public void testOrderedInZList() {
		Point3D a = new Point3D(0,0,3);
		Point3D b = new Point3D(10,0,2);
		Point3D c = new Point3D(0,10,1);

		List<Point3D> ordered = MathHelper.orderedInZ(a, b, c);

		Assert.assertEquals(c, ordered.get(0));
		Assert.assertEquals(b, ordered.get(1));
		Assert.assertEquals(a, ordered.get(2));
	}

	@Test
	public void testAreaTriangleInOrigin() {
		Point3D a = new Point3D(0,0,0);
		Point3D b = new Point3D(10,0,0);
		Point3D c = new Point3D(0,10,0);
		Triangle triangle = new Triangle(a, b, c);

		Assert.assertEquals(50, MathHelper.triangleArea(triangle), 0.00001);
	}

	/**
	 * Test based on explanation: https://www.youtube.com/watch?v=MnpaeFPyn1A
	 */
	@Test
	public void testAreaTriangle() {
		Point3D a = new Point3D(-5,5,-5);
		Point3D b = new Point3D(1,-6,6);
		Point3D c = new Point3D(2,-3,4);
		Triangle triangle = new Triangle(a, b, c);

		Assert.assertEquals(19.3067f, MathHelper.triangleArea(triangle), 0.0001);
	}

	@Test
	public void testTriangularPrismVolume() {
		Point3D a = new Point3D(-5,5,-5);
		Point3D b = new Point3D(1,-6,6);
		Point3D c = new Point3D(2,-3,4);
		Triangle triangle = new Triangle(a, b, c);
		float height = 10;

		Assert.assertEquals(193.0673f, MathHelper.trianglularPrismVolume(triangle, height), 0.0001);
	}

	@Test
	public void testVolumeUnderAlignedTriangle() {
		Point3D a = new Point3D(0,0,1);
		Point3D b = new Point3D(1,0,1);
		Point3D c = new Point3D(0,1,1);
		Triangle triangle = new Triangle(a, b, c);
		
		Point3D d = new Point3D(1,1,1);
		Triangle otherTriangle = new Triangle(b, d, c);

		//0.5 = half of cube volume
		Assert.assertEquals(0.5, MathHelper.volumeUnderTriangle(triangle), 0.001);
		Assert.assertEquals(0.5, MathHelper.volumeUnderTriangle(otherTriangle), 0.001);
	}

	@Test
	public void testVolumeUnderOriginTriangle() {
		Point3D a = new Point3D(0,0,0);
		Point3D b = new Point3D(1,0,0);
		Point3D c = new Point3D(0,1,0);
		Triangle triangle = new Triangle(a, b, c);

		Assert.assertEquals(0, MathHelper.volumeUnderTriangle(triangle), 0.001);
	}
	
	@Test
	public void testVolumeUnderTriangle() {
		Point3D a = new Point3D(-5,5,-5);
		Point3D b = new Point3D(1,-6,6);
		Point3D c = new Point3D(2,-3,4);
		Triangle triangle = new Triangle(a, b, c);

		Assert.assertEquals(24.166f, MathHelper.volumeUnderTriangle(triangle), 0.001);
	}
	
	@Test
	public void testVolumeUnderOtherTriangle() {
		Point3D a = new Point3D(57.352, 78.256, 16.55);
		Point3D b = new Point3D(58.96, 78.256, 16.12);
		Point3D c = new Point3D(58.96, 79.864, 21.98);
		Triangle triangle = new Triangle(a, b, c);

		Assert.assertEquals(23.5519f, MathHelper.volumeUnderTriangle(triangle), 0.001);
	}
	
	@Test
	public void testVolumeOfMesh() {
		Point3D a = new Point3D(0,0,1);
		Point3D b = new Point3D(1,0,1);
		Point3D c = new Point3D(0,-1,1);
		Triangle triangle = new Triangle(a, c, b);
		
		//Projection points
		Point3D pa = new Point3D(a.getX(),a.getY(),0);
		Point3D pb = new Point3D(b.getX(),b.getY(),0);
		Point3D pc = new Point3D(c.getX(),c.getY(),0);
		
		Triangle t1 = new Triangle(a, b, pa);
		Triangle t2 = new Triangle(b, pb, pa);
		Triangle t3 = new Triangle(b, c, pb);
		Triangle t4 = new Triangle(c, pc, pb);
		Triangle t5 = new Triangle(c, a, pc);
		Triangle t6 = new Triangle(a, pa, pc);
		Triangle tp = new Triangle(pa, pb, pc);

		Vector3 ntop = MathHelper.calculateNormal(triangle);
		Vector3 nt1 = MathHelper.calculateNormal(t1);
		Vector3 nt2 = MathHelper.calculateNormal(t2);
		Vector3 nt3 = MathHelper.calculateNormal(t3);
		Vector3 nt4 = MathHelper.calculateNormal(t4);
		Vector3 nt5 = MathHelper.calculateNormal(t5);
		Vector3 nt6 = MathHelper.calculateNormal(t6);
		Vector3 np = MathHelper.calculateNormal(tp);
		
		Assert.assertEquals(1, ntop.z, 0.001);
		Assert.assertEquals(0, nt1.x, 0.001);
		Assert.assertEquals(1, nt1.y, 0.001);
		Assert.assertEquals(nt1.y, nt2.y, 0.0001);
		Assert.assertEquals(0.707, nt3.x, 0.001);
		Assert.assertEquals(-0.707, nt3.y, 0.001);
		Assert.assertEquals(nt3, nt4);
		Assert.assertEquals(-1, nt5.x, 0.001);
		Assert.assertEquals(0, nt5.y, 0.001);
		Assert.assertEquals(nt5.x, nt6.x, 0.001);
		Assert.assertEquals(-1, np.z, 0.001);
		
		Set<Triangle> triangles = new HashSet<Triangle>();
		triangles.add(triangle);
		triangles.add(t1);
		triangles.add(t2);
		triangles.add(t3);
		triangles.add(t4);
		triangles.add(t5);
		triangles.add(t6);
		triangles.add(tp);
		Assert.assertEquals(0.5, MathHelper.volumeOfMesh(triangles), 0.001);
	}
}
