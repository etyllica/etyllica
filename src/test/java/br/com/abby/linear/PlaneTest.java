package br.com.abby.linear;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector3;

public class PlaneTest {

	private Plane floor;

	@Before
	public void setUp() {
		//Floor plane
		Vector3 origin = new Vector3(0,0,0);
		
		//Plane expects points in clockwise order
		floor = new Plane(origin, new Vector3(1,0,0), new Vector3(1,0,1));
	}

	@Test
	public void testConstructor() {
		Assert.assertEquals(0, floor.normal.x, 0);
		Assert.assertEquals(1, floor.normal.y, 0);
		Assert.assertEquals(0, floor.normal.z, 0);
	}

	@Test
	public void testOrthogonalDistance() {
		Vector3 point = new Vector3(2, 5, 0);
		Assert.assertEquals(6, floor.distance(point), 0);
	}
}
