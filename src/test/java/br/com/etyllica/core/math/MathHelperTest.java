package br.com.etyllica.core.math;

import org.junit.Assert;
import org.junit.Test;

import br.com.etyllica.core.linear.Point3D;
import br.com.etyllica.core.linear.Triangle;

public class MathHelperTest {

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
	
	/**
	 * Test based on explanation: https://www.youtube.com/watch?v=MnpaeFPyn1A
	 */
	@Test
	public void testTriangularPrismVolume() {
		Point3D a = new Point3D(-5,5,-5);
		Point3D b = new Point3D(1,-6,6);
		Point3D c = new Point3D(2,-3,4);
		Triangle triangle = new Triangle(a, b, c);
		float height = 10;
		
		Assert.assertEquals(193.0673f, MathHelper.trianglularPrismaVolume(triangle, height), 0.0001);
	}
	
}
