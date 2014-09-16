package br.com.etyllica.gui.radio;

import br.com.etyllica.collision.CollisionDetector;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Radio;
import br.com.etyllica.gui.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class RadioButton extends Radio {

	public RadioButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
			
	@Override
	public void draw(Graphic g) {

		if(!visible)
			return;

		Theme theme = getTheme();		

		if(!disabled) {

			if(!mouseOver) {

				g.setColor(theme.getButtonColor());

			} else {

				if(clicked) {

					g.setColor(theme.getButtonOnClick());

				} else {

					g.setColor(theme.getButtonOnMouse());

				}

			}

		} else {

			g.setColor(theme.getButtonDisabledColor());

		}

		if(checked) {
			g.setColor(theme.getButtonOnClick());
		}
		
		g.fillRect(x,y,w,h);
		drawLabel(g);
	}
	
	@Override
	public boolean onMouse(int mx, int my) {
		return CollisionDetector.colideRectPoint(this, mx, my);
	}

}