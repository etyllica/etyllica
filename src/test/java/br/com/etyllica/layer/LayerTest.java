package br.com.etyllica.layer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LayerTest {

	private Layer layer;
	
	@Before
	public void setUp() {
		
		layer = new Layer(0, 0, 32, 32);
		
		layer.setVisible(false);
				
	}
	
	@Test
	public void testSwapVisible() {
		
		layer.setVisible(false);
		layer.swapVisible();
		
		Assert.assertTrue(layer.isVisible());
		
	}
		
}
