package br.com.etyllica.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.etyllica.core.event.PointerEvent;

public class RingBufferTest {

	private RingBuffer<PointerEvent> events;
	
	@Before
	public void setUp() {
		events = new RingBuffer<PointerEvent>(PointerEvent.class);		
	}
	
	@Test
	public void testGetSlot() {
		
		PointerEvent event = events.getSlot();
		
		event.setX(50);
		event.setY(80);
		
		Assert.assertEquals(1, events.size());
	}
	
	@Test
	public void testSlotPosition() {
		
		PointerEvent event = events.getSlot();
		
		event.setX(50);
		
		PointerEvent secondEvent = events.getSlot();
		
		secondEvent.setX(55);
		
		Assert.assertEquals(2, events.size());
				
		Assert.assertEquals(50, events.getList().get(0).getX());
		Assert.assertEquals(55, events.getList().get(1).getX());
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
