package br.com.etyllica.ui;

import br.com.etyllica.commons.event.MouseEvent;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.commons.event.PointerState;
import br.com.etyllica.ui.base.BaseButton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UITest {

    private BaseButton button;

    @Before
    public void setUp() {
        button = new BaseButton(0, 0, 100, 20);
        UI.add(button);
        UI.getInstance().resetMouseOver();
    }

    @Test
    public void testUpdatePointerEvent() {
        Assert.assertFalse(UI.getInstance().isMouseOver());

        PointerEvent move = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 10, 10);
        UI.getInstance().updateMouse(move);
        Assert.assertNotNull(UI.getInstance().getMouseOver());
    }

    @Test
    public void testMouseOut() {
        Assert.assertFalse(UI.getInstance().isMouseOver());

        PointerEvent move = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 10, 10);
        UI.getInstance().updateMouse(move);
        Assert.assertEquals(button, UI.getInstance().getMouseOver());

        move = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 10, 30);
        UI.getInstance().updateMouse(move);
        Assert.assertNotEquals(button, UI.getInstance().getMouseOver());
    }
}
