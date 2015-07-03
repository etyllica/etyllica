package br.com.abby.linear;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoundingBox3DTest {

	private BoundingBox3D box;
	
	@Before
	public void setUp() {
		//Box representing a cube
		box = new BoundingBox3D(new Point3D(0,0,0), new Point3D(10,10,10));	
	}
	
	@Test
	public void testContainsPoint() {
		Assert.assertTrue(box.contains(new Point3D(0,0,0)));
		Assert.assertFalse(box.contains(new Point3D(11,0,0)));
	}
	
	@Test
	public void testContainsBox() {
		BoundingBox3D anotherBox = new BoundingBox3D(new Point3D(2,2,2), new Point3D(8,8,8));
		
		Assert.assertTrue(box.contains(anotherBox));
		Assert.assertFalse(box.intersects(anotherBox));
	}
	
	@Test
	public void testIntersectBox() {
		BoundingBox3D anotherBox = new BoundingBox3D(new Point3D(8,8,8), new Point3D(12,12,12));
		
		Assert.assertFalse(box.contains(anotherBox));
		Assert.assertTrue(box.intersects(anotherBox));
	}
	
}
