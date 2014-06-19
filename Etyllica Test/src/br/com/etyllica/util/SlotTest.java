package br.com.etyllica.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.etyllica.core.event.PointerEvent;

public class SlotTest {

	private SlotList<PointerEvent> events;
	
	@Before
	public void setUp() {
		
		events = new SlotList<PointerEvent>(PointerEvent.class);
		
	}
	
	@Test
	public void testGetSlot() {
		
		PointerEvent event = events.getSlot();
		
		event.setX(50);
		event.setY(80);
		
		Assert.assertEquals(1, events.size());
		
	}
	
	@Test
	public void testGetSlotAfterPack() {
		
		events.getSlot();
				
		Assert.assertEquals(1, events.size());
		
		events.pack();
		
		Assert.assertEquals(1, events.size());
		
		events.getSlot();
		events.getSlot();
		events.getSlot();
		events.getSlot();
		
		Assert.assertEquals(4, events.size());
		
		events.pack();
		
		Assert.assertEquals(4, events.size());
		
		events.getSlot();
		events.getSlot();
		events.pack();
		
		Assert.assertEquals(2, events.size());
		
	}
	
}
