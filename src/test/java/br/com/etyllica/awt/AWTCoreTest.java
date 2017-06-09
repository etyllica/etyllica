package br.com.etyllica.awt;

import java.awt.Frame;

import br.com.etyllica.ui.UI;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.etyllica.awt.core.AWTCore;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.MouseEvent;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.commons.event.PointerState;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.ui.base.BaseButton;

public class AWTCoreTest {

	private AWTCore innerCore;

	private Application fakeApplication;
	
	private BaseButton button;

	@Before
	public void setUp() {
		AWTWindow window = new AWTWindow(0, 0, 800,600);
		
		fakeApplication = createFakeApplication();
		window.setApplication(fakeApplication);
		
		Frame component = new Frame();
		innerCore = new AWTCore(component, 800, 600);
		
		innerCore.replaceWindow(window);
		
		button = new BaseButton(0, 0, 100, 20);

		UI.add(button);
	}

	public Application createFakeApplication() {
		Application app =  new Application(100,100) {
			@Override
			public void load() {
				loading = 100;
			}

			@Override
			public void draw(Graphics g) {}
		};

		return app;
	}

	@Test
	public void testUpdateUnLockedContext() {
		Assert.assertTrue(innerCore.updateApplication(fakeApplication, 0));
	}
	
	@Test
	public void testUpdatePointerEvent() {
		Assert.assertNull(innerCore.getMouseOver());
		
		PointerEvent move = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 10, 10);
		innerCore.updatePointerEvent(move);
		Assert.assertNotNull(innerCore.getMouseOver());
	}
	
	@Test
	public void testMouseOut() {
		Assert.assertNull(innerCore.getMouseOver());
		
		PointerEvent move = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 10, 10);
		innerCore.updatePointerEvent(move);
		Assert.assertEquals(button, innerCore.getMouseOver());
		
		move = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 10, 30);
		innerCore.updatePointerEvent(move);
		Assert.assertNotEquals(button, innerCore.getMouseOver());
	}

}
