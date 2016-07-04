package br.com.etyllica.gui;

import org.junit.Assert;
import org.junit.Test;

public class ViewGroupTest {

	@Test
	public void testAddViewInVerticalPanel() {
		Panel panel = new Panel(0,0,100,50);
		panel.setOrientation(Orientation.VERTICAL);
		
		TextView hello = new TextView("Hello");
		panel.add(hello);
		Assert.assertEquals(panel.getX(), hello.getX());
		Assert.assertEquals(panel.getY(), hello.getY());
		Assert.assertEquals(panel.getW(), hello.getW());
		Assert.assertEquals(panel.getH(), hello.getH());
		
		TextView hi = new TextView("Hi!");
		panel.add(hi);
		
		Assert.assertEquals(panel.getH()/2, hello.getH());
		Assert.assertEquals(panel.getH()/2, hi.getH());
	}
	
}
