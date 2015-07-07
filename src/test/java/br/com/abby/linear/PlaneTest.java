package br.com.abby.linear;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector3f;

import br.com.etyllica.linear.Point3D;

public class PlaneTest {

	private Plane floor;

	@Before
	public void setUp() {
		//Floor plane
		Vector3f origin = new Vector3f(0,0,0);
		
		//Plane expects points in clockwise order
		floor = new Plane(origin, new Vector3f(0,0,1), new Vector3f(1,0,0));
	}

	@Test
	public void testConstructor() {

		
		
	}

	@Test
	public void testOrthogonalDistance() {
		Point3D point = new Point3D(0, 5, 0);

		Assert.assertEquals(5, floor.distance(point), 0);
	}
}
