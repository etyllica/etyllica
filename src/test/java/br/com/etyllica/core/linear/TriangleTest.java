package br.com.etyllica.core.linear;

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {
	
	@Test
	public void testEquals() {
		Point3D a = new Point3D(1,1,1);
		Point3D b = new Point3D(2,2,1);
		Point3D c = new Point3D(2,2,2);
		
		Triangle t1 = new Triangle(a, b, c);
		Triangle t2 = new Triangle(a, b, c);
		
		Assert.assertTrue(t1.equals(t2));
	}	

}
