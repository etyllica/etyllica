package br.com.etyllica.gui.button;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.theme.Theme;

/**
 * 
 * @author yuripourre
 *
 */

public class RoundCornerButton extends BaseButton {

	public RoundCornerButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void draw(Graphics g) {
		
		Theme theme = getTheme();

		if (!mouseOver) {
			g.setColor(theme.getBaseColor());
		} else {
			if (lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN) {
				g.setColor(theme.getActiveColor());
			} else {
				g.setColor(theme.getSelectionColor());
			}
		}
		
		g.fillRoundRect(x, y, w, h, style.roundness.width, style.roundness.height);
		drawLabel(g);
	}
		
}
