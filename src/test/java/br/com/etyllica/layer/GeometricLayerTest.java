package br.com.etyllica.layer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.etyllica.core.collision.CollisionDetector;

public class GeometricLayerTest {

	private GeometricLayer layer;
	
	@Before
	public void setUp() {

		layer = new GeometricLayer(0, 0, 32, 32);

	}
	
	@Test
	public void testRectPointColision() {
		
		Assert.assertTrue(CollisionDetector.colideRectPoint(layer, 20, 20));
		
		Assert.assertTrue(CollisionDetector.colideRectPoint(layer, 0, 31));
		Assert.assertTrue(CollisionDetector.colideRectPoint(layer, 0, 32));
		
		Assert.assertFalse(CollisionDetector.colideRectPoint(layer, 0, 33));
		
	}
	
	@Test
	public void testCirclePointColision() {

		Assert.assertTrue(layer.colideCirclePoint(layer.getW()/2, layer.getH()/2));
		Assert.assertFalse(layer.colideCirclePoint(layer.getW(), layer.getY()));

	}

}
