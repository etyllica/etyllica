package br.com.etyllica.layer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LayerTest {

	private Layer layer;
	
	@Before
	public void setUp(){
		
		layer = new Layer(0, 0, 32, 32);
		
		layer.setVisible(false);
				
	}
	
	@Test
	public void testSwapVisible(){
		
		layer.setVisible(false);
		layer.swapVisible();
		
		Assert.assertTrue(layer.isVisible());
		
	}
	
	@Test
	public void testAddChild() {
		
		int children = 3; 
		
		for(int i=0;i<children;i++){
			layer.addChild(new Layer());	
		}
				
		Assert.assertEquals(children, layer.getChildren().size());
		
	}
	
}
