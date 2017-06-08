package br.com.etyllica.commons.collision;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.Layer;

public class CollisionDetectorTest {

	private GeometricLayer fixedLayer;
	private Layer rotatedLayer;
	
	@Before
	public void setUp() {
		fixedLayer = new GeometricLayer(20, 60, 400, 100);
		rotatedLayer = new Layer(20, 60, 400, 100);
		rotatedLayer.setAngle(45);
	}
	
	@Test
	public void testFixedCollision() {
		Assert.assertTrue(CollisionDetector.colideRectPoint(fixedLayer, 20, 60));
		Assert.assertFalse(CollisionDetector.colideRectPoint(fixedLayer, 19, 60));
	}
	
	@Test
	public void testRotatedCollision() {
		Assert.assertTrue(CollisionDetector.colideRectPoint(rotatedLayer, 220, 80));
		
		Assert.assertFalse(CollisionDetector.colideRectPoint(rotatedLayer, 20, 60));
		Assert.assertFalse(CollisionDetector.colideRectPoint(rotatedLayer, 19, 60));
	}
	
}
