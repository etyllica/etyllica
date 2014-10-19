package br.com.etyllica.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.Window;
import br.com.etyllica.gui.factory.DefaultButton;

public class InnerCoreTest {

	private InnerCore innerCore;

	private Application fakeApplication;
		
	private List<View> components;
	
	private DefaultButton button;

	@Before
	public void setUp() {
				
		Window window = new Window(0, 0, 800,600);
		
		fakeApplication = createFakeApplication();
		window.setApplication(fakeApplication);
		
		innerCore = new InnerCore();
		
		innerCore.replaceWindow(window);
		
		button = new DefaultButton(0, 0, 100, 20);
		
		components = new ArrayList<View>();
		components.add(button);
	}

	public Application createFakeApplication() {
		
		Application app =  new Application(100,100) {
			
			@Override
			public void load() {
				// TODO Auto-generated method stub
				loading = 100;
			}

			@Override
			public void draw(Graphic g) {
				// TODO Auto-generated method stub
			}
		};

		return app;
	}

	@Test
	public void testUpdateUnLockedContext() {

		Assert.assertTrue(innerCore.updateApplication(fakeApplication, 0));
	}
	
	@Test
	public void testUpdatePointerEvent() {

		Assert.assertNull(innerCore.mouseOver);
		
		PointerEvent move = new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, 10, 10);
				
		innerCore.updatePointerEvent(move, components);
		
		Assert.assertNotNull(innerCore.mouseOver);
	}
	
	@Test
	public void testMouseOut() {

		Assert.assertNull(innerCore.mouseOver);
		
		PointerEvent move = new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, 10, 10);
				
		innerCore.updatePointerEvent(move, components);
		
		Assert.assertEquals(button, innerCore.mouseOver);
		
		move = new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, 10, 30);
		
		innerCore.updatePointerEvent(move, components);
		
		Assert.assertNotEquals(button, innerCore.mouseOver);
	}

}
