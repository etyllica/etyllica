package br.com.etyllica.core.event;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.etyllica.layer.AnimatedLayer;

public class ActionTest {

	private AnimatedLayer layer;
	
	private Action action; 
	
	@Before
	public void setUp(){
		
		layer = new AnimatedLayer(0, 0, 32, 32);
		
		layer.setVisible(false);
		
		action = new Action(layer, "setVisible", true);
		
	}
	
	@Test
	public void testExecuteAction() {
		
		action.executeAction();
		
		Assert.assertTrue(layer.isVisible());
		
	}

}
