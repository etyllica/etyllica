package br.com.etyllica.core.ui;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.ui.UI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.etyllica.core.event.MouseEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.ui.View;
import br.com.etyllica.ui.base.BaseButton;
import br.com.etyllica.ui.base.BaseTextField;

public class UITest {

	UI ui;
	
	@Before
	public void setUp() {
		ui = UI.getInstance();
	}
	
	@Test
	public void testUpdateMouse() {
		BaseButton b = new BaseButton(10, 10, 200, 20);
		BaseTextField f = new BaseTextField(10, 30, 200, 60);
		
		List<View> views = new ArrayList<View>();
		views.add(b);
		views.add(f);

		UI.addAll(views);

		PointerEvent moveOverButtonEvent = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 20, 20);
		ui.updateMouseViews(moveOverButtonEvent, views);
		
		Assert.assertTrue(b.isMouseOver());
		
		PointerEvent moveOverFieldEvent = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 20, 40);
		ui.updateMouseViews(moveOverFieldEvent, views);
		
		PointerEvent clickEvent = new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.PRESSED, 20, 40);
		ui.updateMouseViews(clickEvent, views);
		
		Assert.assertTrue(f.isOnFocus());
		
	}

}
