package br.com.etyllica.core.ui;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.etyllica.core.event.MouseEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.base.BaseTextField;

public class UIModuleTest {

	UIModule uiModule;
	
	@Before
	public void setUp() {
		UIModule.w = 800;
		UIModule.h = 600;

		uiModule = UIModule.getInstance();
	}
	
	@Test
	public void testUpdateMouse() {
		BaseButton b = new BaseButton(10, 10, 200, 20);
		BaseTextField f = new BaseTextField(10, 30, 200, 60);
		
		List<View> views = new ArrayList<View>();
		views.add(b);
		views.add(f);
		
		PointerEvent moveOverButtonEvent = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 20, 20);
		uiModule.updateMouseViews(moveOverButtonEvent, views);
		
		Assert.assertTrue(b.isMouseOver());
		
		PointerEvent moveOverFieldEvent = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 20, 40);
		uiModule.updateMouseViews(moveOverFieldEvent, views);
		
		PointerEvent clickEvent = new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.PRESSED, 20, 40);
		uiModule.updateMouseViews(clickEvent, views);
		
		Assert.assertTrue(f.isOnFocus());
		
	}

}
