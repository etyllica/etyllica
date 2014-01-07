package br.com.etyllica.layer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GeometricLayerTest {

	private GeometricLayer layer;
	
	@Before
	public void setUp(){

		layer = new GeometricLayer(0, 0, 32, 32);

	}
	
	@Test
	public void testRectPointColision(){
		
		Assert.assertTrue(layer.colideRectPoint(20, 20));
		
		Assert.assertTrue(layer.colideRectPoint(0, 32));
		
		Assert.assertFalse(layer.colideRectPoint(0, 33));
		
	}
	
	@Test
	public void testCirclePointColision(){

		Assert.assertTrue(layer.colideCirclePoint(layer.getW()/2, layer.getH()/2));

	}

}
